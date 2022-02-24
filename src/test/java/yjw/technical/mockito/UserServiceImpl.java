package yjw.technical.mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User signUp(final SignUpDTO signUpDTO){
        final User user = User.builder()
                .email(signUpDTO.getEmail())
                .pw(passwordEncoder.encode(signUpDTO.getPw()))
                //.pw(signUpDTO.getPw())
                .build();
        System.out.println("signUp : " + signUpDTO.toString());
        System.out.println("signUp user : " + user.toString());
        return userRepository.save(new User("test4@test.test", "test4"));
    }

    @Override
    public boolean isEmailExists(String email) {
        // TODO Auto-generated method stub
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<User> findAll() {
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
                        .age(3).build()
        );
    }

}
