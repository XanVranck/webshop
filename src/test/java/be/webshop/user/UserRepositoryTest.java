package be.webshop.user;

import be.webshop.infrastructure.SpringIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserRepositoryTest extends SpringIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private User xan;
    private User nena;

    @Before
    public void setUp() throws Exception {
        xan = new User("XanVranck", "Xan");
        nena = new User("NenaCal", "nena");
        userRepository.store(xan);
        userRepository.store(nena);
    }

    @Test
    public void findUserBy_usernameXanVranck_returnsUserXan() {
        User actual = userRepository.findUserBy("XanVranck");

        assertThat(actual).isEqualTo(xan);
    }

    @Test
    public void findUserBy_usernameNenaCal_returnsUserNena() {
        User actual = userRepository.findUserBy("NenaCal");

        assertThat(actual).isEqualTo(nena);
    }

    @Test
    public void findUserBy_unknownUserNAme_returnsEmptyResultDataAccessException() {
        assertThatThrownBy(() -> userRepository.findUserBy("onbekend"))
            .isInstanceOf(EmptyResultDataAccessException.class);
    }
}