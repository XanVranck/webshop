package be.webshop.security;

import be.webshop.utils.AuthProperty;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Properties properties = AuthProperty.loadPropertiesForAuth();
        String header = request.getHeader(properties.getProperty("header.string"));

        if (!header.startsWith(properties.getProperty("token.prefix"))) {
            chain.doFilter(request, response);
            logger.info("unauthorized user tried to access: " + request.getServletPath());
        } else {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication();
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
            logger.info("authorized user: " + authenticationToken);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication() throws IOException {
        Properties properties = AuthProperty.loadPropertiesForAuth();

        String token = properties.getProperty("token");

        String user = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, properties.getProperty("secret").getBytes())
                .setPayload(token)
                .compact();

        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }

        return null;
    }


}
