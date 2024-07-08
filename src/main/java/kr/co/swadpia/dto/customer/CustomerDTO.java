package kr.co.swadpia.dto.customer;

import lombok.Data;

@Data
public class CustomerDTO {

    private Long customerNo;
    private String customerId;
    private String customerPassword;
    private String customerName;
    private String companyName;
    private String customerGrade;
    private String customerBarcode;
    private String customerType;
    private String customerState;
    private String customerMobile;
    private String customerTelNo;
    private String customerEmail;
    private String siteCd;
    private String overseasDeliveryYn;
    private String customerFileGrade;
    private String smsReceiveYn;
    private String emailReceiveYn;
    private String homepageUrl;
    private String postNo;
    private String address1;
    private String address2;
    private String streetAddress1;
    private String streetAddress2;
    private String joinDt;
    private String joinIp;
    private String updateIp;
    private String prePayYn;
    private String adpiaMallReceiveYn;
    private String stayrakReceiveYn;
    private String ctntReceiveYn;
    private String memo;
    private String withdrawalReason;
    private String withdrawalMemo;

    // @QueryProjection
    // public CustomerDTO(String customerName, String customerEmail, String companyName) {
    //     this.customerName = customerName;
    //     this.customerEmail = customerEmail;
    //     this.companyName = companyName;
    // }
}
