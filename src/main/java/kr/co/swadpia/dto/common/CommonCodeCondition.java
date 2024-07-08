package kr.co.swadpia.dto.common;

import org.springdoc.core.annotations.ParameterObject;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
@ParameterObject
public class CommonCodeCondition {

	@Parameter(description = "코드", example = "00")
	String code;

	@Parameter(description = "코드이름", example = "00")
	String codeName;

	@Parameter(description = "그룹코드아이디", example = "0")
	Long groupId;

}
