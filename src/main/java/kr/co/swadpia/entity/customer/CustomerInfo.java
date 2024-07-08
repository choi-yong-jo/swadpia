package kr.co.swadpia.entity.customer;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAt;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Setter
@Getter
public class CustomerInfo extends AuditingAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원추가정보번호")
    private Long customerInfoId;

    @OneToOne
    @JoinColumn(name = "customer_no")
    @Comment("회원번호")
    private Customer customer;

    /** 기업 회원 **/
    @Comment("회사명(상호,회원명)")
    private String companyName;

    @Comment("사업자번호")
    private String businessNumber;

    @Comment("업종/업태")
    private String industry;

    @Comment("사업자명(CEO명)")
    private String ceoName;

    @Comment("회사전화번호")
    private String companyTelNo;

    @Comment("홈페이지")
    private String companyUrl;

    @Comment("회계담당자명")
    private String paymentManagerName;

    @Comment("회계담당연락처")
    private String paymentManagerPhone;

    @Comment("회계담당이메일")
    private String paymentManagerEmail;

    @Comment("작업담당자명")
    private String workManagerName;

    @Comment("메모")
    private String memo;

    /** 개인 회원 **/
    @Comment("페이스북계정")
    private String facebookAccount;

    @Comment("구글계정")
    private String googleAccount;

    @Comment("카카오계정")
    private String kkaoAccount;

    @Comment("네이버계정")
    private String naverAccount;

}