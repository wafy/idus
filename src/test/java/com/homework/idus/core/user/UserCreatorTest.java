package com.homework.idus.core.user;

import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.fixture.UserFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("UserCreator")
public class UserCreatorTest extends UserCrudSupplier {

    @Nested
    @DisplayName("create 메소드")
    class Describe_create {

        @Nested
        @DisplayName("정상적인 회원가입 생성 요청이 주어지면")
        class Context_normal_create_request {

            @Test
            @DisplayName("회원가입을 생성해 리턴한다")
            void it_returns_created_user() {
                User givenUser = UserFixture._회원가입();
                User result = getUserCreator().create(givenUser);
                Assertions.assertNotNull(result);
                Assertions.assertEquals(givenUser.getName(), result.getName());
            }
        }
    }
}