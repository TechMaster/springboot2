package vn.techmaster.learncollection;
//https://www.baeldung.com/java-8-collectors

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

public class CollectorTest {
  
  @Test
  public void toList() {
    Map<String, Integer> scores = Map.of("John", 5, "Anna", 8, "Tom", 10, "James", 9);
    List<Entry<String, Integer>> result = scores.entrySet().stream().collect(Collectors.toList());
    List<Entry<String, Integer>> result2 = scores.entrySet().stream().toList();
  }

  @Test
  public void toSet() {
    List<String> names = List.of("John", "Adam", "Henry", "Anna", "Henry");
    Set<String> result = names.stream().collect(Collectors.toSet());
  }


  @Test
  public void join() {
    List<String> names = List.of("John", "Adam", "Henry", "Anna", "Henry");
    var result = names.stream().collect(Collectors.joining(", "));
    System.out.println(result);
  }

  @Test
  public void averagingDouble() {
    List<Integer> numbers = List.of(1, 3, -4, 2, -6, 7, -8, 9);
    var result = numbers.stream().collect(Collectors.averagingDouble(num->Math.abs(num)));
    System.out.println(result);    
  }

  @Test
  public void filtering_toSet() {
    List<Integer> names = List.of(1, 3, -4, 2, -6, 7, -8, 1, 2, 9);
    names.stream()
    .collect(Collectors.filtering(p -> p > 0, Collectors.toSet()))
    .forEach(System.out::println);
  }

  @Data
  @AllArgsConstructor
  class Employee {
    private String name;
    private String dept;
    private int salary;
  }

  @Test
  public void collectingAndThen() {
    List<Employee> employees = List.of(
      new Employee("John", "sales", 1000),
      new Employee("Anna", "sales", 1200),
      new Employee("Bob", "tech", 800),
      new Employee("Rock", "hr", 700),
      new Employee("Bill", "tech", 200),
      new Employee("Van", "sales", 300)
    );
    
    String name_emp_with_highest_salary = employees.stream()
    .collect(Collectors.collectingAndThen(
      Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
      (Optional<Employee> emp)-> emp.isPresent() ? emp.get().getName() : "none")
      );
    
      System.out.println(name_emp_with_highest_salary);
  }

  @Test
  public void filter_then_groupby() {
    List<Employee> employees = List.of(
      new Employee("John", "sales", 1000),
      new Employee("Anna", "sales", 1200),
      new Employee("Bob", "tech", 800),
      new Employee("Rock", "hr", 700),
      new Employee("Bill", "tech", 200),
      new Employee("Van", "sales", 300)
    );
    
    var res = employees.stream()
    .collect(
      Collectors.collectingAndThen(
        Collectors.filtering(p -> p.getSalary() > 600, Collectors.toList()),
        p -> p.stream().collect(Collectors.groupingBy(Employee::getDept))));
    System.out.println(res);

    

    // Hỏi có cách nào dễ hiểu hơn cách này không?
    // Tips: đừng viết những câu lệnh đánh đố, rất khó bảo trì sau này
    var res2 = employees.stream()
    .filter(p -> p.getSalary() > 600)  //Lọc
    .collect(Collectors.groupingBy(Employee::getDept)); //Rồi phân nhóm
    System.out.println(res2);
  }


  @Test
  public void partitioningBy() {
    List<Employee> employees = List.of(
      new Employee("John", "sales", 1000),
      new Employee("Anna", "sales", 1200),
      new Employee("Bob", "tech", 800),
      new Employee("Rock", "hr", 700),
      new Employee("Bill", "tech", 200),
      new Employee("Van", "sales", 300)
    );
    /*
    Chia thành 2 nhóm nhân viên: lương lớn 600 và phần còn lại
    */
    var res = employees.stream().collect(Collectors.partitioningBy(p -> p.getSalary() > 600 ));

    res.get(true).forEach(System.out::println);
  }

  @Test
  /*
  Thông tin thống kê : số phần tử, tổng, min, trung bình, max
  */
  public void summarizingInt() {
    List<Integer> numbers = List.of(1, 3, -4, 2, -6, 7, -8, 9);
    var result = numbers.stream().collect(Collectors.summarizingInt(p -> p));
    System.out.println(result);
  }

  @Test
  /*
  https://howtodoinjava.com/java12/collectors-teeing-example/
  */
  public void tee() {
    List<Employee> employees = List.of(
      new Employee("John", "sales", 1000),
      new Employee("Anna", "sales", 1200),
      new Employee("Bob", "tech", 800),
      new Employee("Rock", "hr", 700),
      new Employee("Bill", "tech", 200),
      new Employee("Van", "sales", 300)
    );

    HashMap<String, Employee> result = employees.stream().collect( 
                            Collectors.teeing(
                                    Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
                                    Collectors.minBy(Comparator.comparing(Employee::getSalary)),
                                    (e1, e2) -> {
                                        HashMap<String, Employee> map = new HashMap();
                                        map.put("MAX", e1.get());
                                        map.put("MIN", e2.get());
                                        return map;
                                    }
                            ));
         
        System.out.println(result);
  }

  //Homework: hãy tìm tỷ lệ số nhân viên sales / số nhân viên tech
}


