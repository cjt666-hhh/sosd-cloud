package com.sosd.cloud.common;

import lombok.Data;

@Data
public class PageResult<T> {
    private final int code;
    private final T data;
    private int total;
    private String msg;

    public PageResult(int total, int code, T data, String msg) {
        this.total = total;

        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> PageResult<T> success(int total, int code, T data, String msg) {
        return new PageResult<>(total, code, data, msg);
    }


}
