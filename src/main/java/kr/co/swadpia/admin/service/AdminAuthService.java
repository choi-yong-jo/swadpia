package kr.co.swadpia.admin.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import kr.co.swadpia.admin.dto.AdminSessionDTO;
import kr.co.swadpia.common.entity.Admin;
import kr.co.swadpia.member.entity.Role;
import kr.co.swadpia.repository.jpa.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static kr.co.swadpia.config.auth.JwtConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminAuthService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isPresent()) {

            Admin admin = adminOptional.get();
            AdminSessionDTO adminSession = new AdminSessionDTO();
            adminSession.setAdminId(admin.getAdminId());
            adminSession.setEmail(admin.getEmail());
            adminSession.setName(admin.getName());
            adminSession.setPassword(admin.getPassword());
            List<GrantedAuthority> authorities = admin.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleId())).collect(Collectors.toList());
            adminSession.setAuthorities(authorities);
            return adminSession;
        } else {
            return null;
        }
    }
    public void updateRefreshToken(String email, String refreshToken) {
        Optional<Admin>  adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setRefreshToken(refreshToken);
            adminRepository.save(admin);
        } else {
            throw new RuntimeException("관리자를 찾을 수 없습니다.");
        }
    }

    public Map<String, String> refresh(String refreshToken) {

        // === Refresh Token 유효성 검사 === //
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(ADMIN_JWT_SECRET)).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);

        // === Access Token 재발급 === //
        long now = System.currentTimeMillis();
        String email = decodedJWT.getSubject();


        Optional<Admin>  adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();

            String accessToken = JWT.create()
                    .withSubject(admin.getEmail())
                    .withExpiresAt(new Date(now + AT_EXP_TIME))
                    .withClaim("roles", admin.getRoles().stream().map(Role::getRoleId)
                            .collect(Collectors.toList()))
                    .sign(Algorithm.HMAC256(ADMIN_JWT_SECRET));
            Map<String, String> accessTokenResponseMap = new HashMap<>();

            // === 현재시간과 Refresh Token 만료날짜를 통해 남은 만료기간 계산 === //
            // === Refresh Token 만료시간 계산해 1개월 미만일 시 refresh token도 발급 === //
            long refreshExpireTime = decodedJWT.getClaim("exp").asLong() * 1000;
            long diffDays = (refreshExpireTime - now) / 1000 / (24 * 3600);
            long diffMin = (refreshExpireTime - now) / 1000 / 60;
            if (diffMin < 5) {
                String newRefreshToken = JWT.create()
                        .withSubject(admin.getEmail())
                        .withExpiresAt(new Date(now + RT_EXP_TIME))
                        .sign(Algorithm.HMAC256(ADMIN_JWT_SECRET));
                accessTokenResponseMap.put(RT_HEADER, newRefreshToken);
                admin.setRefreshToken(newRefreshToken);
            }

            accessTokenResponseMap.put(AT_HEADER, accessToken);
            return accessTokenResponseMap;

        } else {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
    }


}
