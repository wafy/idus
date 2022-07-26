package com.homework.idus.web.v1.admin;

import com.homework.idus.core.user.UserCrudSupplier;
import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.fixture.UserFixture;
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
@DisplayName("UserSearchController")
class UserSearchControllerTest extends UserCrudSupplier {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void prepareData() {
        getUserDeleteAll();
    }

    @Nested
    @DisplayName("findByUserNo 메소드")
    class Describe_findByUserNo_method {

        @Nested
        @DisplayName("유효한 회원번호 요청이면")
        class Context_request_valid {
            User savedUser;

            @BeforeEach
            void prepareData() {
                savedUser = getUserCreator().create(UserFixture._회원가입());
            }

            @Test
            @DisplayName("HTTP 응답코드 200를 반환한다")
            void it_returns_201() throws Exception {

                mockMvc.perform(get("/v1/admin/search/"+ savedUser.getUserNo())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
            }
        }

    }
}