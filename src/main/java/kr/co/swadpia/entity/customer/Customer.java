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
public class Customer extends AuditingAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원번호")
    private Long customerNo;

    @Comment("회원아이디")
    private String customerId;

    @Comment("회원비밀번호")
    private String customerPassword;

    @Comment("회원이름")
    private String customerName;

    @Comment("회원명(상호)")
    private String companyName;

    @Comment("회원등급")
    private String customerGrade;

    @Comment("회원바코드")
    private String customerBarcode;

    @Comment("회원유형")
    private String customerType;

    @Comment("회원상태")
    private String customerState;

    @Comment("핸드폰")
    private String customerMobile;

    @Comment("전화번호")
    private String customerTelNo;

    @Comment("이메일")
    private String customerEmail;

    @Comment("사이트코드")
    private String siteCd;

    @Comment("해외배송여부")
    private String overseasDeliveryYn;

    @Comment("회원파일등급")
    private String customerFileGrade;

    @Comment("sms수신여부")
    private String smsReceiveYn;

    @Comment("이메일수신여부")
    private String emailReceiveYn;

    @Comment("홈페이지")
    private String homepageUrl;

    @Comment("우편번호")
    private String postNo;

    @Comment("주소")
    private String address1;

    @Comment("상세 주소")
    private String address2;

    @Comment("도로명 주소")
    private String streetAddress1;

    @Comment("도로명 상세 주소")
    private String streetAddress2;

    @Comment("가입일")
    private String joinDt;

    @Comment("가입아이피")
    private String joinIp;

    @Comment("수정아이피")
    private String updateIp;

    @Comment("가결제사용")
    private String preliminaryPayYn;

    @Comment("애드피아몰광고 수신 여부")
    private String adpiaMallReceiveYn;

    @Comment("스테이락광고 수신 여부")
    private String stayrakReceiveYn;

    @Comment("저작물홍보 수신 여부")
    private String contentReceiveYn;

    @Comment("메모")
    private String memo;

    @Comment("탈퇴사유")
    private String withdrawalReason;

    @Comment("탈퇴메모")
    private String withdrawalMemo;

}