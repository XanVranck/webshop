package be.webshop.controller;

import be.webshop.api.UserTo;
import be.webshop.infrastructure.SpringIntegrationTest;
import be.webshop.user.User;
import be.webshop.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerTest extends SpringIntegrationTest {
    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    private UserTo user;

    @Before
    public void setUp() {
        user = new UserTo("Nena", "Nena");
    }

    @Test
    public void signUp_shouldStoreTheUser() {
        userController.signUp(user);

        User actual = userService.findUserBy("Nena");

        assertThat(actual.getUsername()).isEqualTo("Nena");
    }
}