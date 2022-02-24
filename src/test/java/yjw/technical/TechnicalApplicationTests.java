package yjw.technical;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import yjw.technical.mockito.UserController;
import yjw.technical.mockito.UserService;


@SpringBootTest
class TechnicalApplicationTests {

	@InjectMocks    //Mock 객체 userService 주입
	private UserController userController;

	@Mock   //가짜 Mock 주입
	private UserService userService;

	private MockMvc mockMvc;

	@Test
	void contextLoads() {

	}

}
