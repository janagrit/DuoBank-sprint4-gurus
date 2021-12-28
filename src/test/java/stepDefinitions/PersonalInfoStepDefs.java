package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.LoginPage;
import pages.PersonalInformationPage;

import pages.PreapprovalDetailsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ExcelUtils;
import utilities.SeleniumUtils;

import java.util.List;
import java.util.Map;

public class PersonalInfoStepDefs {

    String checkBoxYes;

    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }

    @When("I enter the username and password and than click on login button")
    public void iEnterTheUsernameAndPasswordAndThanClickOnLoginButton() {
        new LoginPage().GurusLoginMethod();
    }

    @Then("I am on the main page clicking on Mortgage Application")
    public void iAmOnTheMainPageClickingOnMortgageApplication() {

        SeleniumUtils.jsClick(new PreapprovalDetailsPage().mortgageLink);


    }

    @Then("After I complete the Preapproval Details page I should land on Personal Information Page")
    public void afterICompleteThePreapprovalDetailsPageAndIShouldLandOnPersonalInformationPage() throws InterruptedException {

        new PreapprovalDetailsPage().setPreapproval_method("Jack Smith", 90000, 2000);
        Thread.sleep(5000);
        String expected = "Personal Information";
        String actual = new PersonalInformationPage().title.getText();
        Assert.assertEquals(expected, actual);

    }

    @Given("The customers is able to move to the next page without providing Bday information")
    public void theCustomersIsAbleToMoveToTheNextPageWithoutProvidingBdayInformation() throws Throwable {

        ExcelUtils excelUtils = new ExcelUtils("PersonalInfo_Data.xlsx", "Sheet1");

        List<Map<String, String>> dataAsListOfMaps = excelUtils.getDataAsListOfMaps();

        PersonalInformationPage personalInfo = new PersonalInformationPage();
        Throwable ex = null;

        String expected = "Current Monthly Housing Expenses";
        Actions actions = new Actions(Driver.getDriver());
        for (int i = 0; i < dataAsListOfMaps.size(); i++) {

            Map<String, String> row = dataAsListOfMaps.get(i);

            if ( row.get("Execute").equalsIgnoreCase("y") ) {

                try {
                    personalInfo.b_firstName.sendKeys(row.get("FirstName"));
                    personalInfo.b_lastName.sendKeys(row.get("LastName"));
                    personalInfo.b_email.sendKeys(row.get("Email"));
                    personalInfo.b_ssn.sendKeys(row.get("SSN"));
                    actions.click(personalInfo.b_marital).sendKeys(Keys.ARROW_UP, row.get("MaterialStatus"), Keys.ENTER).perform();
                    personalInfo.b_cell.sendKeys(row.get("CellPhone"));
                    Thread.sleep(5000);
                    //actions.click(personalInfo.buttonnext).sendKeys(Keys.ENTER);
                    SeleniumUtils.jsClick(personalInfo.buttonNext);
                    //Assert.assertEquals(expected,personalInfo.expensesTitle.getText());
                    //System.out.println(personalInfo.expensesTitle.getText());
                    excelUtils.setCellData("PASS", "Status", i + 1);
                } catch (Throwable e) {
                    ex = e;
                    excelUtils.setCellData("FAIL", "Status", i + 1);
                }
                Driver.getDriver().navigate().back();
            } else {
                excelUtils.setCellData("SKIPPED", "Status", i + 1);
            }
        }
        throw ex;


    }

    @When("I click and put first name {string},last name {string},email {string},{string},{string},{string}")
    public void iClickAndPutFirstNameLastNameEmail(String FirstName, String LastName, String Email, String SSN, String MaterialStatus, String CellPhone) {

        Actions actions = new Actions(Driver.getDriver());

        new PersonalInformationPage().b_firstName.sendKeys(FirstName);
        new PersonalInformationPage().b_lastName.sendKeys(LastName);
        new PersonalInformationPage().b_email.sendKeys(Email);
        new PersonalInformationPage().b_ssn.sendKeys(SSN);
        actions.click(new PersonalInformationPage().b_marital).sendKeys(Keys.ARROW_UP, (MaterialStatus)).perform();
        new PersonalInformationPage().b_cell.sendKeys(CellPhone);
        SeleniumUtils.jsClick(new PersonalInformationPage().buttonNext);
        //Assert.assertEquals(expected,personalInfo.expensesTitle.getText());
        //System.out.println(new PersonalInformationPage().expensesTitle.getText());
    }

    @Then("I still should be able move to the next page")
    public void iStillShouldBeAbleMoveToTheNextPage() {

//        String expected = "Current Monthly Housing Expenses";
        // SeleniumUtils.jsClick(new PersonalInformationPage().buttonNext);
//        Assert.assertEquals(expected, new PersonalInformationPage().expensesTitle.getText());
//        System.out.println(new PersonalInformationPage().expensesTitle.getText());
    }

    @When("I check {string} box")
    public void iCheckBox(String checkBox) throws InterruptedException {
        //Actions actions = new Actions(Driver.getDriver());
        checkBoxYes = checkBox;
        SeleniumUtils.jsClick(new PersonalInformationPage().yesCheckBox);
        Thread.sleep(5000);
    }


    @Then("The Co-Borrower's Information should be displayed")
    public void theCoBorrowerSInformationShouldBeDisplayed() {

        String expected = "Co-Borrower's Information";
        String actual = new PersonalInformationPage().coBorrowerInfo.getText();
        Assert.assertEquals(expected, actual);
    }


    @Then("The Application Wizard option category should include")
    public void theApplicationWizardOptionCategoryShouldInclude(List<String> expectedOption) {

        List<String> actualOption = SeleniumUtils.getElementsText(new PersonalInformationPage().stepsApp);

        Assert.assertEquals(expectedOption, actualOption);

    }

    @Then("I put the info for co-borrower {string}, {string}, {string},{string},{string},{string}")
    public void iPutTheInfoForCoBorrower(String bFirstName, String bLastName, String bEmail, String bSSN, String bMaterialStatus, String bCellPhone) {

        SeleniumUtils.scroll(0, 500);
        Actions actions = new Actions(Driver.getDriver());
        new PersonalInformationPage().c_firstName.sendKeys(bFirstName);
        new PersonalInformationPage().c_lastName.sendKeys(bLastName);
        new PersonalInformationPage().c_email.sendKeys(bEmail);
        new PersonalInformationPage().c_ssn.sendKeys(bSSN);
        actions.click(new PersonalInformationPage().c_marital).sendKeys(Keys.ARROW_UP, (bMaterialStatus)).perform();
        new PersonalInformationPage().c_cell.sendKeys(bCellPhone);
        SeleniumUtils.jsClick(new PersonalInformationPage().buttonNext);
        //Assert.assertEquals(expected,personalInfo.expensesTitle.getText());
        System.out.println(new PersonalInformationPage().expensesTitle.getText());
    }

    @When("If I enter only some information")
    public void ifIEnterOnlySomeInformation(List<Map<String, String>> dataTable) {

        new PersonalInformationPage().b_firstName.sendKeys(dataTable.get(0).get("firstName"));
        new PersonalInformationPage().b_lastName.sendKeys(dataTable.get(0).get("lastName"));
        new PersonalInformationPage().b_email.sendKeys(dataTable.get(0).get("email"));
        new PersonalInformationPage().b_ssn.sendKeys(dataTable.get(0).get("SSN"));
        SeleniumUtils.scroll(0, 500);
        SeleniumUtils.jsClick(new PersonalInformationPage().buttonNext);
    }

    @Then("I should receive an error message")
    public void iShouldReceiveAnErrorMessage() throws InterruptedException {

        String expected = "THIS FIELD IS REQUIRED.";
        String actual = new PersonalInformationPage().errorMessage.getText();
        Assert.assertEquals(expected, actual);
//        String pageSource = Driver.getDriver().getPageSource();
//        Thread.sleep(5000);
//        Assert.assertTrue(pageSource.contains(expected));
//    }

    }
}
