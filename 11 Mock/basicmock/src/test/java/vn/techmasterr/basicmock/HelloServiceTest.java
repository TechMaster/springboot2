package vn.techmasterr.basicmock;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmasterr.basicmock.repository.HelloRepo;
import vn.techmasterr.basicmock.repository.HelloRepoImpl;
import vn.techmasterr.basicmock.service.HelloService;
import vn.techmasterr.basicmock.service.HelloServiceImpl;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class HelloServiceTest {
  @Mock
  private HelloRepo helloRepo;

  @InjectMocks // auto inject helloRepository
  private HelloService helloService = new HelloServiceImpl();

  
  @Test
  void testSay() {
    when(helloRepo.hi()).thenReturn("Mock is great");

    String result = helloService.say();
    assertThat(result).isEqualTo("Mock is great");

    verify(helloRepo, times(1)).hi(); // đảm bảo helloRepo.hi() được gọi ít nhất một lần
  }

  @Test
  void testCallRealMethod() {
    //Để gọi được doCallRealMethod, cần phải mock concrete class chứ không phải interface
    HelloRepo helloRepo = mock(HelloRepoImpl.class);
    doCallRealMethod().when(helloRepo).foo();
    
    when(helloRepo.hi()).thenReturn("Mock is great");
    
    helloRepo.foo();
    assertThat(helloRepo.hi()).isEqualTo("Mock is great");
  }

  @Test
  void testDoNothing() {
    //Đừng làm gì khi được gọi, đôi khi cũng hữu ích, để không tạo ra hiệu ứng phụ hay thay đổi dữ liệu
    doNothing().when(helloRepo).foo();
    helloRepo.foo();
  }

  @Test
  void testDoAnswer() {
    doReturn("BII").when(helloRepo).hi();

    doAnswer(i -> {
      return "BOO";
    }).when(helloRepo).hi();


    when(helloRepo.hi()).thenReturn("BAA");
    System.out.println(helloRepo.hi());
  }


  @Test
  void testBar() {
    HelloRepo helloRepo = mock(HelloRepoImpl.class);

    when(helloRepo.bar(anyInt())).thenAnswer(invocation ->{
      int number = (int)invocation.getArguments()[0];
      var list = new ArrayList<String>(List.of("a", "b", "c", "d", "e"));
      if (number < 5) {
        return list.subList(0, number); //Giả lập
      } else {
        return invocation.callRealMethod();  //Gọi hàm thật
      }
    });

    var r4 = helloRepo.bar(4);
    System.out.println(r4);

    var r6 = helloRepo.bar(6);
    System.out.println(r6);

    verify(helloRepo, times(1)).bar(4);
    verify(helloRepo, times(1)).bar(6);
  }
}
