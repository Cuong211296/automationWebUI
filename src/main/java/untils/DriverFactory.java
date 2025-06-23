package untils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browserName){
        if(driver.get() == null){
            if("chrome".equalsIgnoreCase(browserName)){
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
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
    }

}
