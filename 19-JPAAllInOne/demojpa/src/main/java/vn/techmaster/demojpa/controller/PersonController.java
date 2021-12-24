package vn.techmaster.demojpa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.demojpa.model.Person;
import vn.techmaster.demojpa.model.id.TableID;
import vn.techmaster.demojpa.repository.PersonRepository;

@RestController
@RequestMapping("/api/person")
public class PersonController {
  @Autowired
  private PersonRepository personRepo;

  @Autowired
  private EntityManager em;

  @GetMapping("/{id}")
  public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
    return ResponseEntity.ok().body(personRepo.findById(id).orElseThrow(() -> {
      return new RuntimeException("Person not found");
    }));
  }

  @GetMapping("/name/{fullname}")
  public ResponseEntity<List<Person>> findPersonByFullname(@PathVariable("fullname") String fullname) {
    return ResponseEntity.ok().body(personRepo.findByFullnameContainingIgnoreCase(fullname));
  }

  @GetMapping("/name_city")
  // http://localhost:8080/api/person/name_city?fullname=ling&city=Mexico%20City
  public ResponseEntity<List<Person>> findPersonByFullnameAndCity(
      @RequestParam("fullname") String fullname,
      @RequestParam("city") String city) {
    return ResponseEntity.ok().body(personRepo.findByFullnameContainingIgnoreCaseAndCity(fullname, city));
  }

  @GetMapping("/salarybetween/{from}/{to}")
  // http://localhost:8080/api/person/salarybetween/500/1000
  public ResponseEntity<List<Person>> findPersonHasSalaryBetween(
      @PathVariable("from") int from,
      @PathVariable("to") int to) {
    return ResponseEntity.ok().body(
        personRepo.findBySalaryBetweenOrderBySalaryAsc(from, to));
  }

  @GetMapping("/birthdaybefore/{date}")
  //http://localhost:8080/api/person/birthdaybefore/2000-01-01
  public ResponseEntity<List<Person>> findPersonHasBirthdayBefore(
    @PathVariable("date") String date) throws ParseException {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      var datebefore  = formatter.parse(date);

      personRepo.flush();
    return ResponseEntity.ok().body(
      personRepo.findByBirthdayBeforeOrderByBirthdayDesc(datebefore)
      );
  }

  @GetMapping("/birthdayafter/{date}")
  //http://localhost:8080/api/person/birthdayafter/2000-01-01
  public ResponseEntity<List<Person>> findPersonHasBirthdayAfter(
    @PathVariable("date") String date) throws ParseException {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      var dateafter  = formatter.parse(date);
    return ResponseEntity.ok().body(
      personRepo.findByBirthdayAfterOrderByBirthdayAsc(dateafter)
      );
  }

  //http://localhost:8080/api/person/existbyname/Cassandra%20Skurm
  @GetMapping("/existbyname/{fullname}")
  public String  existByNamee(@PathVariable("fullname") String fullname) {
    if (personRepo.existsByFullname(fullname) ) {
      return  "Person with " + fullname + " exists";
    } else {
      return  "Person with " + fullname + " doest not exist";
    }
  }

  //http://localhost:8080/api/person/existbynamelike/Cassandra%20Skurm
  @GetMapping("/existbynamelike/{fullname}")
  public String  existByNameLike(@PathVariable("fullname") String fullname) {
    if (personRepo.existsByFullnameLike("%" + fullname + "%") ) {
      return  "Person with full name like " + fullname + " exists";
    } else {
      return  "Person with full name like " + fullname + " doest not exist";
    }
  }

  @GetMapping("/top5salary")
  //http://localhost:8080/api/person/top5salary
  public ResponseEntity<List<Person>> top5salary() {
    return ResponseEntity.ok().body(personRepo.findTop5ByOrderBySalaryDesc());
  }

  @GetMapping("/top5salaryjob/{job}")
  //http://localhost:8080/api/person/top5salaryjob/Developer
  public ResponseEntity<List<Person>> top5salarybyjob(@PathVariable("job") String job) {
    var peopleWithSameJob = personRepo.findByJob(job);
    var result = peopleWithSameJob.stream()
    .sorted(Comparator.comparing(Person::getSalary).reversed())
    .limit(5).toList();
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/countbyfullnameandjob")
  //http://localhost:8080/api/person/countbyfullnameandjob?fullname=Ivie&job=Developer
  public long countByFullnameLikeAndJob(@RequestParam("fullname") String fullname, @RequestParam("job") String job) {
    return personRepo.countByFullnameLikeAndJob("%" + fullname + "%", job);
  }

  @DeleteMapping("/delete/{id}")
  //http://localhost:8080/api/person/countbyfullnameandjob?fullname=Ivie&job=Developer
  public void deletebyid(@PathVariable("id") long id) {
    personRepo.deleteById(id);
  }

  @GetMapping("/create")
  @Transactional
  public void createSomeData() {
    TableID r1 = new TableID();
    r1.setName("Buratino");
    em.persist(r1);
    //Long id1 = (Long) em.persistAndGetId(r1);
    System.out.println(r1.getId());

    TableID r2 = new TableID();
    r2.setName("Buratino2");
    //Long id2 = (Long) testEntityManager.persistAndGetId(r2);
    em.persist(r2);
    System.out.println(r2.getId());
    em.flush();
  }

 
}
