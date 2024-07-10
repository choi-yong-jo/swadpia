package kr.co.swadpia.member.service;

import kr.co.swadpia.common.constant.ResultCode;
import kr.co.swadpia.common.dto.LoginParamDTO;
import kr.co.swadpia.common.dto.ResponseDTO;
import kr.co.swadpia.common.dto.SessionDTO;
import kr.co.swadpia.common.utility.RegexUtils;
import kr.co.swadpia.member.dto.RoleInsertDTO;
import kr.co.swadpia.member.dto.RoleUpdateDTO;
import kr.co.swadpia.member.entity.Member;
import kr.co.swadpia.member.entity.Role;
import kr.co.swadpia.repository.jpa.MemberRepository;
import kr.co.swadpia.repository.jpa.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> findAll() {
		List<Role> roles = new ArrayList<>();
		roleRepository.findAll().forEach(e -> roles.add(e));
		return roles;
	}


	public ResponseDTO insert(RoleInsertDTO dto) {
		Role role = new Role();
		BeanUtils.copyProperties(dto, role);
		ResponseDTO responseDTO = validationCheck(role);
		if ("".equals(responseDTO.getResultCode()) || responseDTO.getResultCode() == null) {
			role.setUseYn("Y");
			roleRepository.save(role);
			responseDTO.setResultCode(ResultCode.INSERT.getName());
			responseDTO.setMsg(ResultCode.INSERT.getValue());
		}

		return responseDTO;
	}

	public ResponseDTO update(RoleUpdateDTO dto) {
		Optional<Role> r = roleRepository.findById(dto.getRoleSeq());
		ResponseDTO responseDTO = new ResponseDTO();
		if(r.isPresent()){
			Role role = new Role();
			BeanUtils.copyProperties(dto, role);
			responseDTO = validationCheck(role);
			if ("".equals(responseDTO.getResultCode()) || responseDTO.getResultCode() == null) {
				role.setUseYn("Y");
				roleRepository.save(role);
				responseDTO.setResultCode(ResultCode.UPDATE.getName());
				responseDTO.setMsg(ResultCode.UPDATE.getValue());
			} else {
				responseDTO.setResultCode(ResultCode.NOT_FOUND_ROLE.getName());
				responseDTO.setMsg(ResultCode.NOT_FOUND_ROLE.getValue());
			}
		}

		return responseDTO;
	}

	public ResponseDTO delete(long roleSeq) {
		Optional<Role> r = roleRepository.findById(roleSeq);
		ResponseDTO responseDTO = new ResponseDTO();
		if(r.isPresent()){
			Role role = r.get();
			role.setUseYn("N");
			roleRepository.save(role);
			responseDTO.setResultCode(ResultCode.DELETE.getName());
			responseDTO.setMsg(ResultCode.DELETE.getValue());
		} else {
			responseDTO.setResultCode(ResultCode.NOT_FOUND_ROLE.getName());
			responseDTO.setMsg(ResultCode.NOT_FOUND_ROLE.getValue());
		}

		return responseDTO;
	}

	public ResponseDTO validationCheck(Role role) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Role> list = roleRepository.findByRoleIdAndName(role.getRoleId(), role.getName()).stream().toList();
		if (list.size() > 0) {
			responseDTO.setResultCode(ResultCode.NOT_INSERT_SAME_ROLE.getName());
			responseDTO.setMsg(ResultCode.NOT_INSERT_SAME_ROLE.getValue());
		}

		return responseDTO;
	}

}
