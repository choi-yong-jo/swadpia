package kr.co.swadpia.dto.common;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonCodeDTO {

	private Long id;

	private String code;

	private String codeName;

	private String description;

	private Integer depth;

	private Long parentCodeId;

	private Boolean useYn;

	private List<CommonCodeDTO> children;

	public CommonCodeDTO(Long id, String code, String codeName, String description, Integer depth, Long parentCodeId, Boolean useYn, List<CommonCodeDTO> children) {
		this.id = id;
		this.code = code;
		this.codeName = codeName;
		this.description = description;
		this.depth = depth;
		this.parentCodeId = parentCodeId;
		this.useYn = useYn;
		this.children = children;
	}

}
