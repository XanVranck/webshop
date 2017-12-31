package be.webshop.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    void store(User user){
        entityManager.persist(user);
    }
}
