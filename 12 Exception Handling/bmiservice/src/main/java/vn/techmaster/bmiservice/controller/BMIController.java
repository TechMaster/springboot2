package vn.techmaster.bmiservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vn.techmaster.bmiservice.exception.APIError;
import vn.techmaster.bmiservice.exception.BMIException;
import vn.techmaster.bmiservice.exception.BMILogicException;
import vn.techmaster.bmiservice.request.BMIRequest;
import vn.techmaster.bmiservice.response.BMIResult;
import vn.techmaster.bmiservice.service.HealthService;

@RestController
@RequestMapping("/")
@Slf4j
public class BMIController {
  @Autowired
  private HealthService healthService;


  @GetMapping("bmi/{height}/{weight}")
  public ResponseEntity<BMIResult> calculateBMI(@PathVariable String height, @PathVariable String weight) {
    float fheight;
    float fweight;
    
    try {
      fheight = Float.parseFloat(height);
      fweight = Float.parseFloat(weight);
      log.info("Height " + fheight + " weight " + fweight);
    } catch (NumberFormatException e) {
      log.error("Invalid height = {}, weight = {}", height, weight);      
      return ResponseEntity.badRequest().build(); //Trả về lỗi 400
    }
    BMIRequest bmiRequest = new BMIRequest(fheight, fweight);
    return ResponseEntity.ok(healthService.calculateBMI(bmiRequest));
  }

  @GetMapping("bmi2/{height}/{weight}")
  public ResponseEntity<BMIResult> calculateBMI2(@PathVariable String height, @PathVariable String weight) {
    float fheight;
    float fweight;
    try {
      fheight = Float.parseFloat(height);
      fweight = Float.parseFloat(weight);
    } catch (NumberFormatException e) {
      //Cách này khá hơn một tý, trả về JSON dễ nhìn
      return ResponseEntity.badRequest().body(new BMIResult(-1, "Bad number format"));
    }
    BMIRequest bmiRequest = new BMIRequest(fheight, fweight);
    return ResponseEntity.ok(healthService.calculateBMI(bmiRequest));
  }


  @GetMapping("bmi3/{height}/{weight}")
  public ResponseEntity<BMIResult> calculateBMI3(@PathVariable String height, @PathVariable String weight) {
    float fheight;
    float fweight;
    try {
      fheight = Float.parseFloat(height);
      fweight = Float.parseFloat(weight);
    } catch (NumberFormatException e) {
      throw new BMIException("Cannot parse weight or height to float");
    }

    if (fheight < 0 || fweight < 0) {
      throw new BMIException("weight or height is negative");
    }

    if (fheight > 2.8) {
      throw new BMILogicException(fheight + " is too high");
    }

    BMIRequest bmiRequest = new BMIRequest(fheight, fweight);
    return ResponseEntity.ok(healthService.calculateBMI(bmiRequest));
  }

  @ExceptionHandler({ BMIException.class })
  public ResponseEntity<APIError> handleException(BMIException ex) {
    return ResponseEntity.badRequest().body(new APIError("Dữ liệu đầu vào không hợp lệ", ex.getMessage()));
  }

  @ExceptionHandler({ BMILogicException.class })
  public ResponseEntity<APIError> handleException2(BMILogicException ex) {
    return ResponseEntity.badRequest().body(new APIError("Logic tính BMI sai", ex.getMessage()));
  }
}
