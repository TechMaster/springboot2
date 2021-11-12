package vn.techmaster.hiaop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.hiaop.annotation.RemoveAccent;
import vn.techmaster.hiaop.exception.FilmException;
import vn.techmaster.hiaop.model.Film;
import vn.techmaster.hiaop.service.Account;
import vn.techmaster.hiaop.service.FilmService;

@RestController
public class HomeController {
  @Autowired private Account account;
  @Autowired private FilmService film;
  

  @GetMapping("/do")
  public String doSomething() {
    account.doSomething();
    return "Done";
  }

  @GetMapping("/say")
  public String saySomething() {
    account.saySomething();
    return "Said done";
  }


  @GetMapping("/transfer")
  public String transfer() {
    account.transfer(100);
    return "Done";
  }

  @GetMapping("/film")
  public String create() {
    film.create();
    return "Done";
  }

  @GetMapping("/stream")
  public String stream() {
    film.stream();
    return "Stream";
  }

  @GetMapping(value ="/films",  produces = MediaType.APPLICATION_XML_VALUE)
  public List<Film> get_all_films() {    
    return film.findAll();
  }

  @GetMapping("/noaccent")
  @RemoveAccent
  public String remove_accent() {    
    return "Cháu Ngoan Bác Hồ thì phải chăm lập trình";
  }

  @GetMapping("/filmid")
  public String findFilmById() {    
    try {
      film.findById("OX-13");
      return "ok";
    } catch (FilmException e) {
      return "not ok";
    }
  }

}
