package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ElementHelper {
    AppiumDriver driver;
    WebDriverWait wait;
    Actions action;

    public ElementHelper(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.action = new Actions(driver);
    }

    /**
     * @param key
     * @return
     */
    public WebElement findElement(By key) {
        WebElement element = presenceElement(key);
        //scrollToElement(element);
        return element;
    }

    /**
     * @param key
     * @return
     */
    public List<WebElement> findElements(By key) {
        List<WebElement> elements = presenceElements(key);
        //scrollToElement(elements.get(0));
        return elements;
    }

    /**
     * @param key
     */
    public void click(By key) {
        findElement(key).click();
    }

    /**
     * @param key
     * @param text
     */
    public void sendKey(By key, String text) {
        findElement(key).sendKeys(text);
    }

    /**
     * @param key
     * @return
     */
    public String getText(By key) {
        return findElement(key).getText();
    }

    /**
     * @param key
     * @param text
     * @return
     */
    public boolean checkElementText(By key, String text) {
        return wait.until(ExpectedConditions.textMatches(key, Pattern.compile(text)));
    }

    /**
     * @param key
     */
    public void checkElementVisible(By key) {
        wait.until(ExpectedConditions.visibilityOf(findElement(key)));
    }

    /**
     *
     * @param key
     */
    public void checkElementPresence(By key) {
        wait.until(ExpectedConditions.presenceOfElementLocated(key));
    }

    /**
     * @param text
     * @return
     */
    public boolean checkTitle(String text) {
        return wait.until(ExpectedConditions.titleIs(text));
    }

    /**
     * @param key
     * @param attr
     * @return
     */
    public String getAttribute(By key, String attr) {
        return findElement(key).getAttribute(attr);
    }

    /**
     * @param key
     * @param attr
     * @param text
     */
    public void checkAttribute(By key, String attr, String text) {
        Assert.assertEquals(getAttribute(key, attr), text);
    }

    /**
     * @param key
     * @param text
     */
    public void clickElementWithText(By key, String text) {
        boolean find = false;
        List<WebElement> elements = findElements(key);
        for (WebElement element : elements) {
            if (element.getText().contains(text)) {
                element.click();
                find = true;
                break;
            }
        }
        Assert.assertEquals(true, find);
    }

    /**
     * @param key
     * @param text
     */
    public void checkElementWithText(By key, String text) {
        boolean find = false;
        List<WebElement> elements = findElements(key);
        for (WebElement element : elements) {
            if (element.getText().contains(text)) {
                find = true;
                break;
            }
        }
        Assert.assertEquals(true, find);
    }

    /**
     * @param key
     * @param text
     * @param text2
     */
    public void sendKeyElementWithText(By key, String text, String text2) {
        boolean find = false;
        List<WebElement> elements = findElements(key);
        for (WebElement element : elements) {
            if (element.getText().equals(text)) {
                element.sendKeys(text2);
                find = true;
                break;
            }
        }
        Assert.assertEquals(true, find);
    }

    /**
     * @param key
     * @return
     */
    public WebElement presenceElement(By key) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(key));
    }

    /**
     * @param key
     * @return
     */
    public List<WebElement> presenceElements(By key) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(key));
    }

    /**
     * @param element
     */
    public void scrollToElement(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);

    }

    public void swipeDown(int swipeCount) {
        for (int i = 0; i < swipeCount; i++) {
            try {

                int screenWidth = driver.manage().window().getSize().width;
                int screenHeight = driver.manage().window().getSize().height;
                int startX = screenWidth / 2;
                int startY = (int) (screenHeight * 0.7);
                int endY = (int) (screenHeight * 0.42);
                PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
                Sequence swipe = new Sequence(finger, 1);
                swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
                swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
                swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                driver.perform(Collections.singletonList(swipe));
                System.out.println((i + 1) + ". kaydırma işlemi başarılı!");
            } catch (Exception e) {
                System.out.println("Kaydırma işlemi başarısız: " + e.getMessage());
            }
        }


    }
}






