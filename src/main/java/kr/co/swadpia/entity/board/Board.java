package kr.co.swadpia.entity.board;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAtByCU;
import kr.co.swadpia.entity.personnel.Employee;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Getter
@Setter
public class Board extends AuditingAtByCU {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("게시판 ID")
	private Long boardId;

	@Column(nullable = false)
	@Comment("게시판 코드(공지, 자유, 기타 구분코드)")
	private String boardCode;

	@Column(nullable = false)
	@Comment("게시판 제목")
	private String title;
	
	@Column(nullable = false)
	@Comment("게시판 내용")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@Comment("게시 사용자 ID")
	private Employee employee;

	@OneToMany(mappedBy = "board")
	List<BoardAttachment> boardAttachments;

	@OneToMany(mappedBy = "board")
	List<BoardComment> boardComments;
}
