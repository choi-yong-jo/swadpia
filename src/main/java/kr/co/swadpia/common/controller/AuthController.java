package kr.co.swadpia.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.swadpia.common.dto.*;
import kr.co.swadpia.common.service.AuthService;
import kr.co.swadpia.config.exception.CustomException;
import kr.co.swadpia.common.constant.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static kr.co.swadpia.config.auth.JwtConstants.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@Slf4j
@Tag(name = "memberAuth")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/common/")
public class AuthController {


    private final AuthService authService;

    @Value("${swadpia.admin.profile}")
    public String profile;


    private final RedisTemplate<String, HashMap<String,String>> redisHashmapTemplate;

    @Operation(summary = "login", description = "로그인")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public TokenDTO login(@RequestBody @Valid LoginParamDTO param) throws Exception {

        return new TokenDTO();

    }

    @Operation(summary = "join", description = "회원 가입")
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public Boolean join(@RequestBody @Valid MemberJoinDTO param) throws Exception {

        return authService.join(param);

    }
    @GetMapping("/refresh")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Map<String,String>> refresh(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_HEADER_PREFIX)) {
            throw new RuntimeException("인증이 필요합니다.");
        }
        String refreshToken = authorizationHeader.substring(TOKEN_HEADER_PREFIX.length());
        Map<String, String> tokens = authService.refresh(refreshToken);
        response.setHeader(AT_HEADER, tokens.get(AT_HEADER));
        if (tokens.get(RT_HEADER) != null) {
            response.setHeader(RT_HEADER, tokens.get(RT_HEADER));
        }
        return ResponseEntity.ok(tokens);
    }

    @Operation(summary = "signout", description = "회원 탈퇴")
    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    public Boolean signoutMember(@Parameter(hidden = true) SessionDTO sessionDTO) throws Exception {
        return authService.signout(sessionDTO);
    }


    @Operation(summary = "findEmail", description = "이메일찾기")
    @RequestMapping(value = "/find-email", method = RequestMethod.POST)
    public FindMemberEmailDTO findEmail(@RequestBody @Valid FindMemberEmailParamDTO param) throws Exception {


        return authService.findMemberEmail(param.getName(), param.getMobile());

    }

    @Operation(summary = "findPassword", description = "비밀번호찾기")
    @RequestMapping(value = "/find-password", method = RequestMethod.POST)
    public Boolean findEmail(@RequestBody @Valid FindMemberPasswordParamDTO param) throws Exception {

        return authService.findMemberPassword(param.getEmail(), param.getName());


    }

    @Operation(summary = "checkMobile", description = "휴대폰 중복 체크")
    @RequestMapping(value = "/check-mobile", method = RequestMethod.POST)
    public  Boolean checkMobile(@RequestParam String mobile) {
        if (authService.checkMobile(mobile)) {
            return true;
        } else {
            throw new CustomException(ErrorCode.DUPLICATED_MOBILE, "이미 가입된 휴대폰 번호입니다.");
        }
    }

    @Operation(summary = "resetPassword", description = "비번번호 재설정")
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public  Boolean resetPassword(@RequestBody @Valid ResetPasswordParamDTO param) throws Exception {
        if (authService.resestPassword(param.getPassword(), param.getMemberId())) {
            return true;
        } else {
            throw new CustomException(ErrorCode.FAIL, "비밀번호 재설정에 실패했습니다.");
        }
    }

    @Operation(summary = "checkEmail", description = "이메일 중복 체크")
    @RequestMapping(value = "/check-email", method = RequestMethod.POST)
    public Boolean checkEmail(@RequestParam String email) {
        if (authService.checkEmail(email)) {
            return true;
        } else {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL, "이미 가입된 이메일입니다.");
        }
    }


    @Operation(summary = "logout", description = "로그아웃")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        return "{result:'logoutok'}";
    }




    // 2024.05.16 작업 시작
    @Operation(summary = "identification-info", description = "실명인증 정보")
    @RequestMapping(value = "/identification-info", method = RequestMethod.GET)
    public Object identificationInfo(HttpSession session) {
        return true;
    }
}
