package yjw.technical.mockito;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserListResponseDTO {

    private List<User> userList;

}
