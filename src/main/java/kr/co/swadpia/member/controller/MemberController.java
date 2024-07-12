package kr.co.swadpia.member.controller;

import com.querydsl.core.Tuple;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swadpia.common.constant.ResultCode;
import kr.co.swadpia.common.dto.ResponseDTO;
import kr.co.swadpia.common.service.CommonUtilService;
import kr.co.swadpia.common.utility.SHA256;
import kr.co.swadpia.member.dto.*;
import kr.co.swadpia.member.entity.Member;
import kr.co.swadpia.member.entity.MemberRole;
import kr.co.swadpia.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Tag(name = "Member")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    CommonUtilService commonUtilService;

    @Operation(summary = "회원조회")
    @GetMapping(value = "/list")
    public ResponseEntity<?> getAllmembers() {
        List<Member> member = memberService.findAll();
        ResponseDTO responseDTO = commonUtilService.selectObject(member);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "로그인")
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) throws NoSuchAlgorithmException {
        dto.setPassword(SHA256.encrypt(dto.getPassword()));
        ResponseDTO responseDTO = memberService.login(dto);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "회원상세조회")
    @GetMapping(value = "/detail")
    public ResponseEntity<?> getMember(@RequestParam("memberSeq") Long memberSeq) {
        MemberDetailDTO info = (MemberDetailDTO) memberService.getMemberDetail(memberSeq);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @Operation(summary = "회원등록")
    @PostMapping
    public ResponseEntity<?> insertMember(@RequestBody MemberDTO dto) throws NoSuchAlgorithmException {
        dto.setPassword(SHA256.encrypt(dto.getPassword()));
        ResponseDTO responseDTO = memberService.insert(dto);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "회원수정")
    @PatchMapping
    public ResponseEntity<?> updateMember(@RequestParam Long memberSeq ,@RequestBody MemberDTO dto) throws NoSuchAlgorithmException {
        dto.setPassword(SHA256.encrypt(dto.getPassword()));
        ResponseDTO responseDTO = memberService.update(memberSeq, dto);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "회원삭제")
    @DeleteMapping
    public ResponseEntity<?> deleteMember(@RequestParam("memberSeq") Long memberSeq) throws NoSuchAlgorithmException {
        ResponseDTO responseDTO = memberService.delete(memberSeq);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "회원권한조회")
    @GetMapping(value = "/mapping")
    public ResponseEntity<?> insertMemberRole(@RequestParam("memberSeq") Long memberSeq) {
        List<MemberRole> roles = memberService.mappingById(memberSeq);
        ResponseDTO responseDTO = commonUtilService.selectObject(roles);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "회원권한등록")
    @PostMapping(value = "/mapping")
    public ResponseEntity<?> insertMemberRole(@RequestBody MemberRoleDTO dto) throws NoSuchAlgorithmException {
        Optional<Member> m = memberService.findById(dto.getMemberSeq());
        ResponseDTO responseDTO = new ResponseDTO();
        if(m.isPresent()){
            responseDTO = memberService.insertMemberRole(dto);
        } else {
            responseDTO.setResultCode(ResultCode.NOT_FOUND_INFO.getName());
            responseDTO.setMsg(ResultCode.NOT_FOUND_INFO.getValue());
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "회원권한삭제")
    @DeleteMapping(value = "/mapping")
    public ResponseEntity<?> deleteMemberRole(@RequestBody MemberRoleDTO dto) throws NoSuchAlgorithmException {
        Optional<Member> m = memberService.findById(dto.getMemberSeq());
        ResponseDTO responseDTO = new ResponseDTO();
        if(m.isPresent()){
            responseDTO = memberService.deleteMemberRole(dto);
        } else {
            responseDTO.setResultCode(ResultCode.NOT_FOUND_INFO.getName());
            responseDTO.setMsg(ResultCode.NOT_FOUND_INFO.getValue());
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
