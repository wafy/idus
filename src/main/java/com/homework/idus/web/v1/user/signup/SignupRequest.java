package com.homework.idus.web.v1.user.signup;

import com.homework.idus.core.user.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * 회원가입 요청 DTO 객체.
 */
@Getter
@NoArgsConstructor
public class SignupRequest {

    @Size(min = 1, max = 20, message = "이름은 최대 20글자 까지 가능합니다")
    @NotBlank(message = "이름은 필수 값입니다")
    @Pattern(regexp = "^[a-zA-Zㄱ-힣]*$", message = "한글 영문 대소문자만 가능합니다")
    private String name;

    @Size(min = 1, max = 30, message = "별명은 최대 30글자 까지 가능합니다")
    @NotBlank(message = "별명은 필수 값입니다")
    @Pattern(regexp = "^[a-z]*$", message = "영문 소문자만 가능합니다")
    private String nickname;


    @Pattern(regexp = "^((?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{10,})$",
            message = "영문대소문자, 특수문자, 숫자 한개씩 포함 되어야 하며 최소 10자리 이상입니다")
    private String password;


    @Pattern(regexp = "^((?=.*[0-9]).{1,20})$",
            message = "숫자 포함 20글자 이내 입니다")
    private String mobilePhoneNo;

    @Size(max = 100, message = "유효한 이메일 형식이 아닙니다")
    @NotBlank(message = "유효한 이메일 형식이 아닙니다")
    @Email(message = "유효한 이메일 형식이 아닙니다")
    private String email;
    private Gender gender;

    public SignupRequest(String name,
                         String nickname,
                         String password,
                         String mobilePhoneNo,
                         String email,
                         Gender gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.mobilePhoneNo = mobilePhoneNo;
        this.email = email;
        this.gender = gender;
    }

}
