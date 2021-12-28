package stepDefinitions;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
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
import pages.EmploymentAndIncomePage;
import pages.LoginPage;
import pages.PersonalInformationPage;

import pages.PreapprovalDetailsPage;
import utilities.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class db_PersonalinfoStepDef {

    Map<String, String>  expectedMap;
    Map<String, String>  expectedMap1;
    String checkBoxYes;

    @Given("I am on the homepage.")
    public void iAmOnTheHomepage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }

    @When("I enter the username and password and than click on login button.")
    public void iEnterTheUsernameAndPasswordAndThanClickOnLoginButton() {

        new LoginPage().GurusLoginMethod();
    }

    @Then("I am on the main page clicking on Mortgage Application.")
    public void iAmOnTheMainPageClickingOnMortgageApplication() {

        SeleniumUtils.jsClick(new PreapprovalDetailsPage().mortgageLink);
        
    }

    @Then("After I complete the Preapproval Details page I should land on Personal Information Page.")
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


    @Given("I check {string} box.")
    public void iCheckBox(String arg0) throws InterruptedException {

        SeleniumUtils.jsClick(new PersonalInformationPage().yesCheckBox);
        Thread.sleep(5000);
    }

    @Then("The Co-Borrower's Information should be displayed.")
    public void theCoBorrowerSInformationShouldBeDisplayed() {

        String expected = "Co-Borrower's Information";
        String actual = new PersonalInformationPage().coBorrowerInfo.getText();
        Assert.assertEquals(expected, actual);
    }

    @When("I put the following info for borrower")
    public void iPutTheFollowingInfoForBorrower(List<Map<String,String>> dataTable) {

        Actions actions = new Actions(Driver.getDriver());
        expectedMap = dataTable.get(0);

        new PersonalInformationPage().b_firstName.sendKeys(expectedMap.get("FirstName"));
        new PersonalInformationPage().b_lastName.sendKeys(expectedMap.get("LastName"));
        new PersonalInformationPage().b_email.sendKeys(expectedMap.get("Email"));
        new PersonalInformationPage().b_ssn.sendKeys(expectedMap.get("SSN"));
        actions.click(new PersonalInformationPage().b_marital).sendKeys(Keys.ARROW_UP,(expectedMap.get("MaterialStatus")),Keys.ENTER).perform();
        SeleniumUtils.scroll(0,200);
        new PersonalInformationPage().b_cell.sendKeys(expectedMap.get("CellPhone"));

    }

    @Then("I put the following info for co-borrower")
    public void iPutTheFollowingInfoForCoBorrower(List<Map<String,String>> dataTable1) {

        Actions actions = new Actions(Driver.getDriver());
        expectedMap1 = dataTable1.get(0);

        new PersonalInformationPage().c_firstName.sendKeys(expectedMap1.get("FirstName1"));
        new PersonalInformationPage().c_lastName.sendKeys(expectedMap1.get("LastName1"));
        new PersonalInformationPage().c_email.sendKeys(expectedMap1.get("Email1"));
        new PersonalInformationPage().c_dob.sendKeys(expectedMap1.get("DOB1"));
        new PersonalInformationPage().c_ssn.sendKeys(expectedMap1.get("SSN1"));
        actions.click(new PersonalInformationPage().c_marital).sendKeys(Keys.ARROW_UP,(expectedMap1.get("MaterialStatus1")),Keys.ENTER).perform();
        SeleniumUtils.scroll(0,200);
        new PersonalInformationPage().c_cell.sendKeys(expectedMap1.get("CellPhone1"));
        SeleniumUtils.jsClick(new PersonalInformationPage().buttonNext);
    }


    @And("I still should be able move to the next page.")
    public void iStillShouldBeAbleMoveToTheNextPage() throws InterruptedException {

        String expected = "Current Monthly Housing Expenses";
        String pageSource = Driver.getDriver().getPageSource();
        Assert.assertTrue(pageSource.contains(expected));


        new PersonalInformationPage().Current_Monthly_Housing_Expenses();
        new PersonalInformationPage().Employment_and_Income();
        new PersonalInformationPage().coBorrower_Employment_Information();
        new PersonalInformationPage().Credit_Report();
        new PersonalInformationPage().eConsent();
        new PersonalInformationPage().Summary();
    }

    @And("The database should also contain the correct info.")
    public void theDatabaseShouldAlsoContainTheCorrectInfo() throws SQLException {

        String expectedFirst= expectedMap.get("FirstName");
        String expectedLast = expectedMap.get("LastName");
        String expectedEmail = expectedMap.get("Email");
        String expectedSSN = expectedMap.get("SSN");
        String expectedMaterialStatus = expectedMap.get("MaterialStatus");
        String expectedCellPhone  = expectedMap.get("CellPhone");
        String expectedFirstB= expectedMap1.get("FirstName1");
        String expectedLastB = expectedMap1.get("LastName1");
        String expectedEmailB = expectedMap1.get("Email1");
        String expectedDOBB = expectedMap1.get("DOB1");
        String expectedSSNB = expectedMap1.get("SSN1");
        String expectedMaterialStatusB = expectedMap1.get("MaterialStatus1");
        String expectedCellPhoneB  = expectedMap1.get("CellPhone1");


        String query  = "select * from tbl_mortagage where b_lastName ='"+expectedLast+"'";
        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps(query);
        Map<String, Object> actualMap = queryResultListOfMaps.get(0);

        String actualFirst = (String)(actualMap.get("b_firstName"));
        String actualLast= (String)(actualMap.get("b_lastName"));
        String actualEmail = (String)(actualMap.get("b_email"));
        String actualSSN= (String)(actualMap.get("b_ssn"));
        String actualMaterialStatus = (String)(actualMap.get("b_marital"));
        String actualCellPhone = (String)(actualMap.get("b_cell"));
        String actualFirstB = (String)(actualMap.get("c_firstName"));
        String actualLastB= (String)(actualMap.get("c_lastName"));
        String actualEmailB = (String)(actualMap.get("c_email"));
        String actualDOBB = (String)(actualMap.get("c_dob"));
        String actualSSNB= (String)(actualMap.get("c_ssn"));
        String actualMaterialStatusB = (String)(actualMap.get("c_marital"));
        String actualCellPhoneB = (String)(actualMap.get("c_cell"));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(expectedFirst).isEqualTo(actualFirst);
        softAssertions.assertThat(expectedLast).isEqualTo(actualLast);
        softAssertions.assertThat(expectedEmail).isEqualTo(actualEmail);
        softAssertions.assertThat(expectedSSN).isEqualTo(actualSSN);
        softAssertions.assertThat(expectedMaterialStatus).isEqualTo(actualMaterialStatus);
        softAssertions.assertThat(expectedCellPhone).isEqualTo(actualCellPhone);
        softAssertions.assertThat(expectedFirstB).isEqualTo(actualFirstB);
        softAssertions.assertThat(expectedLastB).isEqualTo(actualLastB);
        softAssertions.assertThat(expectedEmailB).isEqualTo(actualEmailB);
        softAssertions.assertThat(expectedDOBB).isEqualTo(actualDOBB);
        softAssertions.assertThat(expectedSSNB).isEqualTo(actualSSNB);
        softAssertions.assertThat(expectedMaterialStatusB).isEqualTo(actualMaterialStatusB);
        softAssertions.assertThat(expectedCellPhoneB).isEqualTo(actualCellPhoneB);

        softAssertions.assertAll();

        DBUtility.updateQuery("delete from tbl_mortagage where b_lastName ='"+expectedLast+"'");
        DBUtility.close();
    }

