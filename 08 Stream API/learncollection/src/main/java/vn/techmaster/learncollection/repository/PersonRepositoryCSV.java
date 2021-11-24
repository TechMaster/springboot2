package vn.techmaster.learncollection.repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import vn.techmaster.learncollection.model.Person;

@Repository
public class PersonRepositoryCSV implements PersonRepositoryInterface {
  private ArrayList<Person> people;

  @Autowired
  public PersonRepositoryCSV(@Value("${csvFile}") String csvFile) {
    people = new ArrayList<>();
    loadData(csvFile);
  }

  private void loadData(String csvFile) {
    try {
      File file = ResourceUtils.getFile("classpath:static/" + csvFile);
      CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
      CsvSchema schema = CsvSchema.emptySchema().withHeader(); // Dòng đầu tiên sử dụng làm Header
      ObjectReader oReader = mapper.readerFor(Person.class).with(schema); // Cấu hình bộ đọc CSV phù hợp với kiểu
      Reader reader = new FileReader(file);
      MappingIterator<Person> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
      while (mi.hasNext()) {
        Person person = mi.next();
        people.add(person);
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  @Override
  public void printListPeople(List<Person> persons) {
    for (var person: persons) {
      System.out.println(person);
    }
  }  

  @Override
  public List<Person> getAll() {
    return people;
  }

  @Override
  public List<Person> sortPeopleByFullName() {
    return people.stream().sorted(Comparator.comparing(Person::getFullname)).collect(Collectors.toList());
  }


  @Override
  public List<Person> sortPeopleByFullNameReversed() {
    return people.stream().sorted(Comparator.comparing(Person::getFullname).reversed()).collect(Collectors.toList());
  }

  @Override
  public List<String> getSortedCities() {
    /*
     * return people.stream().sorted(Comparator.comparing(Person::getCity)).
     * map(Person::getCity).collect(Collectors.toList());
     */
    //return people.stream().map(Person::getCity).sorted().toList();
    var x = people.stream().map(Person::getCity).peek(System.out::println).sorted();

    return x.toList();
  }

  @Override
  public List<String> getSortedJobs() {
    return people.stream().map(Person::getJob).sorted().toList();
  }

  @Override
  public Map<String, List<Person>> groupPeopleByCity() {    
    return people.stream().collect(Collectors.groupingBy(Person::getCity));
  }

  //Tìm 5 thành phố có số người thuộc danh sách sinh sống đông nhất từ vị trí thứ 5 đến vị trí thứ 1
  @Override
  public List<Entry<String, Long>> findTop5PopulationCitis() {
    //Google Java stream group by count
    var cities_population = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));
    
    //Google Java stream sort by map value
    return cities_population.entrySet().stream()
    .sorted(Entry.<String, Long>comparingByValue().reversed()) 
    .limit(5).toList();
  }

  @Override
  public HashMap<String, Integer> findTop5Jobs() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Entry<String, Entry<String, Long>>> findTopJobInCity() {

		//Multi level grouping https://www.javacodegeeks.com/2016/03/multi-level-grouping-streams.html
		Map<String, Map<String, Long>> result = people.stream().collect(
			Collectors.groupingBy(Person::getCity, 
			Collectors.groupingBy(Person::getJob, Collectors.counting())));

    //Đến đoạn này nếu khó quá đừng cố nối chuỗi các lệnh StreamAPI. Chúng ta cần code dễ hiểu, dễ debug hơn là code tất cả trên 1 dòng

		HashMap<String, Entry<String, Long>> topJobInCity = new HashMap<>();   
  
		for (var entry : result.entrySet()) {			
			var max_job_in_city = entry.getValue().entrySet().stream().max(Comparator.comparing(Map.Entry<String, Long>::getValue));
			topJobInCity.put(entry.getKey(), max_job_in_city.get());
		}

    return topJobInCity.entrySet().stream().toList();
	
  }

  @Override
  public Map<String, Integer> groupJobByCount(){
    return null;    
  } 

  @Override
  public Map<String, Double> averageCityAge() {
    return people.stream().collect(
			Collectors.groupingBy(Person::getCity, Collectors.averagingInt(Person::getAge)));
  }

  @Override
  public Map<String, Double> averageJobAge() {
    return people.stream().collect(
			Collectors.groupingBy(Person::getJob, Collectors.averagingInt(Person::getAge)));
  }

  @Override
  public Map<String, Double> averageJobSalary() {
    return people.stream().collect(
			Collectors.groupingBy(Person::getJob, Collectors.averagingInt(Person::getAge)));
  }

  @Override
  public List<String> find5CitiesHaveMostSpecificJob(String job) {
    
    return null;
  }

  @Override
  public List<Entry<String, Double>> top5HighestSalaryCities() {   
		return people.stream().collect(
			Collectors.groupingBy(Person::getCity, Collectors.averagingInt(Person::getSalary)))
			.entrySet().stream()
			.sorted(Comparator.comparing(Map.Entry<String, Double>::getValue).reversed())
			.peek(System.out::println)
			.limit(5)
			.toList();
  }
  

  @Override
  public HashMap<String, Float> ratioMaleFemalePerCity() {
    
    return null;
  }

  @Override
  public <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
    Map<Object, Boolean> map = new ConcurrentHashMap<>();
    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

  
}
