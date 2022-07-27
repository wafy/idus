package com.homework.idus.core.user.fixture;

import com.homework.idus.core.order.query.Order;
import com.homework.idus.core.user.command.User;

import java.time.LocalDateTime;

public class OrderFixture {

    private static final String givenProductName = "테스트 상품명";

    private static final String givenOrderNo = "A1234";

    public static Order _주문등록(Long userNo) {

        return Order.builder()
                .orderNo(givenOrderNo)
                .productName(givenProductName)
                .orderAt(LocalDateTime.now())
                .user(new User(userNo))
                .build();
    }
}
