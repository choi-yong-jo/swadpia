package kr.co.swadpia.member.dto.request;

import jakarta.persistence.Column;
import kr.co.swadpia.member.entity.MemberRole;
import kr.co.swadpia.member.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class MemberDTO {

	@Column(length = 20)
	private String memberId;

	@Column(length = 20)
	private String teamId;

	@Column(length = 20)
	private String name;

	private String password;

	@Column(length = 50)
	private String email;

	@Column(length = 20)
	private String mobile;

	private String roles;

//	private List<Role> roles;

}
