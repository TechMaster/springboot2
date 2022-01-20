package vn.techmaster.fluxmongo.exception;

import java.util.UUID;

public class PostNotFoundException extends RuntimeException {
  public PostNotFoundException(UUID id) {
    super("Post:" + id +" is not found.");
  }
}
