package com.raferlyan.words.config;

import com.raferlyan.words.utils.ExceptionResponse;
import com.raferlyan.words.utils.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * 系统自定义异常处理逻辑
   *
   * @param exception
   * @param request
   * @return
   */
  @ExceptionHandler(ServiceException.class)
  protected ResponseEntity<Object> handleServiceException(
          ServiceException exception, WebRequest request) {
    ExceptionResponse.ExceptionResponseBuilder<Object> responseBuilder =
        ExceptionResponse.builder()
            .message(exception.getMessage())
            .code(exception.getCode())
            .messageVisible(exception.isMessageVisible());
    if (exception.getResult() != null) {
      responseBuilder.data(exception.getResult().getData());
    }
    ExceptionResponse<Object> build = responseBuilder.build();
    return ResponseEntity.badRequest().body(build);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> exceptionHandler(Exception e) {

    log.error("An exception has occurred! {}", e);
    ExceptionResponse<Object> build = ExceptionResponse.builder().message(e.getMessage()).build();
    return ResponseEntity.badRequest().body(build);
  }
}
