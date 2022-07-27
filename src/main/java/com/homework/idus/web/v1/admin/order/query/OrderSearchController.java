package com.homework.idus.web.v1.admin.order.query;

import com.homework.idus.core.order.query.Order;
import com.homework.idus.core.order.query.OrderPageSearcher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/admin/order")
@RequiredArgsConstructor
public class OrderSearchController {
    private final OrderPageSearcher orderPageSearcher;

    @GetMapping("/{userNo}")
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderResponse> findAll(@PathVariable Long userNo,
                                       Pageable pageable) {
        Page<Order> orders = orderPageSearcher.findAll(userNo, pageable);
        return orders.map(OrderResponse::new);
    }
}
