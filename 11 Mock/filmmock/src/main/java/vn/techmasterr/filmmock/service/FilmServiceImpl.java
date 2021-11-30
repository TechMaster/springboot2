package vn.techmasterr.filmmock.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.techmasterr.filmmock.exception.FilmException;
import vn.techmasterr.filmmock.model.Customer;
import vn.techmasterr.filmmock.model.Film;
import vn.techmasterr.filmmock.repository.CustomerRepo;
import vn.techmasterr.filmmock.repository.FilmRepo;

@Service
public class FilmServiceImpl implements FilmService {
  private FilmRepo filmRepo;
  private CustomerRepo customerRepo;
  private EmailService emailService;
  
  public FilmServiceImpl(FilmRepo filmRepo, CustomerRepo customerRepo, EmailService emailService) {
    this.filmRepo = filmRepo;
    this.customerRepo = customerRepo;
    this.emailService = emailService;
  }
  public void rentAFilm(String customerId, String filmId) throws FilmException {
    Film film = filmRepo.findById(filmId)
    .orElseThrow(() -> new FilmException("Film with id `" + filmId + "` is not found"));

    Customer customer = customerRepo.findById(customerId)
    .orElseThrow(() -> new FilmException("Customer with id `" + customerId + "` is not found"));

    emailService.send(customer.getEmail(), film.getTitle() + " is streamed to your PC" , "....");
  }
  @Override
  public Film findByTitle(String title) throws FilmException {
    
    return null;
  }
}
