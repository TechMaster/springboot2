package vn.techmaster.demojpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import vn.techmaster.demojpa.model.Car;
import vn.techmaster.demojpa.repository.CarRepository;
import vn.techmaster.demojpa.repository.MakerCount;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@DataJpaTest
@Sql({ "/car.sql" })  //Nạp dữ liệu car.sql vào để kiểm thử
public class CarRepositoryTest {
  @Autowired private CarRepository carRepository;
  @Autowired private EntityManager em;

  //Khi nào dùng EntityManager và khi nào dùng Repository?

  @Test
  void testQueryUsingEntityManager() {
    //Chú ý ở đây phải dùng tên của entity chứ không không dùng tên bảng trong CSDL
    Query jpqlQuery = em.createQuery("SELECT o FROM oto o WHERE o.id=:id");  //Đây là JPQL
    jpqlQuery.setParameter("id", 1L);
    Car car = (Car) jpqlQuery.getSingleResult();
    System.out.println(car);
  }

  @Test
  void testTypedQuery() {
    TypedQuery<Car> typedQuery = em.createQuery("SELECT c FROM oto c WHERE c.id=:id", Car.class);
    typedQuery.setParameter("id", 1L);
    Car car = typedQuery.getSingleResult();  // Khi dùng TypedQuery thì không cần ép kiểu
    System.out.println(car);
  }

  @Test
  void testNamedQuery() { //Không khuyến cáo dùng vì nó không tách bạch code
    Query namedQuery = em.createNamedQuery("Car.findById");
    namedQuery.setParameter("id", 1L);
    Car car =  (Car) namedQuery.getSingleResult();
    System.out.println(car);
  }

  @Test
  void testNativeQuery() {
    //Native SQL Query sẽ gửi trực tiếp xuống CSDL
    //Khuyến cáo tránh dùng vì Native SQL có thể không xử lý được lỗ hổng bảo mật SQL Inject
    //Không tương thích tốt với nhiều CSDL khác nhau, có nghĩa chỉ viết cụ thể cho 1 CSDL
    Query nativeQuery = em.createNativeQuery("SELECT * FROM car WHERE id=:id", Car.class);  //Không dùng oto mà dùng car
    nativeQuery.setParameter("id", 1L);
    Car car = (Car) nativeQuery.getSingleResult();
    System.out.println(car);
  }

  @Test
  public void findByIdTest() {
    var car = carRepository.findById(1L);
    assertThat(car.get()).isNotNull();
  }

  @Test
  public void findByModelTest() {
    String model = "E-350 Super Duty";
    List<Car> cars = carRepository.findByModel(model);
    assertThat(cars.get(0).getModel()).isEqualTo(model);
  }

  @Test
  public void findByModelAndYear() {
    var car = carRepository.findByModelAndYear("Boxster", 2008);
    assertThat(car.get()).extracting("model", "year").containsExactly("Boxster", 2008);
  }

  @Test
  public void findByOrderByYearAscTest() {
    List<Car> cars = carRepository.findByOrderByYearAsc();
    assertThat(cars).isSortedAccordingTo(Comparator.comparing(Car::getYear));
  }

  @Test
  public void findByOrderByYearDscTest() {
    List<Car> cars = carRepository.findAll(Sort.by("year").descending());
    assertThat(cars).isSortedAccordingTo(Comparator.comparing(Car::getYear).reversed());
  }

  @Test
  public void findByOrderByModelYearAscTest() {
    List<Car> cars = carRepository.findAll(Sort.by("model", "year"));
    assertThat(cars).isSortedAccordingTo(Comparator.comparing(Car::getModel).thenComparing(Car::getYear));
  }

  @Test
  public void listCarIn2009Test() {
    List<Car> cars = carRepository.listCarIn2009();
    assertThat(cars).extracting("year").containsOnly(2009);
  }

  @Test
  public void listCarInYearTest() {
    List<Car> cars = carRepository.listCarInYear(2008);
    assertThat(cars).extracting("year").containsOnly(2008);
  }

  @Test
  public void countByMakerTest() {
    List<MakerCount> makerCounts = carRepository.countByMaker();
    makerCounts.forEach(System.out::println);
    assertThat(makerCounts).isSortedAccordingTo(Comparator.comparing(MakerCount::maker));
  }

  @Test
  public void top5CarMakerTest() {
    List<MakerCount> top5Makers = carRepository.topCarMaker(PageRequest.of(1, 5));
    top5Makers.forEach(System.out::println);
    assertThat(top5Makers).hasSize(5);

  }

}
