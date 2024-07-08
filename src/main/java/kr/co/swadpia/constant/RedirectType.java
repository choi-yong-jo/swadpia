package kr.co.swadpia.constant;

import lombok.Getter;

@Getter
public enum RedirectType {

    pc("PC", "pc"),
    mobile_join("Mobile Join", "mobile_join"),
    mobile_mypage("Mobile Mypage", "mobile_mypage");


    private final String name;
    private final String value;

    RedirectType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
