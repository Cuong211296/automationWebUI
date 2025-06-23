package untils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();
    static {
        String env = System.getProperty("env", "ca"); // default to 'dev'
        String configFilePath = String.format("src/test/resources/config/config-%s.properties", env.toLowerCase());
        try {
            FileInputStream fis = new FileInputStream(configFilePath);
            props.load(fis);
        } catch (IOException e) { e.printStackTrace(); }
    }
    public static String get(String key) { return props.getProperty(key); }
}
