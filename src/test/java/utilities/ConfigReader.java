package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();

    static {
        try (InputStream input = new FileInputStream("src/test/resources/config.propertiess")) {
            props.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Could not load config.propertiess", ex);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}