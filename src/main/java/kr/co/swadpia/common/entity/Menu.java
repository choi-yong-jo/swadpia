package kr.co.swadpia.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Data
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    @Comment("메뉴ID")
    private Long id;

    @Comment("메뉴명")
    private String menuName;

    @Comment("메뉴URL")
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_group_authority_id",foreignKey = @ForeignKey(name="fk_menu_group_authority__id"))
    private MenuGroupAuthority menuGroupAuthority;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @Comment("상위 메뉴 ID")
    private Menu parent;

    @OneToMany(mappedBy = "parent")
    private List<Menu> children;
}
