package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.LoginPage;
import pages.PersonalInformationPage;

import pages.PreapprovalDetailsPage;
import utilities.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class db_PersonalinfoStepDef {

    Map<String, String>  expectedMap;
    String checkBoxYes;

    @Given("I am on the homepagee")
    public void iAmOnTheHomepage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }

    @When("I enter the username and password and than click on login buttonn")
    public void iEnterTheUsernameAndPasswordAndThanClickOnLoginButton() {

        new LoginPage().GurusLoginMethod();
    }

    @Then("I am on the main page clicking on Mortgage Applicationn")
    public void iAmOnTheMainPageClickingOnMortgageApplication() {

        SeleniumUtils.jsClick(new PreapprovalDetailsPage().mortgageLink);
        
    }

    @Then("After I complete the Preapproval Details page I should land on Personal Information Pagee")
    public void afterICompleteThePreapprovalDetailsPageAndIShouldLandOnPersonalInformationPage() throws InterruptedException {

        new PreapprovalDetailsPage().setPreapproval_method("Jack Smith", 90000, 2000);
        Thread.sleep(5000);
        String expected = "Personal Information";
        String actual = new PersonalInformationPage().title.getText();
        Assert.assertEquals(expected, actual);
    }


    @Given("I am on the personal information page and I am connected to the DB")
    public void iAmOnThePersonalInformationPageAndIAmConnectedToTheDB() {

        DBUtility.createConnection();
    }

    @When("I put the following info")
    public void iPutTheFollowingInfo(List<Map<String,String>> dataTable) throws InterruptedException {

        Actions actions = new Actions(Driver.getDriver());
        expectedMap = dataTable.get(0);

        new PersonalInformationPage().b_firstName.sendKeys(expectedMap.get("FirstName"));
        new PersonalInformationPage().b_lastName.sendKeys(expectedMap.get("LastName"));
        new PersonalInformationPage().b_email.sendKeys(expectedMap.get("Email"));
        new PersonalInformationPage().b_ssn.sendKeys(expectedMap.get("SSN"));
        actions.click(new PersonalInformationPage().b_marital).sendKeys(Keys.ARROW_UP,(expectedMap.get("MaterialStatus")),Keys.ENTER).perform();
        SeleniumUtils.scroll(0,200);
        new PersonalInformationPage().b_cell.sendKeys(expectedMap.get("CellPhone"));
        SeleniumUtils.jsClick(new PersonalInformationPage().buttonNext);

    }

    @Then("I am able move to the next page")
    public void iAmAbleMoveToTheNextPage() throws InterruptedException {

        String expected = "Current Monthly Housing Expenses";
        String pageSource = Driver.getDriver().getPageSource();
        Assert.assertTrue(pageSource.contains(expected));


        new PersonalInformationPage().Current_Monthly_Housing_Expenses();
        new PersonalInformationPage().Employment_and_Income();
        new PersonalInformationPage().Credit_Report();
        new PersonalInformationPage().EconcentPage();
        new PersonalInformationPage().Summary();
    }

    @And("The database should also have the correct info")
    public void theDatabaseShouldAlsoHaveTheCorrectInfo() throws SQLException {

        String expectedFirst= expectedMap.get("FirstName");
        String expectedLast = expectedMap.get("LastName");
        String expectedEmail = expectedMap.get("Email");
        String expectedSSN = expectedMap.get("SSN");
        String expectedMaterialStatus = expectedMap.get("MaterialStatus");
        String expectedCellPhone  = expectedMap.get("CellPhone");

        String query  = "select * from tbl_mortagage where b_lastName ='"+expectedLast+"'";
        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps(query);
        Map<String, Object> actualMap = queryResultListOfMaps.get(0);

        String actualFirst = (String)(actualMap.get("b_firstName"));
        String actualLast= (String)(actualMap.get("b_lastName"));
        String actualEmail = (String)(actualMap.get("b_email"));
        String actualSSN= (String)(actualMap.get("b_ssn"));
        String actualMaterialStatus = (String)(actualMap.get("b_marital"));
        String actualCellPhone = (String)(actualMap.get("b_cell"));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(expectedFirst).isEqualTo( actualFirst);
        softAssertions.assertThat(expectedLast).isEqualTo(actualLast);
        softAssertions.assertThat(expectedEmail).isEqualTo(actualEmail);
        softAssertions.assertThat(expectedSSN).isEqualTo( actualSSN);
        softAssertions.assertThat(expectedMaterialStatus).isEqualTo( actualMaterialStatus);
        softAssertions.assertThat(expectedCellPhone).isEqualTo( actualCellPhone);

        softAssertions.assertAll();

        DBUtility.updateQuery("delete from tbl_mortagage where b_lastName ='"+expectedLast+"'");
        DBUtility.close();

    }
}
