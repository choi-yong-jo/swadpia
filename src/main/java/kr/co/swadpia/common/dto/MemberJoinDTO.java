package kr.co.swadpia.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberJoinDTO {

	@NotBlank(message = "이메일은 필수 입력 항목입니다.")
	private String email;
	@NotBlank(message = "패스워드는 필수 입력 항목입니다.")
	private String password;
	@NotBlank(message = "패스워드 확인은 필수 입력 항목입니다.")
	private String passwordConfirm;
	@NotBlank(message = "이름은 필수 입력 항목입니다.")
	private String name;
	@NotBlank(message = "휴대폰번호는 필수 입력 항목입니다.")
	private String mobile;
//	private String birthday;
//	private String sex;
//	private String dupInfo;
//	private String connInfo;
//	private String postcode;
//	private String address1;
//	private String address2;
}
