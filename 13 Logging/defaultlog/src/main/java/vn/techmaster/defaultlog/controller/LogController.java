package vn.techmaster.defaultlog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/log")
public class LogController {
  Logger logger = LoggerFactory.getLogger(LogController.class);
  @GetMapping("/basic")
  public String basicLog() {
    logger.trace("A TRACE Message");
    logger.debug("A DEBUG Message");
    logger.info("An INFO Message");
    logger.warn("A WARN Message");
    logger.error("An ERROR Message");
    return "Sử dụng Logger Factory";
  }

  // Sử dụng log object được tạo ra từ annotation @Slf4j
  @GetMapping("/slf4j")
  public String slf4jLog() {
    log.trace("A TRACE Message");
    log.debug("A DEBUG Message");
    log.info("An INFO Message");
    log.warn("A WARN Message");
    log.error("An ERROR Message");
    return "Sử dụng slf4j";
  }

}
