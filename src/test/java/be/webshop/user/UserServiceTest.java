package be.webshop.user;

import be.webshop.exception.UserNietGevonden;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepositoryMock;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void findUserBy_verfyInteractionWithRepository() {
        userService.findUserBy("Xan");

        verify(userRepositoryMock).findUserBy("Xan");
    }

    @Test
    public void findUserBy_exceptionIsThrown_serviceDealsWithIt() {
        when(userRepositoryMock.findUserBy("Xan")).thenThrow(EmptyResultDataAccessException.class);

        Assertions.assertThatThrownBy(() -> userService.findUserBy("Xan"))
                .isInstanceOf(UserNietGevonden.class)
                .hasMessage("User met username 'Xan' is niet gevonden");
    }

    @Test
    public void storeUser_verifyInteractionWithRepo() {
        User user = new User("Nena", "nena");
        userService.store(user);

        verify(userRepositoryMock).store(user);
    }
}