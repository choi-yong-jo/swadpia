package kr.co.swadpia.member.dto;

import lombok.Data;

@Data
public class MemberInsertDTO {

	private String email;
	private String password;
	private String name;
	private String mobile;

}
