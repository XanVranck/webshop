package be.webshop.user;

import be.webshop.api.UserTo;

public class UserFactory {

    public static User assembleUser(UserTo userTo) {
        return new User(userTo.getUsername(), userTo.getPassword());
    }
}
