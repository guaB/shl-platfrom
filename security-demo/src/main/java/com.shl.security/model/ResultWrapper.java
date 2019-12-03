package com.shl.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-11-20
 */
@Data
@Builder
@AllArgsConstructor
public class ResultWrapper<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> ResultWrapper<T> success(T data) {
        return new ResultWrapper<>(200, "success", data);
    }
}
