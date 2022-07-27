package com.homework.idus.web.v1.axiom;

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

    private boolean status;
    private String message;
    private E data;
}
