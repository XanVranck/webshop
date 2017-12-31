package be.webshop.security;

import be.webshop.user.User;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private Properties properties = new Properties();
    private InputStream inputStream;


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        loadPropertiesForAuth();
        String header = request.getHeader(properties.getProperty("header.string"));

        if (header == null || !header.startsWith(properties.getProperty("token.prefix"))) {
            chain.doFilter(request, response);
        } else {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(properties.getProperty("header.string"));

        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(properties.getProperty("secret").getBytes())
                    .parseClaimsJws(token.replace(properties.getProperty("token.prefix"), ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
        }
        return null;
    }

    private void loadPropertiesForAuth() throws IOException {
        inputStream = new FileInputStream("authconfig.properties");
        properties.load(inputStream);
    }
}
