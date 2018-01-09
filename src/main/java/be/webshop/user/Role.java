package be.webshop.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER"),
    VISITOR("VISITOR");

    private String role;

    Role(String role) {
        this.role = role;
    }


    @Override
    public String getAuthority() {
        return role;
    }
}
