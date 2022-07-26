package com.homework.idus.core.user.query;

import com.homework.idus.core.user.Gender;
import com.homework.idus.core.user.UserCrudSupplier;
import com.homework.idus.core.user.command.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
@DisplayName("UserPageSearcher")
class UserPageSearcherTest extends UserCrudSupplier {

    @BeforeEach
    void prepareData() {
        getUserDeleteAll();
    }

    @Nested
    @DisplayName("findAll 메서드")
    class Describe_findAll_method {

        @BeforeEach
        void prepareData() {
            for (int i = 0; i < 10; i++) {
               User user =  User.builder()
                       .name("홍길동"+ i)
                       .nickname("별명"+ i)
                       .email( i+"test@test.com")
                       .password("1234a12341233!A")
                       .mobilePhoneNo("010-2345-111"+ i)
                       .gender(Gender.MAN)
                       .build();

                getUserCreator().create(user);
            }
        }

        @Nested
        @DisplayName("회원 전체 조회 요청이면")
        class Context_findAll {

            @Test
            @DisplayName("등록된 회원 수 전체를 리턴한다")
            void it_returns_total_user() {
                UserSearchDescription command = new UserSearchDescription() {
                    @Override
                    public String getName() {
                        return null;
                    }

                    @Override
                    public String getEmail() {
                        return null;
                    }
                };
                
                PageRequest pageRequest = PageRequest.of(0, 10);
                Page<User> savedUser =  getUserPageSearcher().findAll(command, pageRequest);
                System.out.println("dd");
            }
        }
    }

}