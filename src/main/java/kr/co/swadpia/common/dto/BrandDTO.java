package kr.co.swadpia.common.dto;


import lombok.Data;

@Data
public class BrandDTO {
    private Long brandId;
    private Long partnerId;
    private String brandName;
    private String description;
    private String logoImage;
}
