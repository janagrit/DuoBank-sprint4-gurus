package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CreditReportPage;
import pages.EmploymentAndIncomePage;
import pages.PreapprovalDetailsPage;
import utilities.Driver;
import utilities.SeleniumUtils;

public class CreditReportStepDef {

    @Then("Click on Credit Report Page")
    public void clickOnCreditReportPage() {
        EmploymentAndIncomePage employmentAndIncomePage=new EmploymentAndIncomePage();
        employmentAndIncomePage.getToCreditReport();
    }


    @Then("verify that I am on Credit Report Page")
    public void verifyThatIAmOnCreditReportPage() {
        String actual = Driver.getDriver().getTitle();
        String expected= "Loan Application";
        Assert.assertEquals(expected,actual);
    }

    @Given("Click NO")
    public void clickNo() {
//        CreditReportPage creditReportPage =new CreditReportPage();
//        creditReportPage.clickNo.click();

        SeleniumUtils.jsClick(new CreditReportPage().clickNo);
    }

    @Then("Verify if NO is selected")
    public void verifyIfNOIsSelected() {
        Assert.assertTrue(new CreditReportPage().clickNo.isSelected());
    }



    @Then("Verify if Yes is selected")
    public void verifyIfYesIsSelected() {
        Assert.assertTrue(new CreditReportPage().clickYes.isSelected());
    }
}
