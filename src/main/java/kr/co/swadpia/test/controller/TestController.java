package kr.co.swadpia.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swadpia.test.dto.ResponseDTO;
import kr.co.swadpia.entity.Member;
import kr.co.swadpia.test.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "testAPI")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/test")
public class TestController {

    @Autowired
    MemberService memberService;

    @Operation(summary = "memberList", description = "회원조회")
    @GetMapping(value = "/member/list")
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

}
