package com.homework.idus.core.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class OrderPageSearcher {
    private final OrderPageRepository orderPageRepository;

    @Transactional(readOnly = true)
    public Page<Order> findAll(Long userNo, Pageable pageable) {
        return orderPageRepository.findAll(userNo, pageable);
    }

}
