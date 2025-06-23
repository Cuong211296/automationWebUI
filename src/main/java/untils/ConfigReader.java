package untils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();
    static {
        String ca = System.getProperty("ca", "ca"); // default to 'dev'
        String stage = System.getProperty("stage", "stage");
        String prod = System.getProperty("prod", "prod");
        String configFilePath = String.format("src/test/resources/config/config-%s.properties", stage);
        try {
            FileInputStream fis = new FileInputStream(configFilePath);
            props.load(fis);
        } catch (IOException e) { e.printStackTrace(); }
    }
    public static String get(String key) { return props.getProperty(key); }
}
