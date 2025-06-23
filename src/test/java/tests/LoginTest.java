package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import untils.Constants;
import untils.DriverFactory;

public class LoginTest extends BaseTest {
    @Test
    public void testLoginValid() {
        LoginPage lp = new LoginPage(DriverFactory.getDriver());
        lp.login(Constants.USERNAME, Constants.PASSWORD);
        //Assert.assertTrue(DriverFactory.getDriver().getPageSource().contains("You logged into"));
    }
}
