package kr.co.swadpia.entity.board;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAtByCU;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
public class BoardAttachment extends AuditingAtByCU {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attachment_id")
	@Comment("게시판 첨부파일 ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "board_id", nullable = false)
	@Comment("게시판 ID")
	private Board board;

	@Comment("첨부파일명")
	private String fileName;

	@Comment("확장자")
	private String fileExt;

	@Comment("저장 경로")
	private String filePath;

	@Comment("원본 파일명")
	private String fileOriginalName;

	@Column(length = 50)
	@Comment("파일 타입 (이미지, 워드) 등")
	private String fileType;

	@Comment("파일크기")
	private Long fileSize;
}
