package be.webshop.api;

public class UserTo {
    private String username;
    private String password;

    UserTo() {
    }

    public UserTo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
