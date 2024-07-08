package kr.co.swadpia.common.constant;

public enum ClaimType {
    RETURN("Return", "return")
    , EXCHANGE("Exchange", "exchange")
    , CANCEL("Cancel", "cancel");

    private final String name;
    private final String value;

    ClaimType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static ClaimType fromName(String name) {
        for (ClaimType type : ClaimType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    public static ClaimType fromValue(String value) {
        for (ClaimType type : ClaimType.values()) {
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