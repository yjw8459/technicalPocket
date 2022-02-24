package yjw.technical.mockito;

import java.util.UUID;

public class TokenUtils {

    public static String generateJwtToken(User user) {
        return UUID.randomUUID().toString();
    }

}
