package com.sosd.cloud.common;

import lombok.Data;

@Data
public class Result<T> {
  private final int code;
  private final T data;
  private String msg;

  private Result(int code, T data, String msg) {
    this.code = code;
    this.data = data;
    this.msg = msg;
  }

  private Result(int code, String msg) {
    this.code = code;
    this.data = null;
    this.msg = msg;
  }

  public static <T> Result<T> success(T data) {
    return new Result<>(200, data, "操作成功");
  }

  public static <T> Result<T> success(T data, String msg) {
    return new Result<>(200, data, msg);
  }

  public static <T> Result<T> error(int code, T data, String msg) {
    return new Result<>(code, data, msg);
  }

  public static <T> Result<T> error(int code, String msg) {
    return new Result<>(code, msg);
  }


}
