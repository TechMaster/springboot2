package vn.techmasterr.filmmock.service;

public interface EmailService {
  public void send(String to, String subject, String body);
}
