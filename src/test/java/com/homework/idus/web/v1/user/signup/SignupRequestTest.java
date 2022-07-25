package com.homework.idus.web.v1.user.signup;

import com.homework.idus.core.user.fixture.UserFixture;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("SignupRequest")
class SignupRequestTest {

    private static Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("회원가입 요청 유효성 체크")
    class Describe_signup_request {

        @Nested
        @DisplayName("이름 유효성 체크")
        class Context_request_name {

            @Nested
            @DisplayName("null 값이 주어지면")
            class Context_null_name {

                @Test
                @DisplayName("예외를 던진다")
                void it_throws_exception1() {
                    SignupRequest request = new SignupRequest(
                            null,
                            UserFixture.givenNickname,
                            UserFixture.givenPassword,
                            UserFixture.givenMobilePhoneNo,
                            UserFixture.givenEmail,
                            UserFixture.givenGender);
                    Set<ConstraintViolation<SignupRequest>> violations = validator.validate(request);
                    Assertions.assertNotNull(violations);
                    violations.forEach
                            (error -> assertEquals("이름은 필수 값입니다", error.getMessage()));
                }
            }

            @Nested
            @DisplayName("20글자 초과 요청이면")
            class Context_max_length_name {

                @Test
                @DisplayName("예외를 던진다")
                void it_throws_exception1() {
                    String _20글자_초과_이름 = "아이디어스아이디어스아이디어스아이디어스아이디어스아이디어스";
                    SignupRequest request = new SignupRequest(
                            _20글자_초과_이름,
                            UserFixture.givenNickname,
                            UserFixture.givenPassword,
                            UserFixture.givenMobilePhoneNo,
                            UserFixture.givenEmail,
                            UserFixture.givenGender);
                    Set<ConstraintViolation<SignupRequest>> violations = validator.validate(request);
                    Assertions.assertNotNull(violations);
                    violations.forEach
                            (error -> assertEquals("이름은 최대 20글자 까지 가능합니다", error.getMessage()));
                }
            }

            @Nested
            @DisplayName("특수문자가 포함된 요청이면")
            class Context_special_name {

                @Test
                @DisplayName("예외를 던진다")
                void it_throws_exception1() {
                    String _특수문자_포함_이름 = "!@아이디어스";
                    SignupRequest request = new SignupRequest(
                            _특수문자_포함_이름,
                            UserFixture.givenNickname,
                            UserFixture.givenPassword,
                            UserFixture.givenMobilePhoneNo,
                            UserFixture.givenEmail,
                            UserFixture.givenGender);
                    Set<ConstraintViolation<SignupRequest>> violations = validator.validate(request);
                    Assertions.assertNotNull(violations);
                    violations.forEach
                            (error -> assertEquals("한글 영문 대소문자만 가능합니다", error.getMessage()));
                }
            }
        }

        @Nested
        @DisplayName("별명 유효성 체크")
        class Context_request_nickname {

            @Nested
            @DisplayName("null 값이 주어지면")
            class Context_null_nickname {

                @Test
                @DisplayName("예외를 던진다")
                void it_throws_exception1() {
                    SignupRequest request = new SignupRequest(
                            UserFixture.givenName,
                            null,
                            UserFixture.givenPassword,
                            UserFixture.givenMobilePhoneNo,
                            UserFixture.givenEmail,
                            UserFixture.givenGender);
                    Set<ConstraintViolation<SignupRequest>> violations = validator.validate(request);
                    Assertions.assertNotNull(violations);
                    violations.forEach
                            (error -> assertEquals("별명은 필수 값입니다", error.getMessage()));
                }
            }

            @Nested
            @DisplayName("30글자 초과 요청이면")
            class Context_max_length_nickname {

                @Test
                @DisplayName("예외를 던진다")
                void it_throws_exception1() {
                    SignupRequest request = new SignupRequest(
                            UserFixture.givenName,
                            "idusidusidusidusidusidusidusidusidusidusidusidusidusidus",
                            UserFixture.givenPassword,
                            UserFixture.givenMobilePhoneNo,
                            UserFixture.givenEmail,
                            UserFixture.givenGender);
                    Set<ConstraintViolation<SignupRequest>> violations = validator.validate(request);
                    Assertions.assertNotNull(violations);
                    violations.forEach
                            (error -> assertEquals("별명은 최대 30글자 까지 가능합니다", error.getMessage()));
                }
            }

