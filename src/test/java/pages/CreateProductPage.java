package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class CreateProductPage {
    WebDriver driver;
    WebDriverWait wait;

    By productButton = By.xpath("//*[@id='root']/div/div/div/div[2]/div[1]/section/div[1]/div[5]/div");
    By closeGuideButton = By.xpath("/html/body/div[5]/div/div[1]/div/div/div/div/div[2]/div[2]");
    By createProductButton = By.xpath("//*[@id='app-body']/div/div/div[5]/div[1]/div[2]/button");
    By nameProductText = By.id("productName");
    By descriptionProductText = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[1]/div[1]/div[2]/div/div[2]/div[1]/div/div[3]/div");
    By imageProduct = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[1]/div[2]/div[2]/div/div[1]/section/div/input");
    By listingPriceText = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[1]/div[4]/div[2]/div/div/div[1]/div/div[1]/div/input");
    By sellingPriceText = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[1]/div[4]/div[2]/div/div/div[2]/div/div[1]/div/input");
    By costPriceText = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[1]/div[4]/div[2]/div/div/div[3]/div/div[1]/div/input");
    By VAT = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[1]/div[4]/div[2]/div/div/div[4]/div/div/button/span/div/div[1]");
    By VATValue = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[1]/div[4]/div[2]/div/div/div[4]/div/div/div/div/button[1]/span/div/div");
    By showSSRCheckbox = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[1]/div[4]/div[2]/div/div/div[5]/div/div/label[1]/div");
    By Unit = By.id("input-search");
    By UnitValue = By.xpath("//*[@id='search-list']/div[1]/div");
    By weightText = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[2]/div[4]/div[2]/div/div[1]/div[1]/div/input");
    By lenghtText = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[2]/div[4]/div[2]/div/div[2]/div[1]/div/div[1]/div/input");
    By widthText = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[2]/div[4]/div[2]/div/div[2]/div[2]/div/div[1]/div/input");
    By heightText = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[2]/div[4]/div[2]/div/div[2]/div[3]/div/div[1]/div/input");
    By stock  = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[2]/div[3]/div[2]/div/div[5]/div[1]/div/div/div[1]/div/input");
    By applyAllButton = By.xpath("//*[@id='app-body']/div/div/div[5]/div[2]/form/section/section[2]/div[3]/div[2]/div/div[5]/div[1]/button/div");
    By saveButton = By.xpath("//*[@id='app-body']/div/div/div[5]/div[1]/div[2]/div/div/button[1]");
    By successPop = By.xpath("/html/body/div[5]/div/div[1]/div/div/div[2]");

    public CreateProductPage(WebDriver driver){
        this.driver = driver;
    }

    public void createPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {

            WebElement buttonclick = wait.until(ExpectedConditions.elementToBeClickable(productButton));
            buttonclick.click();
        } catch (TimeoutException e) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(screenshot.toPath(), Path.of("D:\\SeleniumTestNG\\screenshot.png"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw e;
        }
        //wait.until(ExpectedConditions.elementToBeClickable(productButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeGuideButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(createProductButton)).click();
    }

    public void inputInfoProduct (){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement ab = wait.until(ExpectedConditions.visibilityOfElementLocated(nameProductText));
        ab.sendKeys("Sản Phẩm Trưng Bày 2");
        driver.findElement(descriptionProductText).sendKeys("Có một điều đáng chú ý về tình trạng hiện tại của Apple: Vị trí tụt hậu trong cuộc đua AI không hẳn là vấn đề nghiêm trọng nhất của công ty này. Nhưng tuần tới có thể lại là một câu chuyện khác.");
    }
    public void uploadImage(){
        driver.findElement(imageProduct).sendKeys("C:\\Users\\cuong\\Downloads\\euqnWGLZqFg8.jpg");
    }
    public void inputPrice(){
        driver.findElement(listingPriceText).sendKeys("10000");
        driver.findElement(sellingPriceText).sendKeys("10000");
        driver.findElement(costPriceText).sendKeys("10000");
        driver.findElement(VAT).click();
        driver.findElement(VATValue).click();
    }
    public void checkBoxDisplay(){
        driver.findElement(showSSRCheckbox).click();
    }
    public void selectUnit(){
        WebElement unitToScroll = driver.findElement(Unit);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", unitToScroll);
        unitToScroll.click();
        driver.findElement(Unit).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement selectValue = wait.until(ExpectedConditions.elementToBeClickable(UnitValue));
        selectValue.click();
    }
    public void inputDemension(){
        driver.findElement(weightText).sendKeys("100");
        driver.findElement(lenghtText).sendKeys("11");
        driver.findElement(widthText).sendKeys("22");
        driver.findElement(heightText).sendKeys("33");
    }
    public void setStock(){
        driver.findElement(stock).sendKeys("100");
        driver.findElement(applyAllButton).click();
    }
    public void clickSave(){
        WebElement saveScroll = driver.findElement(saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveScroll);
        driver.findElement(saveButton).click();
    }
    public String createSuccessPop(){
        WebElement succesMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(successPop));
        return succesMsg.getText();
    }
}
