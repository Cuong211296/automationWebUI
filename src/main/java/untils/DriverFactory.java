package untils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.UUID;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<Path> tempProfileDir = new ThreadLocal<>();

    public static void initDriver(String browserName){
        if(driver.get() == null){
            if("chrome".equalsIgnoreCase(browserName)){
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-software-rasterizer");
                options.addArguments("--remote-allow-origins=*");
                try {
                    Path baseDir = Paths.get("/home/jenkins/tmp");
                    Files.createDirectories(baseDir);
                    Path profile = Files.createTempDirectory(baseDir, "chrome-profile-" + UUID.randomUUID());
                    tempProfileDir.set(profile);
                    options.addArguments("--user-data-dir=" + profile.toAbsolutePath());
                } catch (IOException e) {
                    throw new RuntimeException("❌ Không thể tạo thư mục user-data-dir tạm thời", e);
                }

                driver.set(new ChromeDriver(options));
            }
            else if("edge".equalsIgnoreCase(browserName)){
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
            }
            else if ("firefox".equalsIgnoreCase(browserName)) {
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
            }
            else {
                throw new RuntimeException("Khong tim thay browser nào");
            }
        }
        driver.get().manage().window().maximize();
        System.out.println("Driver initialized successfully");
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void quitDriver(){
        if (driver.get() != null){
            driver.get().quit();
            driver.remove();
        }
        if (tempProfileDir.get() != null) {
            try {
                Files.walk(tempProfileDir.get())
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(java.io.File::delete);
            } catch (IOException e) {
                System.err.println("❗ Không thể xoá thư mục profile: " + e.getMessage());
            }
            tempProfileDir.remove();
        }
    }

}
