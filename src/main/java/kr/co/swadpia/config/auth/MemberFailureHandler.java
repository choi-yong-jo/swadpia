package kr.co.swadpia.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.swadpia.common.dto.ErrorResponseDTO;
import kr.co.swadpia.constant.ErrorCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Component
public class MemberFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ErrorCode.NOT_FOUND, "ID 또는 비밀번호가 일치하지 않습니다.");
        new ObjectMapper().writeValue(response.getWriter(), errorResponse);
    }
}
