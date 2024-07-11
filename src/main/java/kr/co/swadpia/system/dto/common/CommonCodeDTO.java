package kr.co.swadpia.system.dto.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonCodeDTO {

	private Long id;

	private String code;

	private String codeName;

	private String description;

	private Integer sortNo;

	private Boolean useYn;

	public CommonCodeDTO(Long id, String code, String codeName, String description, Integer sortNo, Boolean useYn) {
		this.id = id;
		this.code = code;
		this.codeName = codeName;
		this.description = description;
		this.sortNo = sortNo;
		this.useYn = useYn;
	}

}
