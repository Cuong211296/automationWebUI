package tests;

import base.BaseTest;
import com.github.dockerjava.api.model.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateProductPage;
import pages.LoginPage;
import untils.Constants;
import untils.DriverFactory;


public class CreateProduct extends BaseTest {

    @Test
    public void producSuccess(){
        LoginPage login = new LoginPage(DriverFactory.getDriver());
        CreateProductPage cp = new CreateProductPage(DriverFactory.getDriver());
        login.login(Constants.USERNAME, Constants.PASSWORD);
        cp.createPage();
        cp.inputInfoProduct();
        cp.uploadImage();
        cp.inputPrice();
        cp.checkBoxDisplay();
        cp.selectUnit();
        cp.inputDemension();
        cp.setStock();
        cp.clickSave();
        Assert.assertTrue(cp.createSuccessPop().contains("Sản phẩm được tạo thành công!"));
    }
}
