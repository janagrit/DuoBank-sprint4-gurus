package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.PersonalInformationPage;
import pages.Preapproval_Details_Page;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.time.Duration;

public class PreapprovalDetailsStepDefs {

    @When("I am on the main page clicking on Mortgage Application")
    public void iAmOnTheMainPageClickingOnMortgageApplication() {

        Driver.getDriver().getTitle();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5)) ;
        Assert.assertTrue(new Preapproval_Details_Page().Mortgage_link.isDisplayed());
       // new Preapproval_Details_Page().Mortgage_link.click();
       SeleniumUtils.jsClick(new Preapproval_Details_Page().Mortgage_link);
    }



    @Then("I am on on the preapproval page filling a name {string}, purchase price {int}, down payment {int} and click next button")
    public void iAmOnOnThePreapprovalPageFillingANamePurchasePriceDownPaymentAndClickNextButton(String name, int num1, int num2) {

        new Preapproval_Details_Page().setPreapproval_method(name, num1, num2);

    }



}
