package vn.techmasterr.basicmock;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import vn.techmasterr.basicmock.controller.HelloController;
import vn.techmasterr.basicmock.service.HelloService;


@WebMvcTest(HelloController.class)
public class HelloControllerTest {
  @Autowired
  private MockMvc mvc;  //Phải có đối tượng này

  @MockBean //Thử thay bằng @Mock xem thế nào
  private HelloService helloService;

  @Test
  public void testHello() throws Exception {
    when(helloService.say()).thenReturn("Umbala");

    mvc.perform(MockMvcRequestBuilders.get("/hi")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().string("Umbala"));
  }
}

/*
Hãy xem thêm
//https://rieckpil.de/guide-to-testing-spring-boot-applications-with-mockmvc/
*/
