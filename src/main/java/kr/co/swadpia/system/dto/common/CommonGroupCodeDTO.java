package kr.co.swadpia.system.dto.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonGroupCodeDTO {

	private Long groupId;

	private String groupCode;

	private String groupName;

	private String description;

	private  Boolean useYn;
}
