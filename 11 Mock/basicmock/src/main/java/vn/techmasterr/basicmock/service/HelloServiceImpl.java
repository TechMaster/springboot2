package vn.techmasterr.basicmock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmasterr.basicmock.repository.HelloRepo;

@Service
public class HelloServiceImpl implements HelloService {

  @Autowired private HelloRepo helloRepo;
  @Override
  public String say() {
    return helloRepo.hi();
  }  
}
