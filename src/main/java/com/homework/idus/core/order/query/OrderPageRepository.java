package com.homework.idus.core.order.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderPageRepository extends JpaRepository<Order, Long> {

    @Query(value = "select o from Order o where o.user.userNo =:userNo")
    Page<Order> findAll(@Param("userNo") Long userNo, Pageable pageable);


}
