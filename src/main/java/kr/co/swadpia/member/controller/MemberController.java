package kr.co.swadpia.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swadpia.common.constant.ResultCode;
import kr.co.swadpia.common.dto.ResponseDTO;
import kr.co.swadpia.common.dto.SessionDTO;
import kr.co.swadpia.member.dto.MemberInsertDTO;
import kr.co.swadpia.member.dto.MemberUpdateDTO;
import kr.co.swadpia.member.entity.Member;
import kr.co.swadpia.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Operation(summary = "회원조회")
    @GetMapping(value = "/list")
    public ResponseEntity<?> getAllmembers() {
        List<Member> member = memberService.findAll();
        ResponseDTO responseDTO = new ResponseDTO();
        HttpStatus status;
        if (member == null) {
            responseDTO.setResultCode(ResultCode.NOT_FOUND.getName());
            responseDTO.setMsg(ResultCode.NOT_FOUND.getValue());
        } else {
            responseDTO.setRes(member);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "회원등록")
    @PostMapping(value = "/insert")
    public ResponseEntity<?> saveMember(@RequestBody MemberInsertDTO dto) {
        ResponseDTO responseDTO = new ResponseDTO();
        Member member = memberService.insert(dto);
        if (member != null) {
            responseDTO.setResultCode(ResultCode.INSERT.getName());
            responseDTO.setMsg(ResultCode.INSERT.getValue());
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "회원수정")
    @PutMapping(value = "/update")
    public ResponseEntity<?> updateMember(@RequestParam("mbrNo") Long mbrNo, @RequestBody MemberUpdateDTO dto) {
        ResponseDTO responseDTO = new ResponseDTO();
        dto.setMemberId(mbrNo);
        if (memberService.updateById(dto)) {
            responseDTO.setResultCode(ResultCode.UPDATE.getName());
            responseDTO.setMsg(ResultCode.UPDATE.getValue());
        } else {
            responseDTO.setResultCode(ResultCode.FAIL_UPDATE.getName());
            responseDTO.setMsg(ResultCode.FAIL_UPDATE.getValue());
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

//    @Operation(summary = "회원정보 조회", description = "회원정보 조회")
//    @GetMapping(value = "/myInfo")
//    public Object myInfo(@AuthenticationPrincipal SessionDTO sessionDTO) {
//        return memberService.myInfo(sessionDTO);
//    }

}
