package vn.techmaster.demojpa.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.demojpa.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
  //findBy
  //  tìm theo primary key
  Optional<Person> findById(Long id);

  //  tìm theo thuộc tính khác
  List<Person> findByFullnameContainingIgnoreCase(String fullName);

  //  tìm theo thuộc tính khác
  List<Person> findByFullnameContainingIgnoreCaseAndCity(String fullName, String city);

  //  tìm lương theo dải và sắp xếp
  List<Person> findBySalaryBetweenOrderBySalaryAsc(int from, int to );

  List<Person> findByJobAndCity(String job, String city);

  // greater than Age hàm này  chạy  lỗi vì  age  chỉ  là  trường  compute
  //List<Person> findByAgeGreaterThan(int age);

  List<Person> findByBirthdayBeforeOrderByBirthdayDesc(Date date);

  List<Person> findByBirthdayAfterOrderByBirthdayAsc(Date date);

  boolean existsByFullname(String fullname);

  boolean existsByFullnameLike(String fullname);

  List<Person> findTop5ByOrderBySalaryDesc();

  List<Person> findByJob(String job);

  long countByFullnameLikeAndJob(String fullname, String job);

  //https://www.websparrow.org/spring/spring-data-jpa-derived-query-methods-example
  //https://attacomsian.com/blog/derived-query-methods-spring-data-jpa

  //countBy


  //deleteBy
}
