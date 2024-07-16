package kr.co.swadpia.member.dto.response;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.HashMap;

@Data
public class MemberDetailDTO extends HashMap<String, String> {

	@Column(length = 20)
	private String memberId;

	@Column(length = 20)
	private String teamNm;

	@Column(length = 20)
	private String name;

	@Column(length = 50)
	private String email;

	@Column(length = 20)
	private String mobile;

	private String roles;

}
