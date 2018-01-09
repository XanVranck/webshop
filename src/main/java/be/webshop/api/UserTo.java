package be.webshop.api;

public class UserTo {
    private String username;
    private String password;
    private String email;

    UserTo() {
    }

    public UserTo(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
