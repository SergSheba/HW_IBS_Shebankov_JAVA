package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertyLoader {
    public static Properties loadProperties() throws IOException {
        Properties configuration = new Properties();
        InputStream inputStream = PropertyLoader.class
                .getClassLoader()
                .getResourceAsStream("application.properties");
        configuration.load(inputStream);
        inputStream.close();
        return configuration;
    }
    public static URL getUrl(String property) throws IOException {
        Properties conf = PropertyLoader.loadProperties();
        String propertyHost = conf.getProperty(property);
        return new URL(propertyHost);
    }
}
