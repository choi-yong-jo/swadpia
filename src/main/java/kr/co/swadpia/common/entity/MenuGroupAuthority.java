package kr.co.swadpia.common.entity;

import jakarta.persistence.*;
import kr.co.swadpia.system.dto.system.MenuGroupAuthorityParam;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Data
public class MenuGroupAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_group_authority_id")
    @Comment("메뉴그룹ID")
    private Long id;

    @Comment("메뉴그룹명")
    private String menuGroupAuthorityName;

    @OneToMany(mappedBy = "menuGroupAuthority")
    private List<Menu> menus;

    /*@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rold_id")
    private Role role;*/

    /**
     * 메뉴그룹권한 엔티티 객체생성
     * @param menuGroupAuthorityParam 메뉴그룹권한 요청파라메터 정보
     * @return MenuGroupAuthority entity
     */
    public static MenuGroupAuthority of(MenuGroupAuthorityParam menuGroupAuthorityParam){
        MenuGroupAuthority menuGroupAuthority = new MenuGroupAuthority();
        menuGroupAuthority.setMenuGroupAuthorityName(menuGroupAuthorityParam.getMenuGroupAuthorityName());
        return menuGroupAuthority;
    }
}
