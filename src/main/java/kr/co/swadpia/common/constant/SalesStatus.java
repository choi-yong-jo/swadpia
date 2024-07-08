package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum SalesStatus {

    invisible("Invisible", "invisible"),
    display("Display", "display"),
    sale("Sale", "sale"),
    bid_complete("Bid Complete", "bid_complete"),
    sold_out("Sold Out", "sold_out"),
    time_out("Time Out", "time_out"),
    bid_payment_time_out("Bid Payment Time Out", "bid_payment_time_out"),
    bid_payment_cancel("Bid Payment Cancel", "bid_payment_cancel");


    private final String name;
    private final String value;

    SalesStatus(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
