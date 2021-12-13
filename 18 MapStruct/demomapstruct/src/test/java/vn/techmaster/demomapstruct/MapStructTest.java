package vn.techmaster.demomapstruct;

import static org.assertj.core.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import vn.techmaster.demomapstruct.dto.annotation.Film;
import vn.techmaster.demomapstruct.dto.annotation.FilmDTO;
import vn.techmaster.demomapstruct.dto.annotation.FilmMapper;
import vn.techmaster.demomapstruct.dto.bmi.BMIImperial;
import vn.techmaster.demomapstruct.dto.bmi.BMIMapper;
import vn.techmaster.demomapstruct.dto.bmi.BMIMetric;
import vn.techmaster.demomapstruct.dto.simple.Person;
import vn.techmaster.demomapstruct.dto.simple.User;
import vn.techmaster.demomapstruct.dto.simple.UserPersonMapper;
import vn.techmaster.demomapstruct.dto.simple2.Customer;
import vn.techmaster.demomapstruct.dto.simple2.CustomerDto;
import vn.techmaster.demomapstruct.dto.simple2.CustomerMapper;
import vn.techmaster.demomapstruct.dto.simple2.OrderItem;
import vn.techmaster.demomapstruct.dto.simple2.OrderItemDto;
import vn.techmaster.demomapstruct.dto.typeconversion.Car;
import vn.techmaster.demomapstruct.dto.typeconversion.CarDTO;
//import org.assertj.core.api.Assertions;
import vn.techmaster.demomapstruct.dto.typeconversion.CarMapper;

class MapStructTest {
	@Test
	void User_to_Person() {
		User user = new User(1L, "Trịnh", "Cường", "cuong@techmaster.vn", "oX12e2w");
		var mapper = UserPersonMapper.INSTANCE;
		Person person = mapper.UserToPerson(user);
		assertThat(person.getFirst_name()).isEqualTo("Trịnh");
	}

	@Test
	public void testMapDtoToEntity() {
		CustomerDto customerDto = new CustomerDto();
		customerDto.id = 10L;
		customerDto.customerName = "Filip";
		OrderItemDto order1 = new OrderItemDto();
		order1.name = "Table";
		order1.quantity = 2L;
		customerDto.orders = new ArrayList<>(Collections.singleton(order1));

		Customer customer = CustomerMapper.MAPPER.toCustomer(customerDto);

		assertThat(customer.getId()).isEqualTo(10);
		assertThat(customer.getName()).isEqualTo("Filip");
		assertThat(customer.getOrderItems())
				.extracting("name", "quantity")
				.containsExactly(tuple("Table", 2L));
	}

	@Test
	public void testEntityDtoToDto() {
		Customer customer = new Customer();
		customer.setId(10L);
		customer.setName("Filip");
		OrderItem order1 = new OrderItem();
		order1.setName("Table");
		order1.setQuantity(2L);
		customer.setOrderItems(Collections.singleton(order1));

		CustomerDto customerDto = CustomerMapper.MAPPER.fromCustomer(customer);

		assertThat(customerDto.id).isEqualTo(10);
		assertThat(customerDto.customerName).isEqualTo("Filip");
		assertThat(customerDto.orders)
				.extracting("name", "quantity")
				.containsExactly(tuple("Table", 2L));
	}

	@Test
	public void useAnnotation() {
		Film film = new Film(1L, "Money Heist", "Aron Kar");

		FilmDTO filmDTO = FilmMapper.INSTANCE.filmToDTO(film);
		System.out.println(filmDTO);
	}

	@Test
	public void convertImperialToMetric() {
		BMIImperial bmiIMperial = new BMIImperial(1, 1);
		var bmi_mapper = BMIMapper.INSTANCE;
		BMIMetric bmiMetric = bmi_mapper.imperial_to_metric(bmiIMperial);

		System.out.println(bmiMetric);
		assertThat(bmiMetric.getHeight_cm()).isCloseTo(2.54f, within(0.0001f));
		assertThat(bmiMetric.getWeight_kg()).isCloseTo(0.453592f, within(0.0001f));
	}

	@Test
	public void convertType() {
		Car car = new Car(null, "Triton 4x4", 40000, Date.valueOf("2020-10-21"));
		CarDTO carDTO = CarMapper.INSTANCE.carToCarDto(car);
		System.out.println(carDTO);
	}
}
