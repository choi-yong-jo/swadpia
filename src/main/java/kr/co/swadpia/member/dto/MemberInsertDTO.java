package kr.co.swadpia.member.dto;

import lombok.Data;

@Data
public class MemberInsertDTO {

	private String memberId;
	private String name;
	private String password;
	private String email;
	private String mobile;

}
