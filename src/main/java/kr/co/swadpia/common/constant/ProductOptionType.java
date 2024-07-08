package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum ProductOptionType {

    SELECT("선택", "SELECT"),
    TEXT("입력", "TEXT"),
    FILE("업로드", "FILE");

    private final String name;
    private final String value;

    ProductOptionType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
