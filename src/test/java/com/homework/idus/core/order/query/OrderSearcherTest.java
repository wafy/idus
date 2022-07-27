package com.homework.idus.core.order.query;

import com.homework.idus.core.user.TestSupplier;
import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.fixture.OrderFixture;
import com.homework.idus.core.user.fixture.UserFixture;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DataJpaTest
@DisplayName("OrderSearcher")
class OrderSearcherTest extends TestSupplier {

    @BeforeEach
    void prepareData() {
        getUserDeleteAll();
    }

    @Nested
    @DisplayName("finAll 메소드")
    class Describe_findAll_method {
        User savedUser;

        @BeforeEach
        void prepareData() {
            savedUser = getUserCreator().create(UserFixture._회원가입());
            getOrderSave(OrderFixture._주문등록(savedUser.getUserNo()));
        }

        @Nested
        @DisplayName("회원의 주문이 있는 경우 요청")
        class Context_user_orderList {

            @Test
            @DisplayName("요청한 회원번호에 속한 주문목록을 리턴한다")
            void it_returns_order_findAll() {
                Pageable pageable = PageRequest.of(1, 10);
                Page<Order> orders = getOrderSearcher().findAll(savedUser.getUserNo(), pageable);
                Assertions.assertEquals(1, orders.getTotalElements());
            }
        }
    }
}