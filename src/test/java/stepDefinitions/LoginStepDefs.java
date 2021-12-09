package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.assertj.core.api.SoftAssertions;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;

public class LoginStepDefs {

    @Given("on login page")
    public void onLoginPage() {   Driver.getDriver().get(ConfigReader.getProperty("url"));   }



    @Then("I click on sign up page")
    public void iClickOnSignUpPage() {
        new LoginPage().signUp.click();
    }



    @When("on sign up page I fill out the fields: name {string}, surname {string} and email {string} and password {string} click sign up button")
    public void onSignUpPageIFillOutTheFieldsNameSurnameAndEmailAndPasswordClickSignUpButton(String name, String lastName, String email, String password) {

        new LoginPage().setSignUp(name, lastName, email, password);
        System.out.println(new LoginPage().textRegistration.getText());
    }



    @Then("I verify confirmation msg {string}")
    public void iVerifyConfirmationMsg(String confirmationMsg) {
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(confirmationMsg).isEqualTo(new LoginPage().textRegistration.getText());
    }






    @Then("filling the email {string} and password {string} and click Login button")
    public void fillingTheEmailAndPasswordAndClickLoginButton(String email, String pass) throws InterruptedException {
        new LoginPage().setLoginButton(email, pass);
        System.out.println(Driver.getDriver().getTitle());
    }


}
