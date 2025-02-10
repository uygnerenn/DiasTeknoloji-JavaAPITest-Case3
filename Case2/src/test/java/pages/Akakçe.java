package pages;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ElementHelper;

import java.util.List;

public class Akakçe  {

    AppiumDriver driver;
    WebDriverWait wait;
    ElementHelper elementHelper;


     By notifiMssage = By.id("com.android.permissioncontroller:id/grant_dialog");
     By notiMssgDeny = By.id("com.android.permissioncontroller:id/permission_deny_button");
     By guestButton = By.id("com.akakce.akakce:id/continueWithoutRegister");

     By searchText = By.id("com.akakce.akakce:id/searchText");
     By searchBar = By.id("com.akakce.akakce:id/searchTextView");
     By searchBttn = By.id("com.akakce.akakce:id/searchIcon");
     By filterBttn = By.id("com.akakce.akakce:id/filterArea");
     By chooseFilter = By.xpath("//android.widget.LinearLayout//android.widget.TextView[@text='Bilgisayar, Donanım']");
     By applyFilterBttn = By.id("com.akakce.akakce:id/applyFilterBtn");
     By sortBy = By.id("com.akakce.akakce:id/sortArea");
     By highPrice = By.xpath("//android.widget.TextView[@text='En Yüksek Fiyat']");
     By tenthProduct = By.xpath("//android.widget.FrameLayout[@resource-id='com.akakce.akakce:id/card']//android.widget.TextView[@resource-id='com.akakce.akakce:id/name']\n");
     By goProductBttn = By.id("com.akakce.akakce:id/detailBtnLayout");
     By goProductBttn2 = By.xpath("//android.widget.TextView[@resource-id='com.akakce.akakce:id/detailBtnTextView']");
     By goSellerBttn = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout");





     public Akakçe(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.elementHelper = new ElementHelper(driver);
    }

    public void launchTheApp() {
        wait.until(ExpectedConditions.presenceOfElementLocated(notifiMssage));
        driver.findElement(notiMssgDeny).click();
    }

    public void continueWithoutLogin(){
        driver.findElement(guestButton).click();
    }

    public void Search(String text){
        driver.findElement(searchText).click();
        List<MobileElement> searchBars = driver.findElements(searchBar);
        searchBars.get(1).click();
        searchBars.get(1).sendKeys("Laptop");
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

    }

    public void Filter(){
        driver.findElement(filterBttn).click();
        driver.findElement(chooseFilter).click();
        driver.findElement(applyFilterBttn).click();
    }

    public void sortFilter(){
        driver.findElement(sortBy).click();

    }
    public void highprice(){
        driver.findElement(highPrice).click();
    }

    public void tentProduct(){
        elementHelper.swipeDown(6);
        List<MobileElement> scrollproduct = driver.findElements(tenthProduct);
        scrollproduct.get(1).click();
    }

    public void goProduct(){
         elementHelper.presenceElement(goProductBttn);
         driver.findElement(goProductBttn).click();

    }
    public void goSellerBttnCheck(){
        elementHelper.presenceElement(goSellerBttn);

    }










    }









