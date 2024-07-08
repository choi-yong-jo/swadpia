package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum OrderProductStatus {

    payment_complete("Payment Complete", "결제 완료"),
    delivery_ready("Delivery Ready", "배송준비 중"),
    delivery_Ing("Delivery Ing", "배송 중"),
    delivery_complete("Delivery Complete", "배송 완료"),
    cancel_request("Cancel Request", "주문취소 요청"),
    cancel_complete("Cancel Complete", "주문취소 완료"),
    return_request("Return Request", "반품 신청"),
    return_ing("Return Ing", "반품 중"),
    return_complete("Return Complete", "반품 완료"),
    exchange_request("Exchange Request", "교환 신청"),
    exchange_ing("Exchange Ing", "교환 중"),
    exchange_complete("Exchange Complete", "교환 완료")
    ;

    private final String name;
    private final String value;

    OrderProductStatus(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static OrderProductStatus fromName(String name) {
        for (OrderProductStatus type : OrderProductStatus.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    public static OrderProductStatus fromValue(String value) {
        for (OrderProductStatus type : OrderProductStatus.values()) {
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
