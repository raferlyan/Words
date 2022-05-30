package com.raferlyan.words.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ServiceException extends RuntimeException {
  @Builder.Default private String message = "";

  @Builder.Default private int code = 400;

  @Builder.Default private boolean messageVisible = true;

  private ServiceResult<Object> result;

  public static ServiceException withMessage(int code, String message) {
    return ServiceException.builder().message(message).code(code).build();
  }

  public static ServiceException withMessage(String message) {
    return ServiceException.builder().message(message).build();
  }
}
