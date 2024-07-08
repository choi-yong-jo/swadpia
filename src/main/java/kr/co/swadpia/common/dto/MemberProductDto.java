package kr.co.swadpia.common.dto;

import kr.co.swadpia.common.constant.SalesStatus;

public class MemberProductDto {

    private Long productId;
    private String productName;
    private String brandName;
    private String modelName;
    private String status;
    private SalesStatus salesStatus;
    private Long price;
    private Long deliveryFee;
    private Boolean isDeleted;
    private Boolean isInvisibled;
}
