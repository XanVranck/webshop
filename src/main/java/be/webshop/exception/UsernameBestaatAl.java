package be.webshop.exception;

import static java.lang.String.format;

public class UsernameBestaatAl extends RuntimeException {
    public UsernameBestaatAl(String username) {
        super(format("User met username '%s' bestaat al, gelieve een andere naam te nemen", username));
    }
}
