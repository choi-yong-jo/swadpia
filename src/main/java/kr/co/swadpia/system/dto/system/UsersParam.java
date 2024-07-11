package kr.co.swadpia.system.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

public class UsersParam {

	@Schema(description = "유저ID",
		defaultValue = "1",
		name = "id",
		type = "Long",
		requiredMode = Schema.RequiredMode.AUTO)
	private Long id;

	@Schema(description = "사번",
		defaultValue = "(TBD)",
		name = "employeeNo",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	private String employeeNo;

	@Schema(description = "이메일",
		defaultValue = "admin@swadpia.com",
		name = "email",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	@Email(message = "이메일 형식이 옳바르지 않습니다.")
	private String email;

	@Schema(description = "비밀번호",
		defaultValue = "swtemp",
		name = "password",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	private String password;

	@Schema(description = "이름",
		defaultValue = "성원",
		name = "name",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	private String name;

	@Schema(description = "휴대폰 번호",
		defaultValue = "미구현",
		name = "mobile",
		type = "Long",
		requiredMode = Schema.RequiredMode.AUTO)
	private String mobile;

	@Schema(description = "인증코드",
		defaultValue = "(TBD)",
		name = "verificationCode",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	private String verificationCode;
}
