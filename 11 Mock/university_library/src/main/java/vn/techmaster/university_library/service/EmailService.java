package vn.techmaster.university_library.service;

import org.springframework.stereotype.Service;

import vn.techmaster.university_library.exception.EmailException;

@Service
public class EmailService implements IEmailService {

  @Override
  public void send(String to, String subject, String body) throws EmailException {
    
    
  }
  
}
