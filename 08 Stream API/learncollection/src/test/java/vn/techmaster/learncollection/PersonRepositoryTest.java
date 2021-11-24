package vn.techmaster.learncollection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.learncollection.model.Person;
import vn.techmaster.learncollection.repository.PersonRepositoryInterface;

@SpringBootTest
class PersonRepositoryTest {

	@Autowired
	PersonRepositoryInterface personRepository;

	@Test
	@DisplayName("getAll: lấy tất cả")
	public void getAll() {
		List<Person> people = personRepository.getAll();
		assertThat(people.size()).isGreaterThanOrEqualTo(20);
	}

	@Test
	@DisplayName("sortPeopleByFullName: sắp xếp theo tên")
	public void sortPeopleByFullName() {
		List<Person> people = personRepository.sortPeopleByFullName();
		assertThat(people).isSortedAccordingTo(Comparator.comparing(Person::getFullname));
	}

	@Test
	@DisplayName("sortPeopleByFullNameReversed: sắp xếp theo tên thứ tự Z-A")
	public void sortPeopleByFullNameZA() {
		List<Person> people = personRepository.sortPeopleByFullNameReversed();
		personRepository.printListPeople(people);
		assertThat(people).isSortedAccordingTo(Comparator.comparing(Person::getFullname).reversed());
	}


	@Test	
	public void getSortedJobs(){
		List<String> sortedJobs = personRepository.getSortedJobs();
		sortedJobs.forEach(System.out::println); 

		assertThat(sortedJobs).isSortedAccordingTo(Comparator.naturalOrder())
		.contains("Pole Dancer", "Bartender", "Developer", "Personal Trainer", "Soldier", "Teacher", "Taxi Driver", "Nurse", "Musician");

	}

	@Test
	public void sortPeopleByFullNameReversed() {
		List<Person> sortedPeople = personRepository.sortPeopleByFullNameReversed();
		sortedPeople.forEach(person -> System.out.println(person.getFullname()));
		assertThat(sortedPeople).isSortedAccordingTo(Comparator.comparing(Person::getFullname).reversed());
	}

	@Test	
	public void getSortedCities(){
		List<String> sortedCities = personRepository.getSortedCities();
		sortedCities.forEach(System.out::println);  //In theo tất các thành phố ra để kiểm tra xem có sắp xếp không
	/*
		Cách này viết dài
		assertThat(sortedCities).contains("Paris", "Dubai");
		assertThat(sortedCities).isSortedAccordingTo(Comparator.naturalOrder());*/

		//Cách này chain các điều kiện test với nhau ngắn gọn và đẹp hơn
		assertThat(sortedCities).isSortedAccordingTo(Comparator.naturalOrder())
		.contains("Berlin", "Budapest", "Buenos Aires", "Copenhagen", "Hanoi", "Jakarta","Mexico City","Zagreb");
	}

	@Test
	public void groupPeopleByCity() {
		var city_people = personRepository.groupPeopleByCity();
		List<Person> people = personRepository.getAll();
		List<String> cities = people.stream()
		.map(Person::getCity) //ánh xạ trường City ra stream
		.distinct() //chọn duy nhất
		.peek(System.out::println)  //Dùng peek để in ra debug
		.toList(); //chuyển sang dạng List
		
		//giờ thì kiểm tra
		assertThat(cities).containsAll(city_people.keySet());
	}

	@Test
	public void findTop5PopulationCitis() {
		List<Entry<String, Long>>  result = personRepository.findTop5PopulationCitis();
		//Cần kiểm tra kết quả trả về có 5 phần tử và được sắp xếp theo Map.Entry value
		//Tham khảo https://stackoverflow.com/questions/46904399/comparator-comparing-for-map-entry-in-java-8
		assertThat(result).hasSize(5).isSortedAccordingTo(Comparator.comparing(Map.Entry<String, Long>::getValue).reversed());
	}

