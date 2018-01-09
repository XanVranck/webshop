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

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private UserTo user;

    @Before
    public void setUp() {
        user = new UserTo("Nena", "Nena", "nenacall@hotmail.com");
    }

    @Test
    public void signUp_shouldStoreTheUser() {
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        userController.signUp(user);

        User actual = userService.findUserBy("Nena");

        assertThat(actual.getUsername()).isEqualTo("Nena");
        assertThat(actual.getEmail()).isEqualTo("nenacall@hotmail.com");
    }
}