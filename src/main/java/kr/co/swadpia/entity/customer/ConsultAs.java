package kr.co.swadpia.entity.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.co.swadpia.entity.AuditingAt;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Setter
@Getter
public class ConsultAs extends AuditingAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("상담AS번호")
    private Long consultAsId;

    @Comment("요청구분")
    private String reqType;

    @Comment("요청상세")
    private String reqTypeSub;

    @Comment("요청사항")
    private String reqTitle;

    @Comment("요청메모")
    private String reqDetail;

    @Comment("신청자")
    private String customerId;

    @Comment("회원명")
    private String customerName;

    @Comment("회원명(상호)")
    private String companyName;

    @Comment("핸드폰")
    private String customerMobile;

    @Comment("답변자")
    private String replyAuthor;

    @Comment("진행상태")
    private String status;

    @Comment("처리상태")
    private String processStatus;

    @Comment("답변일자")
    private String replyDt;

    @Comment("소요시간")
    private String replyTime;

    @Comment("주문번호")
    private String orderNo;

    @Comment("반품")
    private String isReturn;

    @Comment("답변방법")
    private String replyType;

    @Comment("연락가능시간")
    private String contactTime;

    @Comment("답변내용")
    private String replyContent;

}