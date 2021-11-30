package vn.techmaster.university_library.service;

import vn.techmaster.university_library.exception.EmailException;

public interface IEmailService {
  public void send(String to, String subject, String body) throws EmailException;
}
