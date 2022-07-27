package com.homework.idus.core.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class OrderPageSearcher {
    private final OrderPageRepository orderPageRepository;


    public Page<Order> findAll(Long userNo, Pageable pageable) {
        return orderPageRepository.findAll(userNo, pageable);
    }

}
