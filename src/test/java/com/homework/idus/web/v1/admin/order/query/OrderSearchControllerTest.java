package com.homework.idus.web.v1.admin.order.query;

import com.homework.idus.core.order.query.Order;
import com.homework.idus.core.user.TestSupplier;
import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.fixture.OrderFixture;
import com.homework.idus.core.user.fixture.UserFixture;
import com.homework.idus.util.ResourceMockUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("OrderSearchController")
class OrderSearchControllerTest extends TestSupplier {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void prepareData() {
        getOrderDeleteAll();
        getUserDeleteAll();
    }

    @Nested
    @DisplayName("findAll 메소드")
    class Describe_findAll_method {

        User savedUser;
        Order savedOrder;

        @BeforeEach
        void prepareData() {
            savedUser = getUserCreator().create(UserFixture._회원가입());
            savedOrder = getOrderSave(OrderFixture._주문등록(savedUser.getUserNo()));

        }

        @Nested
        @DisplayName("단일 회원 주문 조회 요청")
        class Context_request_valid {

            @Test
            @DisplayName("HTTP 응답코드 200를 반환한다")
            void it_returns_200() throws Exception {
                String request = ResourceMockUtil.getString("v1/admin/order.json");

                mockMvc.perform(get("/v1/admin/order/"+ savedUser.getUserNo())
                                .content(request)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
            }
        }
    }
}