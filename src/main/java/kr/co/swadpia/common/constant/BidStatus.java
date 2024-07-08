package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum BidStatus {

    before("Before", "before"),
    proceeding("Proceeding", "proceeding"),
    end("End", "end");



    private final String name;
    private final String value;

    BidStatus(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
