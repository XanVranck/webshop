package be.webshop.controller;

import be.webshop.infrastructure.SpringIntegrationTest;
import be.webshop.user.User;
import be.webshop.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerTest extends SpringIntegrationTest {
    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void setUp() {
        user = new User("Nena", "Nena");
    }

    @Test
    public void signUp_shouldStoreTheUser() {
        userController.signUp(user);

        User actual = userService.findUserBy("Nena");

        assertThat(actual).isEqualTo(user);
    }
}