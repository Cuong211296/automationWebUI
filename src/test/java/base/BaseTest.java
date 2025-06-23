package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import untils.Constants;
import untils.DriverFactory;

public class BaseTest {
    protected WebDriver driver;
    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        DriverFactory.initDriver(browser);
        driver = DriverFactory.getDriver();
        if (driver == null) {
            throw new RuntimeException("Driver is NULL after initDriver! Check init logic.");
        }
        String url = Constants.BASE_URL;
        System.out.println("Navigating to URL: " + url);  // 👉 In ra để xác minh
        driver.get(Constants.BASE_URL);

    }
    @AfterMethod
    public void teardown() {
        DriverFactory.quitDriver();
    }
}
