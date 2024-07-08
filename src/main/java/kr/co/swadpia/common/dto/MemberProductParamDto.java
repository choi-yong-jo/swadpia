package kr.co.swadpia.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.swadpia.constant.SalesStatus;
import lombok.Data;

@Data
public class MemberProductParamDto {

    @Schema(description = "상품ID",
            defaultValue = "미구현",
            name = "productId",
            type = "Long",
            requiredMode = Schema.RequiredMode.AUTO)
    private Long productId;

    @Schema(description = "상품명",
            defaultValue = "LG시그니처 가습청정기",
            name = "productName",
            type = "String")
    private String productName;

    @Schema(description = "브랜드명",
            defaultValue = "LG",
            name = "brandName",
            type = "String")
    private String brandName;

    @Schema(description = "모델명",
            defaultValue = "lg-air-signture",
            name = "modelName",
            type = "String")
    private String modelName;

    @Schema(description = "상태",
            defaultValue = "",
            name = "status",
            type = "String")
    private String status;

    @Schema(description = "판매상태",
            defaultValue = "",
            name = "salesStatus",
            type = "enum")
    private SalesStatus salesStatus;

    @Schema(description = "가격",
            defaultValue = "100000",
            name = "price",
            type = "Long")
    private Long price;

    @Schema(description = "배송비",
            defaultValue = "2500",
            name = "deliveryFee",
            type = "Long")
    private Long deliveryFee;

    @Schema(description = "삭제여부",
            defaultValue = "true",
            name = "isDeleted",
            type = "Boolean")
    private Boolean isDeleted;

    @Schema(description = "표시여부",
            defaultValue = "true",
            name = "isInvisibled",
            type = "Boolean")
    private Boolean isInvisibled;
}
