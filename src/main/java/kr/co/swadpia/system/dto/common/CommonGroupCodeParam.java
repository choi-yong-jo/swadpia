package kr.co.swadpia.system.dto.common;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommonGroupCodeParam {

	private Long groupId;

	@NotBlank(message = "코드는 필수입력 항목입니다.")
	private String groupCode;

	@NotBlank(message = "코드명은 필수입력 항목입니다.")
	private String groupName;

	private String description;

	private Boolean useYn = true;
}
