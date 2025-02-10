package util;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        if (this.driver == null) {
            throw new RuntimeException("❌ Driver is NULL in BasePage!");
        }
        this.wait = new WebDriverWait(driver, 5);
    }



    public WebElement getElement() {
        return driver.findElement(By.id("elementId"));
    }


    public void urlControl(String url) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
