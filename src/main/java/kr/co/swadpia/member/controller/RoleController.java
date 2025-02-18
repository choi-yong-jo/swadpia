package kr.co.swadpia.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swadpia.common.dto.ResponseDTO;
import kr.co.swadpia.common.service.CommonUtilService;
import kr.co.swadpia.member.dto.request.RoleDTO;
import kr.co.swadpia.member.entity.Role;
import kr.co.swadpia.member.service.MemberService;
import kr.co.swadpia.member.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@Tag(name = "Role")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/role")
public class RoleController {

    @Autowired
    MemberService memberService;

    @Autowired
    RoleService roleService;

    @Autowired
    CommonUtilService commonUtilService;

    @Operation(summary = "권한조회")
    @GetMapping(value = "/list")
    public ResponseEntity<?> getRoles() {
        List<Role> list = roleService.findAll();
        ResponseDTO responseDTO = commonUtilService.selectObject(list);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "권한등록")
    @PostMapping
    public ResponseEntity<?> insertRole(@RequestBody RoleDTO dto) {
        ResponseDTO responseDTO = roleService.insert(dto);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "권한수정")
    @PatchMapping
    public ResponseEntity<?> updateRole(@RequestParam("roleSeq") Long roleSeq, @RequestBody RoleDTO dto) {
        ResponseDTO responseDTO = roleService.update(roleSeq, dto);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "권한삭제")
    @DeleteMapping
    public ResponseEntity<?> deleteRole(@RequestParam("roleSeq") Long roleSeq) throws NoSuchAlgorithmException {
        ResponseDTO responseDTO = roleService.delete(roleSeq);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
