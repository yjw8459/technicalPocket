package yjw.technical.mockito;

import java.util.List;

public interface UserService {

    User signUp(SignUpDTO signUpDTO);

    public boolean isEmailExists(String email);

    public List<User> findAll();

}

