package kr.co.swadpia.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FindMemberPasswordParamDTO {

	@NotBlank(message = "이름은 필수 입력 항목입니다.")
	private String name;
	@NotBlank(message = "아이디는 필수 입력 항목입니다.")
	private String email;
}
