package kr.co.swadpia.constant;

import lombok.Getter;

@Getter
public enum PartnerMobileType {

    operator_manager("Operator Manager", "operator_manager"),
    payment_manager("Payment Manager", "payment_manager");



    private final String name;
    private final String value;

    PartnerMobileType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
