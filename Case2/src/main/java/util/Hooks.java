package util;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Hooks {

     AppiumDriver driver;
     Properties properties;
    @Before
    public void before() {
        String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        properties = ConfigReader.initialize_Properties();
        driver = DriverFactory.initialize_Driver(browser);
        if (driver == null) {
            throw new RuntimeException("Driver is NULL in Hooks! Check DriverFactory.");
        }
    }



    @BeforeStep
    public void beforeStep() {

    }

    @AfterStep
    public void afterStep() {
    }

    @After
    public void after() {
        if (driver != null) {
            System.out.println("🛑 Closing Appium Driver...");
            driver.quit();
        } else {
            System.out.println("⚠️ Warning: Driver is already NULL, skipping quit.");
        }

    }
}
