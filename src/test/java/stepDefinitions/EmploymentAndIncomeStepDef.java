package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.EmploymentAndIncomePage;
import utilities.Driver;

public class EmploymentAndIncomeStepDef {

    @And("Verify that I am on Mortgage Application Page")
    public void verifyThatIAmOnMortgageApplicationPage() {

        Assert.assertEquals("Loan Application", Driver.getDriver().getTitle());

    }


    @Given("Go to Employment and Income Page")
    public void goToEmploymentAndIncomePage() {
        EmploymentAndIncomePage employmentAndIncomePage = new EmploymentAndIncomePage();
        employmentAndIncomePage.getToEmploymentAndIncome();
    }

    @And("Verify that I am on Employment and Income Page")
    public void verifyThatIAmOnEmploymentAndIncomePage() {
        String title = "Borrower Employment Information";
        Assert.assertEquals(title,Driver.getDriver().getTitle());
    }


}
