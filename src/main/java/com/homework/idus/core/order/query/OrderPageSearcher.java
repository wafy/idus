package com.homework.idus.core.order.query;

import com.homework.idus.axiom.query.Pager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class OrderPageSearcher {
    private final OrderPageRepository orderPageRepository;

    /**
     * 사용자 주문을 조회해 리턴합니다.
     * @param userNo 사용자번호
     * @param pager 페이저정보
     * @return  조회 결과
     */
    @Transactional(readOnly = true)
    public Page<Order> findAll(Long userNo, Pager pager) {
        Pageable pageable = PageRequest.of(pager.getPage(), pager.getSize());
        return orderPageRepository.findAll(userNo, pageable);
    }

}
