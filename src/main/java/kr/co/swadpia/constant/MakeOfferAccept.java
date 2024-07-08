package kr.co.swadpia.constant;

import lombok.Getter;

@Getter
public enum MakeOfferAccept {

    request("Request", "request"),
    accept("Accept", "accept"),
    deny("Deny","deny"),
    cancel("Cancel","cancel");

    private final String name;
    private final String value;

    MakeOfferAccept(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
