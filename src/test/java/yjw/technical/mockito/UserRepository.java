package yjw.technical.mockito;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public User save(User user){
        System.out.println("save");
        return user;
    }

    public boolean existsByEmail(String email) {
        if ( "test1@test.test".equals(email) )  return true;
        return false;
    }
}
