package be.webshop.user;

import be.webshop.exception.UserNietGevonden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User findUserBy(String username) {
        try {
            return repository.findUserBy(username);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNietGevonden(username);
        }
    }


    public void store(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.store(user);
    }
}
