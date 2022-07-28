package com.homework.idus.web.v1.axiom;

import com.homework.idus.web.v1.user.login.query.LoginResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * HTTP 응댑 객체를 정의힙니다.
 * @param <E>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseModel<E> {

    @Schema(name = "status", description = "성공여부 true/false")
    private boolean status;
    private String message;
    private E data;

    public ApiResponseModel(LoginResponse loginResponse) {
        this.status = true;
        this.message = "로그인이 성공적으로 완료되었습니다.";
        this.data = (E) loginResponse;
    }
}
