package kr.co.swadpia.common.constant;


import lombok.Getter;

@Getter
public enum MemberAgreementType {
    AD_MARKETING("ad_marketing"),
    PRODUCT_REFUND("product_refund");

    private final String name;

    MemberAgreementType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
