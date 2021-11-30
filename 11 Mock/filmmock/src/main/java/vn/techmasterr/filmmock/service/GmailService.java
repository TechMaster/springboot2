package vn.techmasterr.filmmock.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class GmailService implements EmailService {

  @Override
  public void send(String to, String subject, String body) {    
    if (!GmailService.validate(to)) {
      throw new RuntimeException("Email is invalid");
    }

    System.out.println("Gmail delivers email to " + to);
  }

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
      Pattern.CASE_INSENSITIVE);

  public static boolean validate(String emailStr) {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
    return matcher.find();
  }

}
