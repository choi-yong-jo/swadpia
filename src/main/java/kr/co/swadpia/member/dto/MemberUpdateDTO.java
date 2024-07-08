package kr.co.swadpia.member.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MemberUpdateDTO {


	private Long memberId;
	private String email;
	private String password;
	private String passwordConfirm;
	private String name;
	private String mobile;
	private Boolean useAvater;
	private String profileImage;
	private String birthday;
	private String sex;
	private String dupInfo;
	private String connInfo;
	private Date createdAt;
	private Boolean marketingAgreement;



}
