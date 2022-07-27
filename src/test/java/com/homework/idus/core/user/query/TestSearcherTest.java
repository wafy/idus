package com.homework.idus.core.user.query;

import com.homework.idus.core.exception.UserNotFountException;
import com.homework.idus.core.user.TestSupplier;
import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.fixture.UserFixture;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("UserSearcher")
class TestSearcherTest extends TestSupplier {

    @Autowired
    UserSearcher userSearcher;

    @BeforeEach
    void prepareData() {
        getUserDeleteAll();
    }

    @Nested
    @DisplayName("findByUserNo 메서도")
    class Describe_findByUserNo_method {

        @Nested
        @DisplayName("등록된 사용자 번호로 조회 요청이면")
        class Context_exist_userNo {
            User savedUser;

            @BeforeEach
            void prepareData() {
                savedUser = getUserCreator().create(UserFixture._회원가입());
            }

            @Test
            @DisplayName("요청한 사용번호에 해당하는 사용자 정보를 리턴한다")
            void it_returns_saved_user() {
                User saved = userSearcher.findByUserNo(savedUser.getUserNo());
                Assertions.assertNotNull(saved);
                Assertions.assertEquals(savedUser.getUserNo(), saved.getUserNo());
            }
        }

        @Nested
        @DisplayName("등록되지 않는 사용자 번호로 조외 요쳥이면")
        class Context_not_exist_userNo {

            @Test
            @DisplayName("예외를 리턴한다")
            void it_throws_exception() {
                Assertions.assertThrows(UserNotFountException.class,
                        () -> userSearcher.findByUserNo(UserFixture.givenNotExistUserNo));
            }
        }
    }

}