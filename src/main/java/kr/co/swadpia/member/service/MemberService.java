package kr.co.swadpia.member.service;

import kr.co.swadpia.common.dto.SessionDTO;
import kr.co.swadpia.member.entity.Member;
import kr.co.swadpia.member.dto.MemberUpdateDTO;
import kr.co.swadpia.repository.jpa.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public List<Member> findAll() {
		List<Member> members = new ArrayList<>();
		memberRepository.findAll().forEach(e -> members.add(e));
		return members;
	}

	public MemberUpdateDTO myInfo(SessionDTO sessionDTO) {
		log.info(sessionDTO.getEmail());
		Optional<Member> optionalMember = memberRepository.findByEmail(sessionDTO.getEmail());
		if (optionalMember.isPresent()) {
			Member member = optionalMember.get();
			member.setPassword("");

			MemberUpdateDTO result = new MemberUpdateDTO();
			BeanUtils.copyProperties(member, result);
			return result;
		} else {
			return null;
		}
	}

	public Optional<Member> findById(long mbrNo){
		Optional<Member> member = memberRepository.findById(mbrNo);
		return member;
	}


	@Transactional("transactionManager")
	public boolean deleteById(long mbrNo){
		boolean result = false;
		Optional<Member> e = memberRepository.findById(mbrNo);
		if(e.isPresent()){
			memberRepository.deleteById(mbrNo);
			result = true;
		}
		return result;
	}

	@Transactional("transactionManager")
	public Member save(Member member){
		memberRepository.save(member);
		return member;
	}

	@Transactional("transactionManager")
	public boolean updateById(long mbrNo, Member member){
		boolean result = false;
		Optional<Member> e = memberRepository.findById(mbrNo);
		member.setMemberId(mbrNo);
		if(e.isPresent()){
			memberRepository.save(member);
			result = true;
		}
		return result;
	}

}
