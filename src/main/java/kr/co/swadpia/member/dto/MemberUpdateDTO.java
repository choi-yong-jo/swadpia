package kr.co.swadpia.member.dto;

import lombok.Data;

@Data
public class MemberUpdateDTO {

	private Long memberSeq;
	private String memberId;
	private String email;
	private String password;
	private String name;
	private String mobile;
	private String verificationCode;
	private String refreshToken;

}
