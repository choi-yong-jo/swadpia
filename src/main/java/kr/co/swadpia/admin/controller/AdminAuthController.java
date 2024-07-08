package kr.co.swadpia.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.swadpia.admin.dto.AdminSessionDTO;
import kr.co.swadpia.admin.service.AdminAuthService;
import kr.co.swadpia.common.dto.LoginParamDTO;
import kr.co.swadpia.common.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static kr.co.swadpia.config.auth.JwtConstants.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Tag(name = "adminAuth")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/admin/auth")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;


    @Operation(summary = "login", description = "로그인")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public TokenDTO login(@RequestBody @Valid LoginParamDTO param) throws Exception {

        return new TokenDTO();

    }
    @Operation(summary = "logout", description = "로그아웃")
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(HttpSession session){
        return "{result:'logoutok'}";
    }


    @GetMapping("/refresh")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Map<String,String>> refresh(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_HEADER_PREFIX)) {
            throw new RuntimeException("인증이 필요합니다.");
        }
        String refreshToken = authorizationHeader.substring(TOKEN_HEADER_PREFIX.length());
        Map<String, String> tokens = adminAuthService.refresh(refreshToken);
        response.setHeader(AT_HEADER, tokens.get(AT_HEADER));
        if (tokens.get(RT_HEADER) != null) {
            response.setHeader(RT_HEADER, tokens.get(RT_HEADER));
        }
        return ResponseEntity.ok(tokens);
    }
    @Operation(summary = "test", description = "인증테스트")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public AdminSessionDTO myinfio(
            @AuthenticationPrincipal AdminSessionDTO adminSessionDTO) {

        return adminSessionDTO;

    }


}
