package kr.co.swadpia.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPasswordParamDTO {

	@NotBlank(message = "패스워드는 필수 입력 항목입니다.")
	private String password;
	@NotBlank(message = "아이디는 필수 입력 항목입니다.")
	private String verificationCode;
}
