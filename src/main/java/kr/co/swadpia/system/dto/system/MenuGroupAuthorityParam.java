package kr.co.swadpia.system.dto.system;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MenuGroupAuthorityParam {

	@Schema(description = "메뉴그룹권한id(권한 추가시에는 자동생성)",
		defaultValue = "1",
		name = "id",
		type = "Long",
		requiredMode = Schema.RequiredMode.AUTO)
	private Long id;

	@Schema(description = "메뉴그룹이름",
		defaultValue = "VIP",
		name = "menuGroupAuthorityName",
		type = "String",
		requiredMode = Schema.RequiredMode.REQUIRED)
	private String menuGroupAuthorityName;

	@Schema(description = "메뉴 목록",
		defaultValue = "{}",
		name = "menus",
		type = "List<MenuParamDTO>",
		requiredMode = Schema.RequiredMode.NOT_REQUIRED,
		hidden = true)
	private List<MenuParam> menus;

	@Schema(description = "그룹 유저",
		defaultValue = "미구현",
		name = "users",
		type = "List<UsersParamDTO>",
		requiredMode = Schema.RequiredMode.NOT_REQUIRED,
		hidden = true)
	private List<UsersParam> users;

	/*@Schema(description = "상품ID",
		defaultValue = "미구현",
		name = "productId",
		type = "Long",
		requiredMode = Schema.RequiredMode.AUTO)
	private List<Role> roles;*/
}
