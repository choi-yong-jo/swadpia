package kr.co.swadpia.constant;

import lombok.Getter;

@Getter
public enum ClaimStatus {

    cancel_request("Cancel Request", "cancel_request"),
    cancel_complete("Cancel Complete", "cancel_complete"),

    exchange_request("Exchange Request", "exchange_request"),
    exchange_complete("Exchange Complete", "exchange_complete"),

    return_request("Return Request", "return_request"),
    return_complete("Return Complete ", "return_complete");

    private final String name;
    private final String value;

    ClaimStatus(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static ClaimStatus fromName(String name) {
        for (ClaimStatus type : ClaimStatus.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    public static ClaimStatus fromValue(String value) {
        for (ClaimStatus type : ClaimStatus.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }

    // Getter 메소드
    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
