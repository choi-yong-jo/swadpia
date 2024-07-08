package kr.co.swadpia.constant;

import lombok.Getter;

@Getter
public enum OrderBy {

    recommend("Recommend", "recommend"),
    imminent("Imminent", "imminent"),
    latest("Latest", "latest"),
    oldest("Oldest", "oldest"),
    now_price_desc("Not Price Descending", "now_price_desc"),
    now_price_asc("Now Price Ascending", "now_price_asc"),
    start_price_desc("Start Price Descending", "start_price_desc"),
    start_price_asc("Start Price Ascending", "start_price_asc"),
    partner_id_desc("Partner ID Descending", "partner_id_desc"),
    partner_id_asc("Partner ID Ascending", "partner_id_asc");

    private final String name;
    private final String value;

    OrderBy(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
