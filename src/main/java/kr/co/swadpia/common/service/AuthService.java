package kr.co.swadpia.common.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.mail.MessagingException;
import kr.co.swadpia.common.dto.FindMemberEmailDTO;
import kr.co.swadpia.common.dto.MemberJoinDTO;
import kr.co.swadpia.common.dto.SessionDTO;
import kr.co.swadpia.config.exception.CustomException;
import kr.co.swadpia.common.constant.EmailType;
import kr.co.swadpia.common.constant.ErrorCode;
import kr.co.swadpia.entity.EmailTemplate;
import kr.co.swadpia.member.entity.Member;
import kr.co.swadpia.member.entity.Role;
import kr.co.swadpia.repository.jpa.EmailTemplateRepository;
import kr.co.swadpia.repository.jpa.MemberRepository;
import kr.co.swadpia.repository.jpa.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static kr.co.swadpia.config.auth.JwtConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final MailService mailService;
    private final EmailTemplateRepository emailTemplateRepository;




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Member> memberOptional = memberRepository.findByEmail(email);
        if (memberOptional.isPresent()) {

            Member member = memberOptional.get();
            SessionDTO session = new SessionDTO();
            session.setMemberId(member.getMemberId());
            session.setEmail(member.getEmail());
            session.setName(member.getName());
            session.setPassword(member.getPassword());
            List<GrantedAuthority> authorities = member.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getValue())).collect(Collectors.toList());
            session.setAuthorities(authorities);

            /*List<Menu> accessibleMenus = menuRepository.findAll().stream()
                .filter(menu -> menu.getMenuGroupAuthorities().stream()
                    .anyMatch(menuGroup -> userRoles.contains(menuGroup.getRole().getName())))
                .collect(Collectors.toList());*/


            return session;
        } else {
            return null;
        }
    }
    public SessionDTO getSessionByEmail(String email) throws UsernameNotFoundException {
        Optional<Member>  memberOptional = memberRepository.findByEmail(email);
        if (memberOptional.isPresent()) {

            Member member = memberOptional.get();
            SessionDTO session = new SessionDTO();
            session.setMemberId(member.getMemberId());
            session.setEmail(member.getEmail());
            session.setName(member.getName());
            session.setPassword(member.getPassword());
            session.setRoleList(member.getRoles());
            List<GrantedAuthority> authorities = member.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getValue())).collect(Collectors.toList());
            session.setAuthorities(authorities);
            return session;
        } else {
            return null;
        }
    }

    public Boolean checkMobile(String mobile) {
        if (memberRepository.countAllByMobile(mobile) > 0) {
            return false;
        }
        return true;
    }

    public Boolean checkEmail(String email) {
        if (memberRepository.countAllByEmail(email) > 0) {
            return false;
        }
        return true;
    }

    public Boolean resestPassword(String password, String verificationCode) throws Exception {
        Optional<Member> memberOptional = memberRepository.findByVerificationCode(verificationCode);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            member.setPassword(passwordEncoder.encode(password));
            member.setVerificationCode(null);
            memberRepository.save(member);
            return true;
        } else {
            throw new Exception("해당하는 회원 정보를 찾을 수 없습니다.");
        }
    }

    public FindMemberEmailDTO findMemberEmail(String name, String mobile) throws Exception {
        Optional<Member> memberOptional = memberRepository.findByNameAndMobile(name, mobile);

        if (memberOptional.isPresent()) {
            FindMemberEmailDTO result = new FindMemberEmailDTO();

            result.setEmail(memberOptional.get().getEmail());
            return result;
        } else {
            throw new CustomException(ErrorCode.NOT_FOUND,"해당하는 회원 정보를 찾을 수 없습니다.");
        }
    }

    public Boolean findMemberPassword(String email, String name) throws MessagingException, IOException {
        Optional<Member> memberOptional = memberRepository.findByEmailAndName(email, name);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setVerificationCode(RandomStringUtils.randomAlphanumeric(30));
            member = memberRepository.save(member);
            Optional<EmailTemplate> emailTemplateOptional = emailTemplateRepository.findByType(EmailType.find_password);
            if (emailTemplateOptional.isPresent()) {
                mailService.sendMail("패스워드 변경", member.getEmail(), "https://erp.swadpia.co.kr/signin/reset-password?verication=" + member.getVerificationCode());
            }

            return true;
        } else {
            return false;
        }
    }

    public Boolean join(MemberJoinDTO param) throws Exception {

        if (memberRepository.countAllByEmail(param.getEmail()) > 0) {
            throw new Exception("이미 가입된 이메일입니다.");
        }
        if (memberRepository.countAllByMobile(param.getMobile()) > 0) {
            throw new Exception("이미 가입된 번호입니다.");
        }
//        if (memberRepository.countAllByDupInfo(param.getDupInfo()) > 0) {
//            throw new Exception("이미 가입된 회원입니다.");
//        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member member = new Member();
        BeanUtils.copyProperties(param, member);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        List<Role> memberRoles = new ArrayList<>();
        memberRoles.add(roleRepository.findTopByValue("MEMBER"));
        member.setRoles(memberRoles);
//        member.setEmailVerification(false);
        member.setVerificationCode(RandomStringUtils.randomAlphanumeric(20));

        Timestamp now = new Timestamp(new Date().getTime());
        member = memberRepository.save(member);



        return true;
    }


    public Boolean signout(SessionDTO sessionDTO) throws Exception {
        Optional<Member> memberOptional = memberRepository.findById(Long.valueOf(sessionDTO.getMemberId()));
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            // make deleted prefix
            StringBuffer deletedPrefixBuffer = new StringBuffer();
            deletedPrefixBuffer.append("d_");
            deletedPrefixBuffer.append(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
            // deleted_prefix.append(UUID.randomUUID());
            deletedPrefixBuffer.append("_");
            String deletedPrefix = deletedPrefixBuffer.toString();

            // save with deleted prefix
            member.setEmail(deletedPrefix + member.getEmail());
//            member.setDupInfo(deletedPrefix + member.getDupInfo());
            member.setMobile("d_" + member.getMobile());
            memberRepository.save(member);

            return true;

        } else {
            throw new Exception("해당하는 회원 정보를 찾을 수 없습니다.");
        }
    }
    public void updateRefreshToken(String email, String refreshToken) {
        Optional<Member>  memberOptional = memberRepository.findByEmail(email);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setRefreshToken(refreshToken);
            memberRepository.save(member);
        } else {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
    }

    public Map<String, String> refresh(String refreshToken) {

        // === Refresh Token 유효성 검사 === //
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(MEMBER_JWT_SECRET)).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);

        // === Access Token 재발급 === //
        long now = System.currentTimeMillis();
        String email = decodedJWT.getSubject();
        log.error("email = " + email);

        Optional<Member>  memberOptional = memberRepository.findByEmail(email);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            String accessToken = JWT.create()
                    .withSubject(member.getEmail())
                    .withExpiresAt(new Date(now + AT_EXP_TIME))
                    .withClaim("roles", member.getRoles().stream().map(Role::getValue)
                            .collect(Collectors.toList()))
                    .sign(Algorithm.HMAC256(MEMBER_JWT_SECRET));
            Map<String, String> accessTokenResponseMap = new HashMap<>();

            // === 현재시간과 Refresh Token 만료날짜를 통해 남은 만료기간 계산 === //
            // === Refresh Token 만료시간 계산해 1개월 미만일 시 refresh token도 발급 === //
            long refreshExpireTime = decodedJWT.getClaim("exp").asLong() * 1000;
            long diffDays = (refreshExpireTime - now) / 1000 / (24 * 3600);
            long diffMin = (refreshExpireTime - now) / 1000 / 60;
            if (diffMin < 5) {
                String newRefreshToken = JWT.create()
                        .withSubject(member.getEmail())
                        .withExpiresAt(new Date(now + RT_EXP_TIME))
                        .sign(Algorithm.HMAC256(MEMBER_JWT_SECRET));
                accessTokenResponseMap.put(RT_HEADER, newRefreshToken);
                member.setRefreshToken(newRefreshToken);
            }

            accessTokenResponseMap.put(AT_HEADER, accessToken);
            return accessTokenResponseMap;

        } else {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
    }
}
