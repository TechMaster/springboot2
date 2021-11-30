package vn.techmasterr.filmmock.exception;

public class FilmException extends Exception{
  private String message;
  public FilmException(String message) {
    super(message);
  }
}
