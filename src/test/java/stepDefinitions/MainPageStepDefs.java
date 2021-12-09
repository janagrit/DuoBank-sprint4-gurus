package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;

public class MainPageStepDefs {

    @Given("on login page")
    public void onLoginPage() {   Driver.getDriver().get(ConfigReader.getProperty("url"));   }



    @When("I put email and password and click login button")
    public void iPutEmailAndPasswordAndClickLoginButton() {
        new LoginPage().emailInput.sendKeys(ConfigReader.getProperty("email"));
        new LoginPage().passwordlInput.sendKeys(ConfigReader.getProperty("password"));
    }


    @Then("I am on the main page")
    public void iAmOnTheMainPage() {
        Driver.getDriver().getTitle();
    }
}
