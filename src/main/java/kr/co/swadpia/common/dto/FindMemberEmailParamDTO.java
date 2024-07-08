package kr.co.swadpia.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FindMemberEmailParamDTO {

	@NotBlank(message = "이름은 필수 입력 항목입니다.")
	private String name;
	@NotBlank(message = "휴대폰은 필수 입력 항목입니다.")
	private String mobile;
}
