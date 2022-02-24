package yjw.technical.mockito;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value="/user/signUp", method=RequestMethod.POST)
    public ResponseEntity<String> signUp(@RequestBody final SignUpDTO signUpDTO){
        return userService.isEmailExists(signUpDTO.getEmail())
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(TokenUtils.generateJwtToken(userService.signUp(signUpDTO)));
    }

    @RequestMapping(value = "/user/list", method=RequestMethod.GET)
    public ResponseEntity<UserListResponseDTO> findAll(){
        final UserListResponseDTO userListResponseDTO = UserListResponseDTO.builder()
                .userList(userService.findAll()).build();
        return ResponseEntity.ok(userListResponseDTO);
    }


}
