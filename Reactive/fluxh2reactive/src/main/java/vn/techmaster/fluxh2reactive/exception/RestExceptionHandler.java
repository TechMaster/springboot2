package vn.techmaster.fluxh2reactive.exception;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
class RestExceptionHandler {

  @ExceptionHandler(value = {RuntimeException.class})
  public final ResponseEntity<APIError> handleAllExceptions(RuntimeException ex) {
    APIError apiError = new APIError("Generic Exception", ex.getLocalizedMessage());
    log.error(ex.getLocalizedMessage());
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @ExceptionHandler(value = {PostNotFoundException.class})
  public final ResponseEntity<APIError> postNotFound(PostNotFoundException ex) {
    APIError apiError = new APIError("Post Not Found", ex.getLocalizedMessage());
    log.error(ex.getLocalizedMessage());
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

}