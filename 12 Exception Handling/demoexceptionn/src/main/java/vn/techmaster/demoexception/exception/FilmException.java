package vn.techmaster.demoexception.exception;

public class FilmException extends Exception {
  public String hostname;
  public String userid;
  public String filmid;
  public FilmException(String message, String hostname, String userid, String filmid) {
    super(message);
    this.hostname = hostname;
    this.userid = userid;
    this.filmid = filmid;
  }
  @Override
  public String toString() {
    return "FilmException [filmid=" + filmid + ", hostname=" + hostname + ", userid=" + userid + "]";
  }
}
