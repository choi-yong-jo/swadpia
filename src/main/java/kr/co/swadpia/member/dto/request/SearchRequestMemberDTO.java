package kr.co.swadpia.member.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SearchRequestMemberDTO {

	@Column(length = 20)
	private String memberId;

	@Column(length = 20)
	private String teamId;

	@Column(length = 1)
	private String useYn;

	private String roles;

}
