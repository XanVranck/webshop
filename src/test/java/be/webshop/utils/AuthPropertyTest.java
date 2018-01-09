package be.webshop.utils;

import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthPropertyTest {

    @Test
    public void loadPropertiesForAuth() throws IOException {
        Properties properties = AuthProperty.loadPropertiesForAuth();

        assertThat(properties.getProperty("secret")).isEqualTo("SecretKeyToGenJWTs");
        assertThat(properties.getProperty("expiration.time")).isEqualTo("864_000_000");
        assertThat(properties.getProperty("token.prefix")).isEqualTo("Bearer");
        assertThat(properties.getProperty("role")).isEqualTo("Role");
        assertThat(properties.getProperty("header.string")).isEqualTo("Authorization");
        assertThat(properties.getProperty("sign.up.url")).isEqualTo("/user/sign-up");
        assertThat(properties.getProperty("token")).isEqualTo("rndm.gnrtd.tkn");
    }
}