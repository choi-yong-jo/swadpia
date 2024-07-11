package kr.co.swadpia.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swadpia.common.dto.SessionDTO;
import kr.co.swadpia.system.dto.system.MenuGroupAuthorityDTO;
import kr.co.swadpia.system.dto.system.MenuGroupAuthorityParam;
import kr.co.swadpia.system.service.MenuGroupAuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "System")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v0/system")
public class MenuGroupAuthorityController {

	private final MenuGroupAuthorityService menuGroupAuthorityService;

	@Operation(summary = "메뉴그룹권한 등록", description = "메뉴그룹권한 등록")
	@PostMapping(value = "/menu-group-authority")
	public MenuGroupAuthorityDTO createMenuGroup(@AuthenticationPrincipal SessionDTO sessionDTO,
		@RequestBody MenuGroupAuthorityParam menuGroupAuthorityParamDTO) {

		return menuGroupAuthorityService.insertMenuGroupAuthority(sessionDTO, menuGroupAuthorityParamDTO);
	}

	@Operation(summary = "메뉴그룹권한 수정", description = "메뉴그룹권한 수정")
	@PutMapping(value = "/menu-group-authority")
	public MenuGroupAuthorityDTO modifyMenuGroup(@AuthenticationPrincipal SessionDTO sessionDTO,
		@RequestBody MenuGroupAuthorityParam menuGroupAuthorityParamDTO) {

		return menuGroupAuthorityService.modifyMenuGroupAuthority(sessionDTO, menuGroupAuthorityParamDTO);
	}

	@Operation(summary = "메뉴그룹권한 수정", description = "메뉴그룹권한 수정")
	@GetMapping(value = "/menu-group-authority/{id}")
	public MenuGroupAuthorityDTO modifyMenuGroup(@AuthenticationPrincipal SessionDTO sessionDTO,
		@PathVariable String id) {
		return menuGroupAuthorityService.getMenuGroupAuthority(sessionDTO, id);
	}
}
