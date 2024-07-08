package kr.co.swadpia.constant;

import lombok.Getter;

@Getter
public enum OrderStatus {

    none("None", "none"),
    payment_wait("결제대기중", "payment_wait"),
    payment_time_out("결제시한만료", "payment_time_out"),
    payment_complete("결제완료", "payment_complete"),
    delivery_preparing("배송준비중", "delivery_preparing"),
    delivering("배송중", "delivering"),
    delivered("배송완료", "delivered"),
    purchase_complete("구매확정", "purchase_complete"),
    purchase_cancel("구매취소", "purchase_cancel"),

    return_request("반품요청", "return_request"),
    return_complete("반품확정", "return_complete");

    private final String name;
    private final String value;

    OrderStatus(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
