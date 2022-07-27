package com.homework.idus.web.v1.admin.order.command;

import com.homework.idus.core.order.query.Order;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderResponse {
    private String orderNo;
    private Long userNo;
    private String productName;
    private LocalDateTime orderAt;

    public OrderResponse(Order order) {
        this.orderNo = order.getOrderNo();
        this.userNo = order.getUser().getUserNo();
        this.productName = order.getProductName();
        this.orderAt = order.getOrderAt();
    }
}
