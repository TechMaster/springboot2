package vn.techmaster.fluxmongo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
class RestExceptionHandler {

  @ExceptionHandler(PostNotFoundException.class)
  ResponseEntity<?> postNotFound(PostNotFoundException ex) {
    log.debug("handling exception::" + ex.getMessage());
    return ResponseEntity.notFound().build();
  }

}