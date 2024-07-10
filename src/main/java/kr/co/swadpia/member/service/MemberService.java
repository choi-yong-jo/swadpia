package kr.co.swadpia.member.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import kr.co.swadpia.common.constant.ResultCode;
import kr.co.swadpia.common.dto.LoginParamDTO;
import kr.co.swadpia.common.dto.ResponseDTO;
import kr.co.swadpia.common.dto.SessionDTO;
import kr.co.swadpia.common.utility.RegexUtils;
import kr.co.swadpia.common.utility.SHA256;
import kr.co.swadpia.member.dto.LoginDTO;
import kr.co.swadpia.member.dto.MemberInsertDTO;
import kr.co.swadpia.member.dto.MemberRoleDTO;
import kr.co.swadpia.member.entity.Member;
import kr.co.swadpia.member.dto.MemberUpdateDTO;
import kr.co.swadpia.member.entity.MemberRole;
import kr.co.swadpia.member.entity.Role;
import kr.co.swadpia.repository.jpa.MemberRepository;
import kr.co.swadpia.repository.jpa.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

import static kr.co.swadpia.config.auth.JwtConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberRoleRepository memberRoleRepository;
	
	public List<Member> findAll() {
		List<Member> members = new ArrayList<>();
		memberRepository.findAll().forEach(e -> members.add(e));
		return members;
	}

	public Optional<Member> findById(long mbrNo){
		Optional<Member> member = memberRepository.findById(mbrNo);
		return member;
	}

	public ResponseDTO login(LoginDTO dto) {
		Optional<Member> m = memberRepository.findByMemberId(dto.getMemberId());
		String password = m.get().getPassword();
		ResponseDTO responseDTO = new ResponseDTO();
		if (m.isEmpty()) {
			responseDTO.setResultCode(ResultCode.NOT_FOUND_MEMBER.getName());
			responseDTO.setMsg(ResultCode.NOT_FOUND_MEMBER.getValue());
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
	public ResponseDTO insert(MemberInsertDTO dto) {
		Member member = new Member();
		BeanUtils.copyProperties(dto, member);
		ResponseDTO responseDTO = validationCheck(member);
		if ("".equals(responseDTO.getResultCode()) || responseDTO.getResultCode() == null) {
			member.setUseYn("Y");
			memberRepository.save(member);
			responseDTO.setResultCode(ResultCode.INSERT.getName());
			responseDTO.setMsg(ResultCode.INSERT.getValue());
		}

		return responseDTO;
	}

	@Transactional("transactionManager")
	public ResponseDTO update(MemberUpdateDTO dto) {
		Optional<Member> m = memberRepository.findById(dto.getMemberSeq());
		ResponseDTO responseDTO = new ResponseDTO();
		if(m.isPresent()){
			Member member = new Member();
			BeanUtils.copyProperties(dto, member);
			responseDTO = validationCheck(member);
			if ("".equals(responseDTO.getResultCode()) || responseDTO.getResultCode() == null) {
				member.setUseYn("Y");
				memberRepository.save(member);
				responseDTO.setResultCode(ResultCode.UPDATE.getName());
				responseDTO.setMsg(ResultCode.UPDATE.getValue());
			}
		} else {
			responseDTO.setResultCode(ResultCode.NOT_FOUND_MEMBER.getName());
			responseDTO.setMsg(ResultCode.NOT_FOUND_MEMBER.getValue());
		}
		return responseDTO;
	}

	@Transactional("transactionManager")
	public ResponseDTO delete(long memberSeq){
		ResponseDTO responseDTO = new ResponseDTO();
		Optional<Member> m = memberRepository.findById(memberSeq);
		if(m.isPresent()){
			Member member = m.get();
			member.setUseYn("N");
			memberRepository.save(member);
			responseDTO.setResultCode(ResultCode.DELETE.getName());
			responseDTO.setMsg(ResultCode.DELETE.getValue());
		} else {
			responseDTO.setResultCode(ResultCode.NOT_FOUND_MEMBER.getName());
			responseDTO.setMsg(ResultCode.NOT_FOUND_MEMBER.getValue());
		}
		return responseDTO;
	}

	@Transactional("transactionManager")
	public ResponseDTO insertMemberRole(long memberSeq, MemberRoleDTO dto) {
		ResponseDTO responseDTO = new ResponseDTO();
		String[] role = dto.getRoles().split(",");
		for(int i=0 ; i<role.length; i++) {
			MemberRole memberRole = new MemberRole();
			memberRole.setMemberSeq(memberSeq);
			memberRole.setRoleSeq(Long.valueOf(role[i]));
			memberRoleRepository.save(memberRole);
		}

		return responseDTO;
	}

	@Transactional("transactionManager")
	public ResponseDTO deleteMemberRole(long memberSeq, MemberRoleDTO dto) {
		ResponseDTO responseDTO = new ResponseDTO();
		String[] role = dto.getRoles().split(",");
		for(int i=0 ; i<role.length; i++) {
			MemberRole memberRole = new MemberRole();
			memberRole.setMemberSeq(memberSeq);
			memberRole.setRoleSeq(Long.valueOf(role[i]));
			memberRoleRepository.delete(memberRole);
		}

		return responseDTO;
	}

	public Map<String, String> createRefreshToken(Member member) {
		long now = System.currentTimeMillis();
		Map<String, String> accessTokenResponseMap = new HashMap<>();
		String accessToken = JWT.create()
				.withSubject(member.getEmail())
				.withExpiresAt(new Date(now + AT_EXP_TIME))
				.withClaim("roles", member.getRoles().stream().map(Role::getRoleId)
						.collect(Collectors.toList()))
				.sign(Algorithm.HMAC256(ADMIN_JWT_SECRET));

		accessTokenResponseMap.put(AT_HEADER, accessToken);
		return accessTokenResponseMap;
	}

	public ResponseDTO selectObject(Object obj) {
		ResponseDTO dto = new ResponseDTO();
		if (obj == null) {
			dto.setResultCode(ResultCode.EMPTY.getName());
			dto.setMsg(ResultCode.EMPTY.getValue());
		} else {
			dto.setResultCode(ResultCode.SUCCESS.getName());
			dto.setMsg(ResultCode.SUCCESS.getValue());
			dto.setRes(obj);
		}

		return dto;
	}

	public ResponseDTO validationCheck(Member member) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Member> list = memberRepository.findByMemberId(member.getMemberId()).stream().toList();
		if (list.size() > 0) {
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
