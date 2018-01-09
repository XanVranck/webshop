package be.webshop.user;

import be.webshop.exception.UsernameBestaatAl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    User findUserBy(String username) {
        String query = "select u from be.webshop.user.User u where u.username = :username";
        return entityManager.createQuery(query, User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    void store(User user) throws UsernameBestaatAl {
        try {
            entityManager.persist(user);
        } catch (PersistenceException e) {
            throw new UsernameBestaatAl(user.getUsername());
        }
    }
}
