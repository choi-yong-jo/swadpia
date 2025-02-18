package kr.co.swadpia.member.service;

import kr.co.swadpia.common.constant.ResultCode;
import kr.co.swadpia.common.dto.ResponseDTO;
import kr.co.swadpia.member.dto.request.RoleDTO;
import kr.co.swadpia.member.entity.Role;
import kr.co.swadpia.repository.jpa.member.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		roleRepository.findByUseYnOrderByRoleSeq("Y").forEach(e -> roles.add((Role) e));
		return roles;
	}


	public ResponseDTO insert(RoleDTO dto) {
		Role role = new Role();
		BeanUtils.copyProperties(dto, role);
		ResponseDTO responseDTO = validationCheck(role);
		if ("".equals(responseDTO.getResultCode()) || responseDTO.getResultCode() == null) {
			roleRepository.save(role);
			responseDTO.setResultCode(ResultCode.INSERT.getName());
			responseDTO.setMsg(ResultCode.INSERT.getValue());
		}

		return responseDTO;
	}

	public ResponseDTO update(Long roleSeq, RoleDTO dto) {
		Optional<Role> r = roleRepository.findByRoleSeq(roleSeq);
		ResponseDTO responseDTO = new ResponseDTO();
		if(r.isPresent()){
			Role role = new Role();
			BeanUtils.copyProperties(dto, role);
			role.setRoleSeq(roleSeq);
			if ("".equals(responseDTO.getResultCode()) || responseDTO.getResultCode() == null) {
				roleRepository.save(role);
				responseDTO.setResultCode(ResultCode.UPDATE.getName());
				responseDTO.setMsg(ResultCode.UPDATE.getValue());
			} else {
				responseDTO.setResultCode(ResultCode.NOT_FOUND_INFO.getName());
				responseDTO.setMsg(ResultCode.NOT_FOUND_INFO.getValue());
			}
		}

		return responseDTO;
	}

	public ResponseDTO delete(long roleSeq) {
		Optional<Role> r = roleRepository.findByRoleSeq(roleSeq);
		ResponseDTO responseDTO = new ResponseDTO();
		if(r.isPresent()){
			Role role = r.get();
			role.setUseYn("N");
			roleRepository.save(role);
			responseDTO.setResultCode(ResultCode.DELETE.getName());
			responseDTO.setMsg(ResultCode.DELETE.getValue());
		} else {
			responseDTO.setResultCode(ResultCode.NOT_FOUND_INFO.getName());
			responseDTO.setMsg(ResultCode.NOT_FOUND_INFO.getValue());
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
