package be.webshop.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AuthProperty {
    public static Properties loadPropertiesForAuth() throws IOException {
        FileInputStream inputStream = new FileInputStream("authconfig.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }
}
