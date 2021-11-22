package vn.techmaster.learncollection;

import java.util.List;
import java.util.Set;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

import static org.assertj.core.api.Assertions.assertThat;

//Lấy cảm hứng từ bài viết này https://howtodoinjava.com/java8/intstream-examples/#create
//Ăn quả nhớ kẻ trồng cây, đừng copy code người khác mà không ghi chú.
public class InStreamTest {

  @Test
  public void sumInstream() {
    var instream = IntStream.of(1, 2, 3, 4, 5, 6);
    assertThat(instream.sum()).isEqualTo(21);
  }

  @Test
  public void iterateInstream() {
    var result = IntStream.iterate(0, i -> i + 2).limit(10);
    // Bạn có thấy khả năng nối chuỗi trong lệnh test của AssertJ không. Đó chính là
    // điểm mạnh của nó đấy
    assertThat(result).hasSize(10).allSatisfy(item -> {
      assertThat(item % 2).isZero();
    });
  }

  @Test
  public void generateInstream() {
    IntStream result = IntStream.generate(() -> {
      return (int) (Math.random() * 100);
    }).limit(8);

    assertThat(result).hasSize(8).allSatisfy(item -> {
      assertThat(item).isLessThan(100);
    });
  }

  @Test
  public void rangeInstream() {
    IntStream result = IntStream.range(5, 10);
    assertThat(result).hasSize(5).allSatisfy(item -> {
      assertThat(item >= 5 && item <= 10).isTrue();
    });
  }

  @Test
  public void filterInstream() {
    IntStream stream = IntStream.range(1, 100);
    List<Integer> primes = stream.filter(InStreamTest::isPrime).boxed().toList();
    assertThat(primes).isNotEmpty().allSatisfy(item -> {
      assertThat(isPrime(item)).isTrue();
    });

  }

  public static boolean isPrime(int i) {
    IntPredicate isDivisible = index -> i % index == 0;
    return i > 1 && IntStream.range(2, i).noneMatch(isDivisible);
  }

  // Chạy thử mã nguồn từ link này
  // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/IntStream.html
  public enum Color {
    RED, GREEN, BLUE, BLACK, WHITE, YELLOW
  }

  @Data
  @AllArgsConstructor
  class Widget {
    private Color color;
    private int weight;
  }

  @Test
  public void testStreamMapFilter() {
    var widgets = List.of(new Widget(Color.RED, 10), new Widget(Color.BLACK, 2), new Widget(Color.RED, 5),
        new Widget(Color.WHITE, 1));
    int sum = widgets.stream().filter(w -> w.getColor() == Color.RED) // Lọc
        .mapToInt(w -> w.getWeight()) // ánh xạ sang Instream
        .sum(); // tính tổng
    assertThat(sum).isEqualTo(15);
  }

  @Test
  public void sequentialVsParallel() {
    //Tham khảo https://www.baeldung.com/java-when-to-use-parallel-stream
    List<Integer> listOfNumbers = List.of(1, 2, 3, 4, 5, 6);
    
    //Chỉ dùng 1 thread để cùng chạy
    Set<String> result1 =  listOfNumbers.stream()
    .map(number -> Thread.currentThread().getName()).collect(Collectors.toSet());
    System.out.println(result1);


    //Sẽ dùng nhiều thread để cùng chạy
    Set<String> result2 =  listOfNumbers.stream().parallel()
    .map(number -> Thread.currentThread().getName()).collect(Collectors.toSet());
    System.out.println(result2);

    assertThat(result1).hasSize(1);
    assertThat(result2.size()).isGreaterThan(1);
  }

  @Test
  public void distict() {
    List<Integer> listOfNumbers = List.of(1, 2, 1, 3, 4, 3, 5, 6);
    var result = listOfNumbers.stream().distinct().toList();
    var setints = listOfNumbers.stream().collect(Collectors.toSet());
    assertThat(result.size()).isEqualTo(setints.size()).isEqualTo(6);    
  }

}
