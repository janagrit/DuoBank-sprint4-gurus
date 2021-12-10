package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;


public class LoginStepDefs {


    @When("I am on login page I filling out email and password and click login button")
    public void iAmOnLoginPageIFillingOutEmailAndPasswordAndClickLoginButton() {
        new LoginPage().GurusLoginMethod();
        System.out.println(Driver.getDriver().getTitle());
        Assert.assertTrue(Driver.getDriver().getTitle().equals("Loan Application"));
    }


}
