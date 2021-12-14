package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.EmploymentAndIncomePage;
import pages.LoginPage;
import pages.PersonalInformationPage;
import pages.PreapprovalDetailsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ExcelUtils;
import utilities.SeleniumUtils;

import java.util.List;
import java.util.Map;

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

        Assert.assertEquals("Loan Application", Driver.getDriver().getTitle());

    }

    @Given("Click on  Employment and Income Page")
    public void clickOnEmploymentAndIncomePage() {
        EmploymentAndIncomePage employmentAndIncomePage = new EmploymentAndIncomePage();
        employmentAndIncomePage.getToEmploymentAndIncome();
    }


    @And("Verify that I am on Employment and Income Page")
    public void verifyThatIAmOnEmploymentAndIncomePage() {
        Assert.assertEquals("Loan Application",Driver.getDriver().getTitle());
    }


    @Given("Select {string} checkbox.")
    public void selectCheckbox(String CurerntJob) throws InterruptedException {

            String curerntJob = CurerntJob;
            SeleniumUtils.jsClick(new EmploymentAndIncomePage().currentJob);
            Thread.sleep(5000);

    }

    @Then("Verify that checkbox is selected")
    public void verifyThatCheckboxIsSelected() {
        Assert.assertTrue(new EmploymentAndIncomePage().currentJob.isEnabled());
    }



    @Given("I enter information from Excel File named {string} for required fields")
    public void iEnterInformationFromExcelFileNamedForRequiredFields(String file) throws Throwable {

        ExcelUtils excelUtils = new ExcelUtils("Employer1.xlsx","Sheet1");

        List<Map<String, String>> dataAsListOfMaps = excelUtils.getDataAsListOfMaps();

        EmploymentAndIncomePage employmentAndIncomePage = new EmploymentAndIncomePage();
        Throwable ex = null;
        Actions actions = new Actions(Driver.getDriver());

        for (int i = 0; i < dataAsListOfMaps.size(); i++) {

            Map<String, String> row = dataAsListOfMaps.get(i);

            if(row.get("Execute").equalsIgnoreCase("y")){

                try {
                    SeleniumUtils.scroll(0,-300);
                    Thread.sleep(3000);
                    //SeleniumUtils.waitForVisibility(employmentAndIncomePage.employer,5);

                    employmentAndIncomePage.employer.sendKeys(row.get("Employer Name"));

                    employmentAndIncomePage.position.sendKeys(row.get("Position"));
                    employmentAndIncomePage.city.sendKeys(row.get("City"));
                    employmentAndIncomePage.state.sendKeys(row.get("State"));
                    employmentAndIncomePage.startDate.sendKeys(row.get("Start Date"));


                    excelUtils.setCellData("PASS", "Status", i + 1);
                }catch(Throwable e){
                    ex = e;
                    excelUtils.setCellData("FAIL", "Status", i + 1);
                }
                Driver.getDriver().navigate().back();
            }else{
                excelUtils.setCellData("SKIPPED", "Status", i + 1);
            }
        }throw ex;


    }

    @When("I click and enter gross monthly income{string},monthly overtime {string},monthly bonuses{string},monthly commissions{string}, monthly dividents{string}")
    public void iClickAndEnterGrossMonthlyIncomeMonthlyOvertimeMonthlyBonusesMonthlyCommissionsMonthlyDividents(String income, String overtime, String bonuses, String commissions, String devidents) {

        new EmploymentAndIncomePage().grossMonthlyIncome.sendKeys(income);
        new EmploymentAndIncomePage().monthlyOverTime.sendKeys(overtime);
        new EmploymentAndIncomePage().monthlyBonuses.sendKeys(bonuses);
        new EmploymentAndIncomePage().monthlyCommission.sendKeys(commissions);
        new EmploymentAndIncomePage().monthlyDividents.sendKeys(devidents);



    }


    @When("I fill out {string},{string},{string},{string},{string},{string}")
    public void iFillOut(String source1 ,String amount1, String source2, String amount2, String source3, String amount3) {

        EmploymentAndIncomePage employmentAndIncomePage=new EmploymentAndIncomePage();
        employmentAndIncomePage.incomesource1.sendKeys(source1);
        employmentAndIncomePage.amount1.sendKeys(amount1);

        employmentAndIncomePage.incomesource2.sendKeys(source2);
        employmentAndIncomePage.amount2.sendKeys(amount2);

        employmentAndIncomePage.incomesource3.sendKeys(source3);
        employmentAndIncomePage.amount3.sendKeys(amount3);
    }

    @When("I will leave required fields empty and go to next page")
    public void iWillLeaveRequiredFieldsEmptyAndGoToNextPage() {
        EmploymentAndIncomePage employmentAndIncomePage = new EmploymentAndIncomePage();

        employmentAndIncomePage.employer.sendKeys("");
        employmentAndIncomePage.grossMonthlyIncome.sendKeys("");
        employmentAndIncomePage.buttonnext.click();

    }

    @Then("message {string} should be displayed")
    public void messageShouldBeDisplayed(String msg) {

        Assert.assertEquals(msg, new EmploymentAndIncomePage().error.getText());
    }


}
