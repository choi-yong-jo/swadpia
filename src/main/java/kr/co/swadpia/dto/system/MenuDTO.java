package kr.co.swadpia.dto.system;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class MenuDTO {

	@Schema(description = "메뉴ID",
		defaultValue = "1",
		name = "id",
		type = "Long",
		requiredMode = Schema.RequiredMode.AUTO)
	private Long id;

	@Schema(description = "메뉴명",
		defaultValue = "메뉴그룹관리",
		name = "menuName",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	private String menuName;

	@Schema(description = "메뉴URL",
		defaultValue = "/system/menu",
		name = "url",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	private String url;

	@Schema(description = "메뉴그룹권한 목록",
		defaultValue = "",
		name = "groupAuthorityParamDTO",
		type = "List",
		requiredMode = Schema.RequiredMode.AUTO)
	private List<MenuGroupAuthorityParam> groupAuthorityParamDTO;

	@Schema(description = "상위메뉴정보",
		defaultValue = "미구현",
		name = "parentMenuParamDTO",
		type = "MenuParamDTO",
		requiredMode = Schema.RequiredMode.AUTO)
	private MenuDTO parentMenuParamDTO;

	@Schema(description = "하위메뉴정보",
		defaultValue = "",
		name = "childrenParamDTO",
		type = "List MenuParamDTO",
		requiredMode = Schema.RequiredMode.AUTO)
	private List<MenuDTO> childrenParamDTO;
}
