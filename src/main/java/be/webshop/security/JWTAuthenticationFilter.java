package be.webshop.security;

import be.webshop.user.User;
import be.webshop.user.UserService;
import be.webshop.utils.AuthProperty;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

import static java.util.Collections.emptyList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private Properties properties = AuthProperty.loadPropertiesForAuth();

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) throws IOException {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        User user = new User(username, password);

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        emptyList()));
        if (authenticate != null) {
            response.addHeader(properties.getProperty("role"), String.valueOf(authenticate.getAuthorities().stream().findFirst().get()));
        }

        return authenticate;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) {
        String token = Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername() + ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getPassword() + ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getAuthorities())
                .signWith(SignatureAlgorithm.HS512, properties.getProperty("secret").getBytes())
                .compact();
        response.addHeader("access-control-expose-headers", properties.getProperty("header.string") + ", " + properties.getProperty("role"));
        response.addHeader(properties.getProperty("header.string"), properties.getProperty("token.prefix") + " " + token);
    }

}
