package kr.co.swadpia.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.swadpia.common.dto.SessionDTO;
import kr.co.swadpia.dto.common.CommonCodeCondition;
import kr.co.swadpia.dto.common.CommonCodeDTO;
import kr.co.swadpia.dto.common.CommonCodeParam;
import kr.co.swadpia.system.service.CommonCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "commonCode")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v0/common/")
public class CommonCodeController {

	private final CommonCodeService commonCodeService;

	@Operation(summary = "code", description = "코드조회")
	@RequestMapping(value = "/code", method = RequestMethod.GET)
	public CommonCodeDTO getCommonCode(@AuthenticationPrincipal SessionDTO sessionDTO, @RequestParam Long codeId) {

		return commonCodeService.getCommonCode(codeId);
	}

	@Operation(summary = "codeList", description = "전체코드조회")
	@RequestMapping(value = "/code-list", method = RequestMethod.POST)
	public List<CommonCodeDTO> searchCommonCodeList(@AuthenticationPrincipal SessionDTO sessionDTO,
										CommonCodeCondition commonCodeCondition) {

		return commonCodeService.searchCommonCodeList(commonCodeCondition);
	}

	@Operation(summary = "code", description = "코드등록")
	@RequestMapping(value = "/code-add", method = RequestMethod.POST)
	public Object insertCommonCode(@AuthenticationPrincipal SessionDTO sessionDTO, @RequestBody @Valid CommonCodeParam commonCodeParam) {

		commonCodeService.insertCommonCode(commonCodeParam);

		return true;
	}

	@Operation(summary = "code", description = "코드삭제")
	@RequestMapping(value = "/code-delete", method = RequestMethod.DELETE)
	public Object removeCommonCode(@AuthenticationPrincipal SessionDTO sessionDTO, @RequestParam Long codeId) {

		commonCodeService.removeCommonCode(codeId);

		return true;
	}

	@Operation(summary = "code", description = "코드수정")
	@RequestMapping(value = "/code-modify", method = RequestMethod.PUT)
	public Object modifyCommonCode(@AuthenticationPrincipal SessionDTO sessionDTO, @RequestBody @Valid CommonCodeParam commonCodeParam) {

		commonCodeService.modifyCommonCode(commonCodeParam);

		return true;
	}
}