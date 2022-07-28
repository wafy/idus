package com.homework.idus.core.user.command;

import com.homework.idus.core.exception.ExistException;
import com.homework.idus.core.exception.UserExistException;
import com.homework.idus.core.user.TestSupplier;
import com.homework.idus.core.user.fixture.UserFixture;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("UserCreator")
class UserCreatorTest extends TestSupplier {

    @BeforeEach
    void prepareData() {
        getOrderDeleteAll();
        getUserDeleteAll();
    }

    @Nested
    @DisplayName("create 메소드")
    class Describe_create_method {

        @Nested
        @DisplayName("사용자 생성 명령이 주어지면")
        class Context_normal_create_command {

            @Test
            @DisplayName("사용자를 등록해 리턴한다")
            void it_returns_created_user() {
                final User savedUser = getUserCreator().create(UserFixture._회원가입());

                Assertions.assertNotNull(savedUser);
                Assertions.assertNotNull(savedUser.getUserNo(), "새로 생성된 사용자는 회원번호를 갖고 있어야 합니다.");
            }
        }

        @Nested
        @DisplayName("이미 가입된 사용자 이름으로 생성 명령이 주어지면")
        class context_conflict_name_command {

            @BeforeEach
            void prepareData() {
                getUserCreator().create(UserFixture._회원가입());
            }

            @Test
            @DisplayName("예외를 던진다")
            void it_throws_exception() {
                Assertions.assertThrows(UserExistException.class,
                        () -> getUserCreator().create(UserFixture._회원가입()));
            }
        }

        @Nested
        @DisplayName("이미 가입된 사용자 별명으로 생성 명령이 주어지면")
        class context_conflict_nicname_command {

            @BeforeEach
            void prepareData() {
                getUserCreator().create(UserFixture._회원가입());
            }

            @Test
            @DisplayName("예외를 던진다")
            void it_throws_exception() {
                Assertions.assertThrows(ExistException.class,
                        () -> getUserCreator().create(UserFixture._회원가입_별명()));
            }
        }

        @Nested
        @DisplayName("이미 가입된 사용자 전화번호로 생성 명령이 주어지면")
        class context_conflict_mobilePhoneNo_command {

            @BeforeEach
            void prepareData() {
                getUserCreator().create(UserFixture._회원가입());
            }

            @Test
            @DisplayName("예외를 던진다")
            void it_throws_exception() {
                Assertions.assertThrows(ExistException.class,
                        () -> getUserCreator().create(UserFixture._회원가입_전화번호()));
            }
        }

        @Nested
        @DisplayName("이미 가입된 사용자 이메일로 생성 명령이 주어지면")
        class context_conflict_email_command {

            @BeforeEach
            void prepareData() {
                getUserCreator().create(UserFixture._회원가입());
            }

            @Test
            @DisplayName("예외를 던진다")
            void it_throws_exception() {
                Assertions.assertThrows(ExistException.class,
                        () -> getUserCreator().create(UserFixture._회원가입_이메일()));
            }
        }
    }
}