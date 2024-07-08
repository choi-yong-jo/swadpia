package kr.co.swadpia.constant;

import lombok.Getter;

@Getter
public enum SettlementStatus {
    purchase_complete("Purchase Complete", "purchase_complete"),
    waiting("Waiting", "waiting"),
    hold("Hold", "hold"),
    complete("Complete", "complete");


    private final String name;
    private final String value;

    SettlementStatus(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
