package vn.techmaster.fluxh2reactive.exception;


public class PostNotFoundException extends RuntimeException {
  public PostNotFoundException(Long id) {
    super("Post:" + id +" is not found.");
  }
}
