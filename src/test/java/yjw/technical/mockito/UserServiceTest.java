package yjw.technical.mockito;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Spy    //Mock되지 않는 메소드는 실제 메소드로 동작
    private BCryptPasswordEncoder passwordEncoder;

    private MockMvc mockMvc;


    @DisplayName("signIn")
    @Test
    void signUp(){
        //given
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final SignUpDTO signUpDTO = signUpDTO();
        final String encryptedPw = encoder.encode(signUpDTO.getPw());

        //when
        doReturn(new User(signUpDTO.getEmail(), encryptedPw, 1)).when(userRepository).save(any(User.class));
        final User user = userService.signUp(signUpDTO);

        //then
        assertThat(user.getEmail()).isEqualTo(signUpDTO.getEmail());
        assertThat(encoder.matches(signUpDTO.getPw(), user.getPw())).isTrue();

        //verify Mock된 객체의 해당 메소드가 몇 번 호출되었는지 검증한다.
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(any(String.class));

    }

    @DisplayName("이메일 중복 여부")
    @Test
    public void isEmailDuplicated(){
        //given
        final SignUpDTO signUpDTO = signUpDTO();
        doReturn(true).when(userRepository).existsByEmail(signUpDTO.getEmail());

        //when
        final boolean isDuplicated = userService.isEmailExists(signUpDTO.getEmail());

        //then
        assertThat(isDuplicated).isTrue();
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
        final MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
        final UserListResponseDTO response = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), UserListResponseDTO.class);
        assertThat(response.getUserList().size()).isEqualTo(5);
    }


    private SignUpDTO signUpDTO() {
        return new SignUpDTO("test@test.test", "test");
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
