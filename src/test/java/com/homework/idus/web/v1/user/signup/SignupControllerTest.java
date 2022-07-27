package com.homework.idus.web.v1.user.signup;

import com.homework.idus.core.user.TestSupplier;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("SignupController")
class SignupControllerTest extends TestSupplier {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void prepareData() {
        getUserDeleteAll();
    }

    @Nested
    @DisplayName("signup 메소드")
    class Describe_signup_method {

        @Nested
        @DisplayName("유효한 회원 요청이면")
        class Context_request_valid {

            @Test
            @DisplayName("HTTP 응답코드 201를 반환한다")
            void it_returns_201() throws Exception {
                String request = ResourceMockUtil.getString("v1/user/signup.json");

                mockMvc.perform(post("/v1/user/signup")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated());
            }
        }
    }
}