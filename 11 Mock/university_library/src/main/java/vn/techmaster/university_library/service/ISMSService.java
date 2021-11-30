package vn.techmaster.university_library.service;

import vn.techmaster.university_library.exception.SMSException;

public interface ISMSService {
  public void send(String mobile, String message) throws SMSException;
}
