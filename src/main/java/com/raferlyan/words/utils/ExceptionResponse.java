package com.raferlyan.words.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionResponse<T> {

  @Builder.Default private int code = 400;

  @Builder.Default private String message = "";

  @Builder.Default private boolean messageVisible = true;

  private T data;
}
