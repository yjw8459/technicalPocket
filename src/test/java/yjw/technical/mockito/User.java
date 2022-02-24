package yjw.technical.mockito;

import lombok.*;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {

    private String email;

    private String name;

    private String pw;

    private int age;

    public User(String email, String pw, int age){
        this.email = email;
        this.pw = pw;
        this.age = age;
    }
    public User(String email, String pw){
        this.email = email;
        this.pw = pw;
    }
}
