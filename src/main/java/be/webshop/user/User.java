package be.webshop.user;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Objects;

import static be.webshop.user.Role.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String username;
    private String password;

    @Enumerated
    private Role role;

    User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        role = CUSTOMER;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
