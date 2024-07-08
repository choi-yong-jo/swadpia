package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum OrderByEvent {

    event_id_desc("Event Id Desc", "event_id_desc"),
    seller_count_desc("Seller Count Desc", "seller_count_desc");

    private final String name;
    private final String value;

    OrderByEvent(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
