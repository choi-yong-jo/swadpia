package kr.co.swadpia.system.dto.common;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommonCodeParam {

	private Long id;

	private Long groupId;

	private Long parentCodeId;

	@NotBlank(message = "코드는 필수입력 항목입니다.")
	private String code;

	@NotBlank(message = "코드명은 필수입력 항목입니다.")
	private String codeName;

	private String description;

	private Integer depth;

	private Boolean useYn = true;
}
