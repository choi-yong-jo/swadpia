package kr.co.swadpia.member.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.co.swadpia.common.constant.ResultCode;
import kr.co.swadpia.common.dto.ResponseDTO;
import kr.co.swadpia.common.service.CommonUtilService;
import kr.co.swadpia.common.utility.RegexUtils;
import kr.co.swadpia.member.entity.*;
import kr.co.swadpia.member.dto.*;
import kr.co.swadpia.repository.jpa.MemberRepository;
import kr.co.swadpia.repository.jpa.MemberRoleRepository;
import kr.co.swadpia.team.entity.QTeam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static kr.co.swadpia.config.auth.JwtConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberRoleRepository memberRoleRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	EntityManager em;

	@Autowired
	CommonUtilService commonUtilService;

	JPAQueryFactory queryFactory;

	public List<Member> selectMember(String useYn) {
		List<Member> list;
		QMember m = QMember.member;
		queryFactory = new JPAQueryFactory(em);
		list = queryFactory
				.selectFrom(m)
				.where(
						m.useYn.eq(useYn)
				).fetch().stream().toList();

		return list;
	}

	public ResponseDTO selectMemberInfo(String useYn) {
		List<Tuple> info;
		QMember m = QMember.member;
		QTeam t = QTeam.team;
		queryFactory = new JPAQueryFactory(em);
		info = queryFactory.from(m)
				.select(m.memberId, t.teamNm, m.name, m.email, m.mobile)
				.innerJoin(t)
				.on(t.teamId.eq(m.teamId))
				.where(
						m.useYn.eq(useYn)
				).fetch().stream().toList();

		String[] column = {"memberId","teamNm","name","email","mobile"};
		ResponseDTO data = commonUtilService.setMemberDetail(column, info);

		return data;
	}
	
	public ResponseDTO findAll() {
		List<Member> members = new ArrayList<>();
//		memberRepository.findAll().forEach(e -> members.add(e));
//		memberRepository.findByUseYn("Y").forEach(e -> members.add((Member) e));
		members = selectMember("Y");

		ResponseDTO responseDTO = commonUtilService.selectObject(members);

//		ResponseDTO responseDTO = selectMemberInfo("Y");

		return responseDTO;
	}

	public ResponseDTO getMemberDetail(long memberSeq){
		List<Tuple> info;
		QMember m = QMember.member;
		QTeam t = QTeam.team;
		queryFactory = new JPAQueryFactory(em);
		info = queryFactory.from(m)
				.select(m.memberId, t.teamNm, m.name, m.email, m.mobile)
				.innerJoin(t)
				.on(t.teamId.eq(m.teamId))
				.where(
						m.memberSeq.eq(memberSeq)
				).fetch().stream().toList();

		String[] column = {"memberId","teamNm","name","email","mobile"};
		ResponseDTO data = commonUtilService.setMemberDetail(column, info);
		return data;
	}

	public Optional<Member> findById(long memberSeq){
		Optional<Member> member;
		QMember m = QMember.member;
		queryFactory = new JPAQueryFactory(em);
		member = Optional.ofNullable(
				queryFactory.selectFrom(m)
                .where(
                	m.memberSeq.eq(memberSeq)
                ).fetchOne());

//		Optional<Member> member = memberRepository.findById(memberSeq);
		return member;
	}

	public ResponseDTO login(LoginDTO dto) {
		Optional<Member> m = memberRepository.findByMemberId(dto.getMemberId());
		String password = m.get().getPassword();
		ResponseDTO responseDTO = new ResponseDTO();
		if (m.isEmpty()) {
			responseDTO.setResultCode(ResultCode.NOT_FOUND_INFO.getName());
			responseDTO.setMsg(ResultCode.NOT_FOUND_INFO.getValue());
		} else if (!password.equals(dto.getPassword())) {
			responseDTO.setResultCode(ResultCode.NOT_VALIDATED_PASSWORD.getName());
			responseDTO.setMsg(ResultCode.NOT_VALIDATED_PASSWORD.getValue());
		} else {
			// TODO : refreshToken 값 새로 생성하여 member 업데이트
			Map<String, String> tokens = createRefreshToken(m.get());
			Member member = new Member();
			BeanUtils.copyProperties(m.get(), member);
			member.setRefreshToken(tokens.get("accessToken").toString());
			memberRepository.save(member);

			responseDTO.setResultCode(ResultCode.SUCCESS.getName());
			responseDTO.setMsg(ResultCode.SUCCESS.getValue());
			responseDTO.setRes(member);
		}

		return responseDTO;
	}

	@Transactional("transactionManager")
	public ResponseDTO insert(MemberDTO dto) {
		Member member = new Member();
		BeanUtils.copyProperties(dto, member);
		ResponseDTO responseDTO = validationCheck(member, "I");
		if ("".equals(responseDTO.getResultCode()) || responseDTO.getResultCode() == null) {
			memberRepository.save(member);
			responseDTO.setResultCode(ResultCode.INSERT.getName());
			responseDTO.setMsg(ResultCode.INSERT.getValue());
		}

		return responseDTO;
	}

	@Transactional("transactionManager")
	public ResponseDTO update(Long memberSeq, MemberDTO dto) {
		Optional<Member> m = memberRepository.findByMemberSeq(memberSeq);
		ResponseDTO responseDTO = new ResponseDTO();
		if(m.isPresent()) {
			Member member = new Member();
			BeanUtils.copyProperties(dto, member);
			member.setMemberSeq(memberSeq);
			responseDTO = validationCheck(member, "U");
			if ("".equals(responseDTO.getResultCode()) || responseDTO.getResultCode() == null) {
				memberRepository.save(member);
				responseDTO.setResultCode(ResultCode.UPDATE.getName());
				responseDTO.setMsg(ResultCode.UPDATE.getValue());
			}
		} else {
			responseDTO.setResultCode(ResultCode.NOT_FOUND_INFO.getName());
			responseDTO.setMsg(ResultCode.NOT_FOUND_INFO.getValue());
		}
		return responseDTO;
	}

	@Transactional("transactionManager")
	public ResponseDTO delete(long memberSeq){
		ResponseDTO responseDTO = new ResponseDTO();
		Optional<Member> m = memberRepository.findByMemberSeq(memberSeq);
		if(m.isPresent()){
			Member member = m.get();
			member.setUseYn("N");
			memberRepository.save(member);
			responseDTO.setResultCode(ResultCode.DELETE.getName());
			responseDTO.setMsg(ResultCode.DELETE.getValue());
		} else {
			responseDTO.setResultCode(ResultCode.NOT_FOUND_INFO.getName());
			responseDTO.setMsg(ResultCode.NOT_FOUND_INFO.getValue());
		}
		return responseDTO;
	}

	public ResponseDTO mappingById(Long memberSeq) {
		List<MemberRole> roles = memberRoleRepository.findByMemberSeqOrderByRoleSeq(memberSeq);
		ResponseDTO responseDTO = commonUtilService.selectObject(roles);
		return responseDTO;
	}

	@Transactional("transactionManager")
	public ResponseDTO insertMemberRole(MemberRoleDTO dto) {
		ResponseDTO responseDTO = new ResponseDTO();
		String[] role = dto.getRoles().split(",");
		List<MemberRole> roles = memberRoleRepository.findByMemberSeqOrderByRoleSeq(dto.getMemberSeq());
		int insCnt = 0;
		for(int i=0 ; i<role.length; i++) {
			boolean existedRole = true;
			boolean roleCheck = false;
			if (roles.size() > 0) {
				for (int j=0; j<roles.size(); j++) {
					Long insRole = Long.valueOf(role[i]);
					if(existedRole) {
						existedRole = (insRole == roles.get(j).getRoleSeq()) ? false : true;
					}
				}
			}
			List<Role> list = roleService.findAll();
			for(int k=0; k<list.size(); k++) {
				if (!roleCheck) {
					roleCheck = (list.get(k).getRoleSeq() == Long.valueOf(role[i])) ? true : false;
				}
			}
			if (existedRole && roleCheck) {
				MemberRole memberRole = new MemberRole();
				memberRole.setMemberSeq(dto.getMemberSeq());
				memberRole.setRoleSeq(Long.valueOf(role[i]));
				memberRoleRepository.save(memberRole);
				insCnt++;
			}
		}

		if (insCnt > 0) {
			responseDTO.setResultCode(ResultCode.INSERT.getName());
			responseDTO.setMsg(ResultCode.INSERT.getValue());
		} else {
			responseDTO.setResultCode(ResultCode.NOT_INSERT_MEMBER_ROLE_EXIST.getName());
			responseDTO.setMsg(ResultCode.NOT_INSERT_MEMBER_ROLE_EXIST.getValue());
		}

		return responseDTO;
	}

	@Transactional("transactionManager")
	public ResponseDTO deleteMemberRole(MemberRoleDTO dto) {
		ResponseDTO responseDTO = new ResponseDTO();
		String[] role = dto.getRoles().split(",");
		int delCnt = 0;
		for(int i=0 ; i<role.length; i++) {
			Optional<MemberRole> mr = memberRoleRepository.findByMemberSeqAndRoleSeq(dto.getMemberSeq(), Long.valueOf(role[i]));
			if(!mr.isEmpty()) {
				MemberRole memberRole = new MemberRole();
				memberRole.setMemberSeq(dto.getMemberSeq());
				memberRole.setRoleSeq(Long.valueOf(role[i]));
				memberRoleRepository.deleteById(mr.get().getMemberRoleSeq());
				delCnt++;
			}
		}

		if (delCnt > 0) {
			responseDTO.setResultCode(ResultCode.DELETE.getName());
			responseDTO.setMsg(ResultCode.DELETE.getValue());
		} else {
			responseDTO.setResultCode(ResultCode.NOT_DELETE_MEMBER_ROLE.getName());
			responseDTO.setMsg(ResultCode.NOT_DELETE_MEMBER_ROLE.getValue());
		}

		return responseDTO;
	}

	public Map<String, String> createRefreshToken(Member member) {
		long now = System.currentTimeMillis();
		Map<String, String> accessTokenResponseMap = new HashMap<>();
		String accessToken = JWT.create()
				.withSubject(member.getEmail())
				.withExpiresAt(new Date(now + AT_EXP_TIME))
				.sign(Algorithm.HMAC256(ADMIN_JWT_SECRET));

		accessTokenResponseMap.put(AT_HEADER, accessToken);
		return accessTokenResponseMap;
	}

	public ResponseDTO validationCheck(Member member, String type) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Member> list = memberRepository.findByMemberId(member.getMemberId()).stream().toList();
		if ("I".equals(type) && list.size() > 0) {
			responseDTO.setResultCode(ResultCode.NOT_INSERT_SAME_MEMBER_ID.getName());
			responseDTO.setMsg(ResultCode.NOT_INSERT_SAME_MEMBER_ID.getValue());
		} else if (!RegexUtils.isValidEmail(member.getEmail())) {
			responseDTO.setResultCode(ResultCode.NOT_VALIDATED_EMAIL.getName());
			responseDTO.setMsg(ResultCode.NOT_VALIDATED_EMAIL.getValue());
		} else if (!RegexUtils.isValidPhoneNumber(member.getMobile())) {
			responseDTO.setResultCode(ResultCode.NOT_VALIDATED_MOBILE.getName());
			responseDTO.setMsg(ResultCode.NOT_VALIDATED_MOBILE.getValue());
		}

		return responseDTO;
	}

}