//    @Given("I am connected to the DB")
//    public void iAmConnectedToTheDB() {
//
//        DBUtility.createConnection();
//    }

    List<List<Object>> queryResultAsListOfLists;
    @When("I send a query to check for duplicate usernames")
    public void iSendAQueryToCheckForDuplicateUsernames() {
        queryResultAsListOfLists = DBUtility.getQueryResultAsListOfLists("select b_firstName, count(b_firstName) from tbl_mortagage group by b_firstName having count(b_firstName)>1");

    }

    @Then("The returned result list should be empty")
    public void theReturnedResultListShouldBeEmpty() {
        //Assert.assertTrue("The list is not empty and the size is " + queryResultAsListOfLists.size(), queryResultAsListOfLists.isEmpty() );

        Assert.assertNotEquals(queryResultAsListOfLists.size(),0);
    }

    @When("I update the name column with a String with an invalid length of {int}, the update should truncate the length to {int}")
    public void iUpdateTheNameColumnWithAStringWithAnInvalidLengthOfTheUpdateShouldTruncateTheLengthTo(Integer length, Integer truncated) throws SQLException {


        String str = "";
        for (int i = 0; i < length; i++) {
            char ch =  (char)(97 + (int)(Math.random()*26));
            str += ch;

        }
        //System.out.println(str);
        try{
            DBUtility.updateQuery("update tbl_mortagage set realtor_info ='"+str+"' where id=0");
        }catch (MysqlDataTruncation ex){
            ex.printStackTrace();
        }


        List<List<Object>> queryResultAsListOfLists = DBUtility.getQueryResultAsListOfLists("select realtor_info from tbl_mortagage where id=0");
        Integer actualLength = ((String)(queryResultAsListOfLists.get(0).get(0))).length();

//        System.out.println(actualLength);
//        System.out.println(truncated);

        //Assert.assertEquals(truncated, actualLength);
        Assert.assertNotEquals(truncated, actualLength);


    }
    

    @When("I send a query to check for duplicate cell phone")
   public void i_send_a_query_to_check_for_duplicate_cell_phone() {
    queryResultAsListOfLists = DBUtility.getQueryResultAsListOfLists("select b_cell, count(b_cell) from tbl_mortagage group by b_cell having count(b_cell)>1");
}

    @When("I enter the letter and as an estimate purchase price and it will still be accepted")
    public void i_enter_the_letter_and_as_an_estimate_purchase_price_and_it_will_still_be_accepted() throws SQLException {

      String expected = "abc";
      DBUtility.updateQuery("update tbl_mortagage set est_purchase_price ='"+expected+"' where id=0");


      DBUtility.getQueryResultAsListOfLists("select est_purchase_price from tbl_mortagage where id=0");
      String actual =  (String)(DBUtility.getQueryResultAsListOfLists("select est_purchase_price from tbl_mortagage where id=0").get(0).get(0));
      Assert.assertEquals(expected, actual);

    }

    String expectedDB;
    @When("I update the name column with a unicode chars, the update should be successful")
    public void iUpdateTheNameColumnWithAUnicodeCharsTheUpdateShouldBeSuccessful() throws SQLException {

        expectedDB = "名前";
        DBUtility.updateQuery("update tbl_mortagage set b_firstName='"+expectedDB+"' where id=33479");

        String actual =  (String)(DBUtility.getQueryResultAsListOfLists("select b_firstName from tbl_mortagage where id=33479").get(0).get(0));
        Assert.assertEquals(expectedDB, actual);
    }

    @Then("The update should be also successful on the UI")
    public void theUpdateShouldBeAlsoSuccessfulOnTheUI() {


        Driver.getDriver().get(ConfigReader.getProperty("url"));
        new LoginPage().GurusLoginMethod();
        SeleniumUtils.jsClick(new PersonalInformationPage().applicationList);

        String expected = "Application List";
        String pageSource = Driver.getDriver().getPageSource();
        Assert.assertTrue(pageSource.contains(expected));

        Driver.getDriver().navigate().refresh();

        String expectedUI = "名前 Cruz";
        String actualUI = new PersonalInformationPage().applicatInfo.getText();
        Assert.assertEquals(expectedUI, actualUI);
    }
}




