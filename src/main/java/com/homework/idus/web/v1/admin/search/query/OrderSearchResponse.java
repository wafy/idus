package com.homework.idus.web.v1.admin.search.query;

import com.homework.idus.core.order.query.Order;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderSearchResponse {
    private String orderNo;
    private String productName;
    private LocalDateTime orderAt;

    public OrderSearchResponse(Order order) {
        this.orderNo = order.getOrderNo();
        this.productName = order.getProductName();
        this.orderAt = order.getOrderAt();
    }
}
