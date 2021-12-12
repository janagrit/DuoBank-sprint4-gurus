package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;

public class SignUpStepDefs {

    @Given("on login page")
    public void onLoginPage() {   Driver.getDriver().get(ConfigReader.getProperty("url"));   }

    @Then("I click on sign up page")
    public void iClickOnSignUpPage() {
        new LoginPage().signUp.click();
    }



    @When("on sign up page I fill out the fields: name {string}, surname {string} and email {string} and password {string} click sign up button")
    public void onSignUpPageIFillOutTheFieldsNameSurnameAndEmailAndPasswordClickSignUpButton(String name, String lastName, String email, String password) {

        new LoginPage().setSignUp(name, lastName, email, password);

        Assert.assertTrue(new LoginPage().emailerror.isDisplayed());
    }





    @Then("I verify msg {string} or {string}")
    public void iVerifyMsgOr(String confmsg, String errormsg) {

        SoftAssertions softAssert = new SoftAssertions();
        if(new LoginPage().emailerror.isDisplayed()){
            softAssert.assertThat(errormsg).isEqualTo(new LoginPage().emailerror.getText());
        }else
        softAssert.assertThat(confmsg).isEqualTo(new LoginPage().textRegistration.getText());




    }
}
