package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum BannerType {

    main("Main", "main"),
    sub("Sub", "sub"),
    mobile("Mobile", "mobile");
    private final String name;
    private final String value;

    BannerType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
