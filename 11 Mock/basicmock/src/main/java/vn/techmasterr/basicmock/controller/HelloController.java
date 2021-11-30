package vn.techmasterr.basicmock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmasterr.basicmock.service.HelloService;

@RestController
public class HelloController {
  @Autowired private HelloService helloService;
  @GetMapping("/hi")
  public String hello() {
    return helloService.say();
  }
}
