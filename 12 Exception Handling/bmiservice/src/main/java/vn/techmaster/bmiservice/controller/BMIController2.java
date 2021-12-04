package vn.techmaster.bmiservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import vn.techmaster.bmiservice.exception.APIError;
import vn.techmaster.bmiservice.exception.BMIException;
import vn.techmaster.bmiservice.exception.BMILogicException;

@RestController
public class BMIController2 {
  @GetMapping("/bmi4")
  public String demoThrow() {
    throw new BMIException("Cannot parse weight or height to float");
  }

  @GetMapping("/bmi5")
  public String demoThrow2() {
    throw new BMILogicException("Cannot parse weight or height to float");
  }

  @GetMapping("/bmi6")
  public String demoThrow4() {
    throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Actor Not Found");
  }

  @ExceptionHandler({ BMIException.class, BMILogicException.class })
  public ResponseEntity<APIError> handleException(RuntimeException ex) {
    return ResponseEntity.badRequest().body(new APIError("Bắt được cả BMIException và BMILogicException", ex.getMessage()));
  }

}
