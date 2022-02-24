package yjw.technical.mockito;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
public class SignUpDTO {

    private String email;
    private String pw;

    public SignUpDTO(String email, String pw) {
        this.email = email;
        this.pw = pw;
    }
}
