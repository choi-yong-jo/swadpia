package kr.co.swadpia.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.swadpia.common.dto.SessionDTO;
import kr.co.swadpia.dto.common.CommonGroupCodeCondition;
import kr.co.swadpia.dto.common.CommonGroupCodeDTO;
import kr.co.swadpia.dto.common.CommonGroupCodeParam;
import kr.co.swadpia.system.service.CommonGroupCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "commonGroupCode")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v0/common/")
public class CommonGroupCodeController {

	private final CommonGroupCodeService commonGroupCodeService;

	@Operation(summary = "code", description = "코드그룹조회")
	@RequestMapping(value = "/group-code", method = RequestMethod.GET)
	public CommonGroupCodeDTO getCommonGroupCode(@AuthenticationPrincipal SessionDTO sessionDTO, @RequestParam Long groupCodeId) {

		return commonGroupCodeService.getCommonGroupCode(groupCodeId);
	}

	@Operation(summary = "codeList", description = "전체코드그룹조회")
	@RequestMapping(value = "/group-code-list", method = RequestMethod.POST)
	public List<CommonGroupCodeDTO> searchCommonGroupCodeList(@AuthenticationPrincipal SessionDTO sessionDTO, CommonGroupCodeCondition commonGroupCodeCondition) {

		return commonGroupCodeService.searchCommonGroupCodeList(commonGroupCodeCondition);
	}

	@Operation(summary = "code", description = "코드그룹등록")
	@RequestMapping(value = "/group-code-add", method = RequestMethod.POST)
	public Object insertCommonGroupCode(@AuthenticationPrincipal SessionDTO sessionDTO, @RequestBody @Valid CommonGroupCodeParam commonGroupCodeParam) {

		commonGroupCodeService.insertCommonGroupCode(commonGroupCodeParam);

		return true;
	}

	@Operation(summary = "code", description = "코드그룹삭제")
	@RequestMapping(value = "/group-code-delete", method = RequestMethod.DELETE)
	public Object removeCommonGroupCode(@AuthenticationPrincipal SessionDTO sessionDTO, @RequestParam Long groupCodeId) {

		commonGroupCodeService.removeCommonGroupCode(groupCodeId);

		return true;
	}

	@Operation(summary = "code", description = "코드그룹수정")
	@RequestMapping(value = "/group-code-modify", method = RequestMethod.PUT)
	public Object modifyCommonGroupCode(@AuthenticationPrincipal SessionDTO sessionDTO, @RequestBody @Valid CommonGroupCodeParam commonGroupCodeParam) {

		commonGroupCodeService.modifyCommonGroupCode(commonGroupCodeParam);

		return true;
	}
}