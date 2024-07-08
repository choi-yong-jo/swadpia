package kr.co.swadpia.entity.board;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAtByCU;
import kr.co.swadpia.entity.personnel.Employee;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
public class BoardComment extends AuditingAtByCU {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("게시판 댓글 ID")
    private Long commentId;
    
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    @Comment("게시판 ID")
    private Board board;

    @Comment("댓글 내용")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("작성자 ID")
    private Employee employee;

}