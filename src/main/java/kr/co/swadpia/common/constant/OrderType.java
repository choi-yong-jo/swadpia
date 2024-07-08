package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum OrderType {

    bid("Bid", "bid"),
    buynow("BuyNow", "buynow");

    private final String name;
    private final String value;

    OrderType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
