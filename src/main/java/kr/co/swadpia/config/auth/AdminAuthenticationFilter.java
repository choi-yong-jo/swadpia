package kr.co.swadpia.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Map;


@Slf4j
public class AdminAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AdminAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> jsonBody = objectMapper.readValue(request.getInputStream(), Map.class);
            String email = jsonBody.get("email");
            String password = jsonBody.get("password");
            log.error("Provider - authenticate() : email = " + email);
            log.error("Provider - authenticate() : password = " + password);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
            return authenticationManager.authenticate(token);
        } catch (Exception e) {
            log.error("Provider - authenticate() : " + e.getMessage());
            return authenticationManager.authenticate(null);
        }

    }
}
