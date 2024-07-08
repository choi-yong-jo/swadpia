package kr.co.swadpia.dto.system;

import java.util.List;

import lombok.Data;

@Data
public class MenuGroupAuthorityDTO {

	private Long id;

	private String menuGroupAuthorityName;

	private List<MenuParam> menus;

	private List<UsersParam> users;

	/*@Schema(description = "상품ID",
		defaultValue = "미구현",
		name = "productId",
		type = "Long",
		requiredMode = Schema.RequiredMode.AUTO)
	private List<Role> roles;*/
}
