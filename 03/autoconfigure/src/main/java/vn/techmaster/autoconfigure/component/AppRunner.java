package vn.techmaster.autoconfigure.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import vn.techmaster.autoconfigure.repository.FilmRepo;

@Component
public class AppRunner implements CommandLineRunner {
  @Autowired
  private FilmRepo filmRepo;
  @Autowired
  private Environment environment;

  @Override
  public void run(String... args) throws Exception {
    filmRepo.addNew("Bố Già");
    filmRepo.addNew("Catch me if you can");
    filmRepo.addNew("Squid Game");
    filmRepo.addNew("Million Dollar Code");

    for (String profileName : environment.getActiveProfiles()) {
      System.out.println("Currently active profile - " + profileName);
    }
  }
}
