package be.webshop.exception;

import static java.lang.String.*;

public class UserNietGevonden extends RuntimeException {
    public UserNietGevonden(String username) {
        super(format("User met username '%s' is niet gevonden", username));
    }
}
