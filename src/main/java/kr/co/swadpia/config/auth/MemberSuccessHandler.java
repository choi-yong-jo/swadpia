package kr.co.swadpia.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.swadpia.common.dto.SessionDTO;
import kr.co.swadpia.common.dto.TokenDTO;
import kr.co.swadpia.common.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

import static kr.co.swadpia.config.auth.JwtConstants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RequiredArgsConstructor
@Component
public class MemberSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthService accountService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SessionDTO sessionDTO = (SessionDTO) authentication.getPrincipal();

        String accessToken = JWT.create()
                .withSubject(sessionDTO.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + AT_EXP_TIME))
                .withClaim("roles", sessionDTO.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .sign(Algorithm.HMAC256(MEMBER_JWT_SECRET));
        String refreshToken = JWT.create()
                .withSubject(sessionDTO.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + RT_EXP_TIME))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .sign(Algorithm.HMAC256(MEMBER_JWT_SECRET));

        // Refresh Token DB에 저장
        accountService.updateRefreshToken(sessionDTO.getUsername(), refreshToken);

        // Access Token , Refresh Token 프론트 단에 Response Header로 전달
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.setHeader(AT_HEADER, accessToken);
        response.setHeader(RT_HEADER, refreshToken);

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setAccessToken(accessToken);
        tokenDTO.setRefreshToken(refreshToken);
        new ObjectMapper().writeValue(response.getWriter(), tokenDTO );
    }
}
