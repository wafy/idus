package com.homework.idus.core.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class OrderSearcher {
    private final OrderRepository orderRepository;


    public Page<Order> findAll(Long userNo, Pageable pageable) {
        return orderRepository.findAll(userNo, pageable);
    }

}
