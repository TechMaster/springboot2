package vn.techmaster.autoconfigure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.techmaster.autoconfigure.configuration.Config;
import vn.techmaster.autoconfigure.configuration.EmailConfig;
import vn.techmaster.autoconfigure.event.CustomEvent;
import vn.techmaster.autoconfigure.model.Film;
import vn.techmaster.autoconfigure.model.Order;
import vn.techmaster.autoconfigure.repository.FilmRepo;


@Controller
public class HomeController {
  @Autowired private FilmRepo filmRepo;
  @Autowired private ApplicationEventPublisher applicationEventPublisher;
  @Autowired private EmailConfig emailConfig;
  @Autowired private Config config;

  @GetMapping("/")
  @ResponseBody
  public String showHome() {
    return "<h1>Hello World</h1>";
  }

  @GetMapping("/films")
  @ResponseBody
  public List<Film> getAllFilms() {
    return filmRepo.getAll();
  }

  @GetMapping("/film/{id}")
  @ResponseBody
  public Optional<Film> getFilmById(@PathVariable String id){
    return filmRepo.findById(id);
  }

  @PostMapping("/rent")
  @ResponseBody
  public String customerMakeOrder(@RequestBody Order order){
    System.out.println("Customer rent a film");
    CustomEvent customEvent = new CustomEvent(order, "ORDER");
    applicationEventPublisher.publishEvent(customEvent);
    return "Order is being proccesed";
  }

  @GetMapping("/email")
  @ResponseBody
  public String emailConfig(){
    return emailConfig.toString();
  }
}
