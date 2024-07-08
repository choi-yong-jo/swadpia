package kr.co.swadpia.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swadpia.common.dto.ResponseDTO;
import kr.co.swadpia.common.dto.SessionDTO;
import kr.co.swadpia.member.entity.Member;
import kr.co.swadpia.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "testAPI")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/member/")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Operation(summary = "memberList", description = "회원조회")
    @GetMapping(value = "/list")
    public ResponseEntity<?> getAllmembers() {
        List<Member> member = memberService.findAll();

        ResponseDTO responseDTO = new ResponseDTO();
        if (member == null) {
            responseDTO.setMsg("조회된 데이터가 없습니다.");
        } else {
            responseDTO.setRes(member);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "회원정보 조회", description = "회원정보 조회")
    @GetMapping(value = "/myInfo")
    public Object myInfo(@AuthenticationPrincipal SessionDTO sessionDTO) {
        return memberService.myInfo(sessionDTO);
    }

}
