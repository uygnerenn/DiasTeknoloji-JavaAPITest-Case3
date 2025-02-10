package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    static AppiumDriver driver;
    static Properties properties;
    private static WebDriverWait wait;

    static DesiredCapabilities capabilities;

    public static AppiumDriver initialize_Driver(String browser){
        properties = ConfigReader.getProperties();

        capabilities = new DesiredCapabilities();
        if (browser.equals("Android")) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "emulator-5554");
            capabilities.setCapability("appPackage", "com.akakce.akakce");
            capabilities.setCapability("appActivity", "com.akakce.akakce.ui.splash.SplashActivity");

            try {
                driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

        } else if (browser.equals("iOS")) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("udid", "uygnerenn");
            capabilities.setCapability("appPackage", "com.akakce.akakce");
            capabilities.setCapability("appActivity", "com.akakce.akakce.ui.splash.SplashActivity");
            try {
                driver = new IOSDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }


        int impWait = Integer.parseInt(properties.getProperty("implicityWait"));
        driver.manage().timeouts().implicitlyWait(impWait, TimeUnit.SECONDS);
        return getDriver();



    }


    public static WebDriverWait getWait() {
        return wait;
    }
    public static AppiumDriver getDriver() {
        return  driver;
    }


}