            @Nested
            @DisplayName("영문 대문자 또는 한글이 포함된 요청이면")
            class Context_include_nickname {

                @Test
                @DisplayName("예외를 던진다")
                void it_throws_exception1() {
                    SignupRequest request = new SignupRequest(
                            UserFixture.givenName,
                            "AA한글",
                            UserFixture.givenPassword,
                            UserFixture.givenMobilePhoneNo,
                            UserFixture.givenEmail,
                            UserFixture.givenGender);
                    Set<ConstraintViolation<SignupRequest>> violations = validator.validate(request);
                    Assertions.assertNotNull(violations);
                    violations.forEach
                            (error -> assertEquals("영문 소문자만 가능합니다", error.getMessage()));
                }
            }

            @Nested
            @DisplayName("숫자가 포함된 요청이면")
            class Context_include_number_nickname {

                @ParameterizedTest
                @ValueSource(strings = {
                        //숫자 prefix가 있는 경우
                        "1111aaaa",
                        "1234abcd",
                        //삿자가 postfix가 있는 경우
                        "aaa1111",
                        "test1234"
                })
                @DisplayName("예외를 던진다")
                void it_throws_exception1(String input) {
                    SignupRequest request = new SignupRequest(
                            UserFixture.givenName,
                            input,
                            UserFixture.givenPassword,
                            UserFixture.givenMobilePhoneNo,
                            UserFixture.givenEmail,
                            UserFixture.givenGender);
                    Set<ConstraintViolation<SignupRequest>> violations = validator.validate(request);
                    Assertions.assertTrue(violations.size() > 0);
                    violations.forEach
                            (error -> assertEquals("영문 소문자만 가능합니다", error.getMessage()));
                }
            }
        }

        @Nested
        @DisplayName("비밀번호 유효성 체크")
        class Context_request_password {

            @Nested
            @DisplayName("제약 사항에 포함되지 않는 문자열이 주어지면")
            class Context_null_password {

                @ParameterizedTest
                @ValueSource(strings = {
                        "",
                        " ",
                        "1한글12dd",
                        "강12aa!@",
                        "3N$5a"
                })
                @DisplayName("예외를 던진다")
                void it_throws_exception(String input) {
                    SignupRequest request = new SignupRequest(
                            UserFixture.givenName,
                            UserFixture.givenNickname,
                            input,
                            UserFixture.givenMobilePhoneNo,
                            UserFixture.givenEmail,
                            UserFixture.givenGender);
                    Set<ConstraintViolation<SignupRequest>> violations = validator.validate(request);
                    Assertions.assertTrue(violations.size() > 0);
                    violations.forEach
                            (error -> assertEquals("영문대소문자, 특수문자, 숫자 한개씩 포함 되어야 하며 최소 10자리 이상입니다", error.getMessage()));
                }
            }
        }

        @Nested
        @DisplayName("전화번호 유효성 체크")
        class Context_request_cellphone {

            @Nested
            @DisplayName("제약 사항에 포함되지 않는 문자열이 주어지면")
            class Context_null_password {

                @ParameterizedTest
                @ValueSource(strings = {
                        "",
                        " ",
                        "123456789012345678901111",
                        "iduspassword111",
                        "한글aaabbb111",
                        "ASAAA111AAAA",
                        "@#$%sdfdf",
                        "010-544-0057"
                })
                @DisplayName("예외를 던진다")
                void it_throws_exception(String input) {
                    SignupRequest request = new SignupRequest(
                            UserFixture.givenName,
                            UserFixture.givenNickname,
                            UserFixture.givenPassword,
                            input,
                            UserFixture.givenEmail,
                            UserFixture.givenGender);
                    Set<ConstraintViolation<SignupRequest>> violations = validator.validate(request);
                    Assertions.assertNotNull(violations);
                    violations.forEach
                            (error -> assertEquals("전화번호는 20자리 이내입니다.", error.getMessage()));
                }
            }
        }
    }
}