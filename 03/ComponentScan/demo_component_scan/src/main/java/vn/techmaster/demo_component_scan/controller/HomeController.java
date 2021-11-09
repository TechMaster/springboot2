package vn.techmaster.demo_component_scan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.ApplicationContext;

@RestController
public class HomeController {
  @Autowired private ApplicationContext context;
  
  @GetMapping("/api/beans")
  private List<String> getBeans() {
    return List.of(context.getBeanDefinitionNames());
  }
}
