package kr.co.swadpia.member.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RoleUpdateDTO {

	private Long roleSeq;
	private String roleId;
	private String name;
	private String description;

}
