package com.homework.idus.core.user.fixture;


import com.homework.idus.core.user.Gender;
import com.homework.idus.core.user.command.User;

public class UserFixture {

    public static final String givenName = "홍길동";
    public static final String givenNickname = "idus";
    public static final String givenPassword = "1234a12341233!A";
    public static final String givenMobilePhoneNo = "010-1234-1234";
    public static final String givenEmail = "test@idus.com";
    public static final Gender givenGender = Gender.MAN;

    public final static Long givenNotExistUserNo = 999999999999L;




    public static User _회원가입() {
        return User.of(givenName, givenNickname, givenPassword, givenMobilePhoneNo,
                givenEmail, givenGender);
    }




}
