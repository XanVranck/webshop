package be.webshop.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AuthProperty {
    public static Properties loadPropertiesForAuth() throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("../resources/authconfig.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();
        return properties;
    }
}
