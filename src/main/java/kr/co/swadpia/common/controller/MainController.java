package kr.co.swadpia.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swadpia.common.dto.SessionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "commonMain")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/common/")
public class MainController {

	@Operation(summary = "main", description = "메인화면")
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public Object main(@AuthenticationPrincipal SessionDTO sessionDTO) {

		return true;
	}
}
