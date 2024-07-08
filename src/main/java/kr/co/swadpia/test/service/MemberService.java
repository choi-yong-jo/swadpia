package kr.co.swadpia.test.service;

import kr.co.swadpia.entity.Member;
import kr.co.swadpia.repository.jpa.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public List<Member> findAll() {
		List<Member> members = new ArrayList<>();
		memberRepository.findAll().forEach(e -> members.add(e));
		return members;
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
