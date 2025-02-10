package stepDefinitions;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Akakçe;
import util.DriverFactory;

import java.sql.DriverManager;

public class AkakçeStep {

   AppiumDriver driver = DriverFactory.getDriver();
 private Akakçe akakce;

 public AkakçeStep() {
  this.driver = DriverFactory.getDriver();
  this.akakce = new Akakçe(driver);
 }

    @Given("User launches the Akakce mobile application")
    public void user_launches_the_akakce_mobile_application() {
       akakce.launchTheApp();
    }

    @When("User proceeds with the continue without login option if the login prompt appears")
    public void user_proceeds_with_continue_without_login() {
       akakce.continueWithoutLogin();
    }

   @And("User enters {string} in the search bar and initiates a search")
   public void userEntersInTheSearchBarAndInitiatesASearch(String text) {
      akakce.Search(text);
   }

    @And("User clicks on the Filter button")
    public void user_clicks_on_filter_button() {
     akakce.Filter();
    }

    @And("User selects Computer → Hardware under the subcategory and clicks on the View Products button")
    public void user_selects_computer_hardware() {
     akakce.sortFilter();
    }

    @And("User selects the sorting option Highest Price")
    public void user_selects_highest_price() {
     akakce.highprice();
    }

    @And("User clicks on the 10th product from the results")
    public void user_clicks_on_tenth_product() {
        akakce.tentProduct();
    }

    @And("User clicks on the Go to Product button")
    public void user_clicks_on_go_to_product() {
     akakce.goProduct();
    }

    @Then("User should see the Go to Seller button displayed on the product details screen")
    public void user_verifies_go_to_seller_button() {
     akakce.goSellerBttnCheck();
    }


}



