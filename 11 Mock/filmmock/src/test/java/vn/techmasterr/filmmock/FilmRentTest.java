package vn.techmasterr.filmmock;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import vn.techmasterr.filmmock.exception.FilmException;
import vn.techmasterr.filmmock.model.Customer;
import vn.techmasterr.filmmock.model.Film;
import vn.techmasterr.filmmock.repository.CustomerRepo;
import vn.techmasterr.filmmock.repository.FilmRepo;
import vn.techmasterr.filmmock.service.EmailService;
import vn.techmasterr.filmmock.service.FilmService;
import vn.techmasterr.filmmock.service.FilmServiceImpl;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
class FilmRentTest {
	@Mock
	private FilmRepo filmRepo;

	@Mock
	private CustomerRepo customerRepo;

	@Mock
	private EmailService emailService;

	@InjectMocks
	private FilmService filmService = new FilmServiceImpl(filmRepo, customerRepo, emailService);

	@Test
	void whenCustomerRentsAFilm() {
		when(filmRepo.findById(anyString()))
		.thenAnswer(invocation -> 
			Optional.of(new Film((String)invocation.getArguments()[0], "Titanic")));

		/*when(customerRepo.findById(anyString()))
		.thenAnswer(invocation -> 
			Optional.of(new Customer((String)invocation.getArguments()[0], "John", 
			"john@onemount.com", "0902209011")));*/
		

		//Nếu không dùng tham số truyền vào
		when(customerRepo.findById(anyString()))
		.thenReturn(Optional.of(new Customer("006", "John", "john@onemount.com", "0902209011")));

		try {
			filmService.rentAFilm("ox-13", "005");
		} catch (FilmException e) {
			e.printStackTrace();
		}
	}

	@Test
	void whenNonExistingCustomerRentsAFilm() {
		when(filmRepo.findById(anyString()))
		.thenAnswer(invocation -> 
			Optional.of(new Film((String)invocation.getArguments()[0], "Titanic")));

		when(customerRepo.findById(anyString()))
		.thenAnswer(invocation -> Optional.empty());


		assertThatThrownBy(() -> {
			filmService.rentAFilm("ox-13", "005");
		})
		.isInstanceOf(FilmException.class)
		.hasMessageStartingWith("Customer with id")
		.hasMessageContaining("not found");
	}

	@Test
	void whenCustomerRentsNonExistingFilm() {
		when(filmRepo.findById(anyString()))
		.thenAnswer(invocation -> 
		Optional.empty());

		/*
		when(customerRepo.findById(anyString()))
		.thenAnswer(invocation -> 
		Optional.of(new Customer((String)invocation.getArguments()[0], "John", 
		"john@onemount.com", "0902209011")));*/


		assertThatThrownBy(() -> {
			filmService.rentAFilm("ox-13", "005");
		})
		.isInstanceOf(FilmException.class)
		.hasMessageStartingWith("Film with id")
		.hasMessageContaining("not found");
	}

	@Test
	void whenCustomerWithBadEmailRentFilm() {
		when(filmRepo.findById(anyString()))
		.thenAnswer(invocation -> 
		Optional.of(new Film((String)invocation.getArguments()[0], "Titanic")));

		when(customerRepo.findById(anyString()))
		.thenAnswer(invocation -> 
		Optional.of(new Customer((String)invocation.getArguments()[0], "John", 
		"john@xxxx", "0902209011")));

		doThrow(new RuntimeException("Email is invalid"))
		.when(emailService)
		.send(anyString(), anyString(), anyString());

		/*when(emailService.send(anyString(), anyString(), anyString()))
		.thenThrow(new RuntimeException("Email is invalid"));*/

		assertThatThrownBy(() -> {
			filmService.rentAFilm("ox-13", "005");
		})
		.isInstanceOf(RuntimeException.class)
		.hasMessageStartingWith("Email is invalid");
	}

	@Test
	void whenCannotConnectToDatabase() {
		//Giả lập Exception khi gọi filmRepo.findById
		when(filmRepo.findById(anyString()))
		.thenThrow(new RuntimeException("Cannot connect to database"));

		
		assertThatThrownBy(() -> {
			filmService.rentAFilm("ox-13", "005");
		})
		.isInstanceOf(RuntimeException.class)
		.hasMessageStartingWith("Cannot connect to database");
	}

	@Test
	void whenInvalidFilmId() {
		//Giả lập Exception khi gọi filmRepo.findById với một tham số cụ thể
		when(filmRepo.findById(anyString()))
		.thenAnswer(invocation -> {
				switch ((String)invocation.getArguments()[0]) {
					case "000":
						throw new RuntimeException("Invalid film id");
					default:
						return Optional.of(new Film((String)invocation.getArguments()[0], "Titanic"));
				}
		});

		
		assertThatThrownBy(() -> {
			filmService.rentAFilm("ox-13", "000");
		})
		.isInstanceOf(RuntimeException.class)
		.hasMessageStartingWith("Invalid film id");
	}

}
