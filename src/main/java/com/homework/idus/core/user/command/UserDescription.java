package com.homework.idus.core.user.command;

import com.homework.idus.core.user.Gender;

public interface UserDescription {

    Long getId();

    String getName();

    String getNickname();

    String getPassword();

    String getMobilePhoneNo();

    String getEmail();

    Gender getGender();
}
