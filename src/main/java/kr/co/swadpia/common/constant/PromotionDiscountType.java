package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum PromotionDiscountType {

    Fixed("Fixed Amount Discount", "정액"),
    Percentage("Percentage Discount", "정률")
    ;

    private final String name;
    private final String value;

    PromotionDiscountType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
