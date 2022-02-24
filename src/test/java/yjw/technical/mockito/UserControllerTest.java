package yjw.technical.mockito;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
/**
 * 불필요한 Stub가 발생할 경우 strictness.lenient 에러가 발생하는데 strictness를 설정하면
 * 위와 같은 제약을 느슨하게 허용해줌
 */
//@MockitoSettings(strictness = Strictness.LENIENT)
public class UserControllerTest {

    @InjectMocks    //Mock 객체 userService 주입
    private UserController userController;

    @Mock   //가짜 Mock 주입
    private UserService userService;

    private MockMvc mockMvc;

    //초기화
    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @DisplayName("signUpSucess")
    @Test
    public void signUpSuccess() throws Exception{
        //given
        final SignUpDTO signUpDTO = signUpDTO();
        /**
         * Stub은 메서드에 대한 결과값을 지정하는 것이다.
         * 예를 들어 signUp메서드가 호출되지 않는 경우 signUp에 대한 Stub은 불필요하기 때문에 제거해야 한다.
         */
        doReturn(false).when(userService).isEmailExists(signUpDTO.getEmail());
        doReturn(new User("test4@test.test", "test4"))
                .when(userService).signUp(any(SignUpDTO.class));//any : 어떠한 변수로 처리함을 뜻함.

        /**
         * when mockMvc에 데이터와 함께 POST 요청을 보낸다.
         * 보내는 데이터는 객체가 아닌 JSON이어야 한다.
         * 때문에, 별도의 변환이 필요하고, Gson을 활용하여 변환한다.
         * new Gson().toJson(signUpDTO) : signUpDTO를 JSON으로 변환
         *
         * mockMvc의 perform에 대한 정보를 작성하여 넘겨주어야 한다.
         * 요청 정보를 작성하기 위해서는 MockMvcRequestBuilders를 사용해야 한다.
         * 요청 메소드 종류, 내용, 파라미터를 설정할 수 있다.
         */
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(signUpDTO))
        );

        /**
         * then : 호출된 결과를 검증
         *
         * 회원가입 API 호출 결과로 200Response와 JWT 토큰을 발급받고 있고 이를 검증한다.
         */
        final MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();//200 Response 검증
        //final MvcResult mvcResult = resultActions.andExpect(status().isBadRequest()).andReturn();
        final String token = mvcResult.getResponse().getContentAsString();
        assertThat(token).isNotNull();
    }

    @DisplayName("existEmail")
    @Test
    public void signUpFailByDuplicatedEmail() throws Exception{
        //given
        final SignUpDTO signUpDTO = signUpDTO();
        //doReturn을 true로 설정하면 userService.isEmailExists는 true를 반환하도록 설정된다.
        doReturn(true).when(userService).isEmailExists(signUpDTO.getEmail());

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(signUpDTO))
        );

        //then
        resultActions.andExpect(status().isBadRequest());   //400에러인지 검증
        //resultActions.andExpect(status().isOk());
    }

    @DisplayName("사용자 목록 조회")
    @Test
    public void getUserList() throws Exception{
        //given
        doReturn(userList()).when(userService).findAll();

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/user/list")
        );
        //then

        //andReturn으로 MvcResult 반환
        final MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
        //new Gson(String, type)으로 json -> Object 변환
        final UserListResponseDTO response = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), UserListResponseDTO.class);
        assertThat(response.getUserList().size()).isEqualTo(5); //갯수가 5개인지 검증

    }

    private SignUpDTO signUpDTO() {
        return new SignUpDTO("test4@test.test", "test4");
    }

    private List<User> userList() {
        return Arrays.asList(
                User.builder()
                        .email("test1@test.test")
                        .name("test1")
                        .age(1).build(),
                User.builder()
                        .email("test2@test.test")
                        .name("test2")
                        .age(2).build(),
                User.builder()
                        .email("test3@test.test")
                        .name("test3")
                        .age(3).build(),
                User.builder()
                        .email("test4@test.test")
                        .name("test4")
                        .age(4).build(),
                User.builder()
                        .email("test5@test.test")
                        .name("test5")
                        .age(5).build()
        );
    }
}
