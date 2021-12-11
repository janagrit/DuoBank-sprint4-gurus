package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.EmploymentAndIncomePage;
import pages.LoginPage;
import pages.PreapprovalDetailsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

public class EmploymentAndIncomeStepDef {

    @Given("I am on the main page")
    public void iAmOnTheMainPage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
       LoginPage loginPage = new LoginPage();
       loginPage.GurusLoginMethod();
    }

    @Then("Click on Mortgage Application link")
    public void clickOnMortgageApplicationLink() {
        SeleniumUtils.jsClick(new PreapprovalDetailsPage().mortgageLink);
    }


    @And("Verify that I am on Mortgage Application Page")
    public void verifyThatIAmOnMortgageApplicationPage() {

        Assert.assertEquals("Loan Application", Driver.getDriver().getCurrentUrl());

    }

    @Given("Click on  Employment and Income Page")
    public void clickOnEmploymentAndIncomePage() {
        EmploymentAndIncomePage employmentAndIncomePage=new EmploymentAndIncomePage();
        employmentAndIncomePage.getToEmploymentAndIncome();
    }


    @And("Verify that I am on Employment and Income Page")
    public void verifyThatIAmOnEmploymentAndIncomePage() {
        Assert.assertEquals("Loan Application",Driver.getDriver().getTitle());
    }



}
