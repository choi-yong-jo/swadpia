package kr.co.swadpia.member.service;

import kr.co.swadpia.common.dto.SessionDTO;
import kr.co.swadpia.entity.Member;
import kr.co.swadpia.repository.jpa.MemberRepository;
import kr.co.swadpia.member.dto.MemberUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {


        private final MemberRepository memberRepository;


    public MemberUpdateDTO myinfio(SessionDTO sessionDTO) {
        log.debug(sessionDTO.getEmail());
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


}
