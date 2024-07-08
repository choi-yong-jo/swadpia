package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum DashboardTerm {

    all("All", "all"),
    this_month("This Month", "this_month"),
    last_month("Last Month", "last_month"),
    two_month_ago("Two Month Ago", "two_month_ago");
    private final String name;
    private final String value;

    DashboardTerm(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