	@Test
	public void prototype_findTopJobInCity() {
		List<Person> people = personRepository.getAll();
		//Multi level grouping https://www.javacodegeeks.com/2016/03/multi-level-grouping-streams.html
		Map<String, Map<String, Long>> result = people.stream().collect(
			Collectors.groupingBy(Person::getCity, 
			Collectors.groupingBy(Person::getJob, Collectors.counting())));

		HashMap<String, Entry<String, Long>> topJobInCity = new HashMap<>();

		for (var entry : result.entrySet()) {			
			var max_job_in_city = entry.getValue().entrySet().stream().max(Comparator.comparing(Map.Entry<String, Long>::getValue)).get();
			topJobInCity.put(entry.getKey(), max_job_in_city);
		}
	}

	@Test
	public void findTopJobInCity() {
		List<Entry<String, Entry<String, Long>>> result = personRepository.findTopJobInCity();
		var city_people = personRepository.groupPeopleByCity();
		assertThat(result.size()).isEqualTo(city_people.size());
		System.out.println(result);
	}

	@Test
	//Chỉ dùng để kiểm tra hàm tính tuổi
	public void testGetAge() {
		Person john  = new Person();
		john.setBirthday("1975/11/27");
		assertThat(john.getAge()).isEqualTo(45);

		Person jim  = new Person();
		jim.setBirthday("1975/11/20");
		assertThat(jim.getAge()).isEqualTo(46);
	}


	@Test
	//Cần viết thử prototype để chạy 
	public void prototypeTop5HighestSalaryCities() {
		List<Person> people = personRepository.getAll();
		var city_average_salary = people.stream().collect(
			Collectors.groupingBy(Person::getCity, Collectors.averagingInt(Person::getSalary)))
			.entrySet().stream()
			.sorted(Comparator.comparing(Map.Entry<String, Double>::getValue).reversed())
			.peek(System.out::println)
			.limit(5)
			.toList();
	}

	@Test
	public void prototypeRatioMaleFemalePerCity() {
		List<Person> people = personRepository.getAll();
		var group_city_list_male = people.stream()
		.collect(Collectors.groupingBy(Person::getCity, Collectors.filtering(p -> p.getGender().equals("Male"), Collectors.toList())));

		var group_city_count_male = people.stream()
		.collect(Collectors.groupingBy(Person::getCity, Collectors.filtering(p -> p.getGender().equals("Male"), Collectors.counting())));

		System.out.println(group_city_count_male);

		var group_city = people.stream()
		.collect(Collectors.groupingBy(Person::getCity));

		HashMap<String, Double> city_male_female_ratio = new HashMap<>();

		for (var person_in_city : group_city.entrySet()) {
			long males = person_in_city.getValue().stream().filter(p -> p.getGender().equals("Male")).count();
			long population_in_city = person_in_city.getValue().size();
			city_male_female_ratio.put(person_in_city.getKey(), (double)males/(population_in_city - males));
		}
		System.out.println(city_male_female_ratio);
	}

	@Test
	public void teeRatioMaleFemale() {
		List<Person> people = personRepository.getAll();
		var maleVsFemale = people.stream()
				.collect(Collectors.teeing(
					Collectors.filtering(e -> e.getGender().equals("Male"), Collectors.counting()),
					Collectors.filtering(e -> e.getGender().equals("Female"), Collectors.counting()), 
					(male, female) -> {
							HashMap<String, Object> map = new HashMap<>();
							map.put("male", male);
							map.put("female", female);
							return map;
						}));

		System.out.println(maleVsFemale);
	}

	@Test
	public void teeRatioMaleFemalePerCity() {
		List<Person> people = personRepository.getAll();
		var maleVsFemalePerCity = people.stream()
				.collect(Collectors.groupingBy(Person::getCity,
						Collectors.teeing(
							Collectors.filtering(e -> e.getGender().equals("Male"), Collectors.counting()),
							Collectors.filtering(e -> e.getGender().equals("Female"), Collectors.counting()), 
							(male, female) -> {
									return (double)male / female;
								})));

		System.out.println(maleVsFemalePerCity);
	}

	
}
