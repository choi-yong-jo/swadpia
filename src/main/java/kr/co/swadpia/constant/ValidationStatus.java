package kr.co.swadpia.constant;

import lombok.Getter;

@Getter
public enum ValidationStatus {

    before("Before", "before"),
    pass("Pass", "pass"),
    error("Error", "error");
    private final String name;
    private final String value;

    ValidationStatus(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
