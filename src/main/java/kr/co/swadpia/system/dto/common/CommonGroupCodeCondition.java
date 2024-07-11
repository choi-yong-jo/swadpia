package kr.co.swadpia.system.dto.common;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import org.springdoc.core.annotations.ParameterObject;

@Data
@ParameterObject
public class CommonGroupCodeCondition {

	@Parameter(description = "코드", example = "00")
	String groupCode;

	@Parameter(description = "코드이름", example = "00")
	String groupName;

	@Parameter(description = "그룹코드아이디", example = "0")
	Long groupId;

}
