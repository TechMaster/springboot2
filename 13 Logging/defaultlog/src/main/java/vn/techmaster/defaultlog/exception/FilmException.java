package vn.techmaster.defaultlog.exception;


public class FilmException extends RuntimeException {
  private String filmid;
  
  public FilmException(String message, String filmid) {
    super(message);
    this.filmid = filmid;
  }

  public String getFilmid() {
    return filmid;
  }

  @Override
  public String toString() {
    return "FilmException [filmid=" + filmid + "]";
  }

}
