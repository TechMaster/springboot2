package vn.techmaster.learncollection;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BasicStreamTest {

  @Test
  public void differentLoopMethods() {
    List<String> names = List.of("John", "Adam", "Henry", "Anna", "Tí", "Tèo");
    
    for (var name: names) { System.out.println(name); }
    for (int i = 0; i < names.size(); i++) { System.out.println(names.get(i)); }
    names.forEach(n -> System.out.println(n));    
    names.forEach(System.out::println);
    names.stream().forEach(System.out::println);

  }

  @Test
  public void parallel_forEachOrdered() {
    List<String> names = List.of("John", "Adam", "Henry", "Anna", "Tí", "Tèo");
    names.stream().parallel().forEach(System.out::println);
    // forEachOrdered luôn đảm bảo thứ tự giống với đầu vào còn forEach không đảm
    // bảo với parallel
    System.out.println("----------");
    names.stream().parallel().forEachOrdered(System.out::println);
  }

  @Test
  public void map() {
    List<String> names = List.of("John", "Adam", "Henry", "Anna", "Henry");
    names.stream().map(p -> p.toUpperCase()).forEach(System.out::println);
  }

  @Test
  public void distinct() {
    List<String> names = List.of("John", "Adam", "Henry", "Anna", "Henry");
    names.stream().distinct().forEach(System.out::println);
  }

  @Test
  public void filter() {
    List<String> names = List.of("John", "Adam", "Henry", "Anna", "Henry");
    names.stream().filter(p -> (p.contains("Hen") && (p.length()>3))).forEach(System.out::println);
  }

    // Phần này sinh viên tự thực hành theo link
  // https://stackify.com/streams-guide-java-8/
  @Test
  public void takeWhile() {
    List<Integer> ints = List.of(4, 4, 4, 5, 6, 7, 8, 9, 10);
    // Tiếp tục lấy phần tử trong stream chừng nào điều kiện (number > 2) còn đúng
    ints.stream().takeWhile(number -> (number / 4 ==1)).forEach(System.out::println);
  }

  @Test
  public void dropWhile() {
    List<Integer> ints = List.of(4, 4, 4, 5, 6, 7, 8, 9, 10);
    // Bỏ qua chừng nào điều kiện number % 2 == 0 còn đúng
    // 4, 4, 4 đều thoả mãn number % 2 == 0 nên bị bỏ qua drop
    // Nhưng đến 5 không còn thoả mãn
    ints.stream().dropWhile(number -> number % 2 == 0).forEach(System.out::println);
  }

  @Test
  public void anyMatch() {
    List<Integer> ints = List.of(4, 4, 4, 5, 6, 7, 8, 4, 10);
    Boolean res = ints.stream().anyMatch(p -> p % 5 == 0);
    if (res) {
      System.out.println("Tập có chứa ít nhất một số chia hết cho 5");
    }
  }

  @Test
  public void allMatch() {
    List<Integer> ints = List.of(2, 3, 5, 7, 11, 13, 17);
    Boolean res = ints.stream().allMatch(p -> InStreamTest.isPrime(p));
    if (res) {
      System.out.println("Tập chứa toàn các số nguyên tố");
    }
  }

  @Test
  public void noneMatch() {
    List<Integer> ints = List.of(4, 6, 8, 9, 12, 14, 15, 16, 18);
    Boolean res = ints.stream().noneMatch(p -> InStreamTest.isPrime(p));
    if (res) {
      System.out.println("Tập không chứa bất kỳ số nguyên tố nào");
    }
  }


  @Test
  public void max() {
    List<String> names = List.of("John", "Adam", "Henry", "Anna", "Henry");
    var result = names.stream().max(Comparator.naturalOrder());
    System.out.println(result); // John

    List<Invoice> invoices = List.of(
        new Invoice("A01", 200, 2), // 400
        new Invoice("A02", 100, 5), // 500
        new Invoice("A03", 150, 2)); // 300
    var invoice_with_max_price = invoices.stream().max(Comparator.comparing(Invoice::getPrice));
    System.out.println(invoice_with_max_price);    
  }

  @Test
  public void customComparator() {
    List<Invoice> invoices = List.of(
        new Invoice("A01", 200, 2), // 400
        new Invoice("A02", 100, 5), // 500
        new Invoice("A03", 150, 2)); // 300
    Comparator<Invoice> compareSubTotal = new Comparator<Invoice>() {
      public int compare(Invoice inv1, Invoice inv2) {
        return inv1.getPrice() * inv1.getQty() - inv2.getPrice() * inv2.getQty();
      }
    };
    var invoice_with_max_subtotal = invoices.stream().max(compareSubTotal);
    System.out.println(invoice_with_max_subtotal);

    //Cách viết tắt
    var invoice_with_max_subtotal2 = invoices.stream()
    .max((inv1, inv2) -> inv1.getPrice() * inv1.getQty() - inv2.getPrice() * inv2.getQty());
    System.out.println(invoice_with_max_subtotal2);
  }

  @Test
  public void sort() {
    List<String> names = List.of("John", "Adam", "Henry", "Anna", "Henry");
    names.stream().sorted().forEach(System.out::println);
  }

  @Test
  public void sort_by_length() {
    List<String> names = List.of("CCC", "A", "EEEEE", "BB", "DDDD");
    Comparator<String> compareStringLength = new Comparator<String>() {
      public int compare(String s1, String s2) {
        return s1.length() - s2.length();
      }
    };
    names.stream()
    .sorted(compareStringLength)    
    .forEach(System.out::println);

    //Cách viết tắt
    names.stream()
    .sorted((s1, s2) -> s1.length() - s2.length())    
    .forEach(System.out::println);

  }

  @Test
  // skip: bỏ qua n phần tử đầu tiên
  public void skip() {
    List<String> names = List.of("John", "Adam", "Henry", "Anna", "Henry");
    names.stream().skip(3).forEach(System.out::println);
  }

  @Test
  public void reduce_combine() {
    List<String> names = List.of("John", "Adam", "Henry", "Anna", "Henry");
    String combinedString = names.stream().reduce("", (result, element) -> result + element + " ");
    System.out.println(combinedString);
  }

  @Test
  public void reduce_find_longest() {
    List<String> names = List.of("John", "Adamson", "Thierry Henry", "Annaka", "Tí", "Tèo");
    String longestString = names.stream().reduce("", (word1, word2) -> word1.length() > word2.length() ? word1 : word2);
    System.out.println(longestString);
  }


  @Data
  @AllArgsConstructor
  class Invoice {
    String invoiceNo;
    int price;
    int qty;
  }

  @Test
  public void map_reduce() {
    List<Invoice> invoices = List.of(new Invoice("A01", 200, 2), // 400
        new Invoice("A02", 100, 5), // 500
        new Invoice("A03", 150, 2)); // 300

    int result = invoices.stream().map(invoice -> invoice.getPrice() * invoice.getQty()) // Tính subtotal
        .reduce(0, (total, subTotal) -> total + subTotal); // Tính tổng

    System.out.println(result);
  }

 

  @Test
  public void groupingBy() {
    List<String> names = List.of("John", "Adam", "Henry", "Anna", "Henry", "Anna", "Anna", "Ted");
    names.stream()
    .collect(Collectors.groupingBy(t->t, Collectors.counting()))
    .entrySet()
    .forEach(System.out::println);
  }

  /*
   * In Java 8, we can use the flatMap to convert the above 2 levels Stream into
   * one Stream level or a 2d array into a 1d array. Hỏi: Map khác gì FlatMap? The
   * map() method wraps the underlying sequence in a Stream instance, whereas the
   * flatMap() method allows avoiding nested Stream<Stream<R>> structure.
   */
  @Test
  public void flatMap() {
    List<List<String>> names = List.of(List.of("John", "Adam"), List.of("Anna", "Jane"), List.of("Tí", "Tèo"));
    // Duỗi phẳng
    names.stream().flatMap(Collection::stream).toList().forEach(System.out::println);
  }

  @Data
  class Developer {
    private Integer id;
    private String name;
    private Set<String> book;

    // getters, setters, toString
    public void addBook(String book) {
      if (this.book == null) {
        this.book = new HashSet<>();
      }
      this.book.add(book);
    }
  }

  @Test
  public void flatMap2() {
    // https://mkyong.com/java8/java-8-flatmap-example/
    Developer o1 = new Developer();
    o1.setName("mkyong");
    o1.addBook("Java 8 in Action");
    o1.addBook("Spring Boot in Action");
    o1.addBook("Effective Java (3nd Edition)");

    Developer o2 = new Developer();
    o2.setName("zilap");
    o2.addBook("Learning Python, 5th Edition");
    o2.addBook("Effective Java (3nd Edition)");

    List<Developer> devs = List.of(o1, o2);

    /*
     * Dòng lệnh này in ra 2 Set<String> [Spring Boot in Action, Effective Java (3nd
     * Edition), Java 8 in Action] [Learning Python, 5th Edition, Effective Java
     * (3nd Edition)]
     */
    devs.stream().map(x -> x.getBook()).forEach(System.out::println);

    Set<String> collect = devs.stream().map(x -> x.getBook()) // Stream<Set<String>>
        .flatMap(x -> x.stream()) // Stream<String>
        .filter(x -> !x.toLowerCase().contains("python")) // filter python book
        .collect(Collectors.toSet()); // remove duplicated

    collect.forEach(System.out::println);
  }

  @Data
  @AllArgsConstructor
  class Emp {
    private String name;
    private int age;
    private int salary;
  }
  @Test
	public void sortByAgeThenSalary() {
		List<Emp> emps = List.of(
      new Emp("John", 20, 2000),
      new Emp("Anna", 20, 1900),
      new Emp("Bill", 21, 2100),
      new Emp("Geogre", 21, 2300),
      new Emp("Tom", 18, 1500),
      new Emp("Bob", 30, 1200),
      new Emp("Jane", 30, 2600)
    );
		emps.stream()
		.sorted(Comparator.comparing(Emp::getAge)
    .thenComparing(Comparator.comparing(Emp::getSalary).reversed()))
		.limit(100)
		.forEach(System.out::println);
	}
 

}
