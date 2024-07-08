package kr.co.swadpia.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swadpia.common.dto.SessionDTO;
import kr.co.swadpia.member.dto.MemberUpdateDTO;
import kr.co.swadpia.member.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "myPageController")
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/api/member/")
public class MyPageController {

    private final MyPageService myPageService;
    @Operation(summary = "회원정보 조회", description = "회원정보 조회")
    @RequestMapping(value = "/myinfo", method = RequestMethod.GET)
    public Object myinfio(@AuthenticationPrincipal SessionDTO sessionDTO) {

        MemberUpdateDTO result = myPageService.myinfio(sessionDTO);
        return myPageService.myinfio(sessionDTO); 

    }


}
