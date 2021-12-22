package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.*;
import utilities.DBUtility;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class db_EmploymentAndIncomeStepDef {

    Map<String, String> expectedMap;

    @Given("I am connected to DB")
    public void iAmConnectedToDB() {

        DBUtility.createConnection();
        System.out.println("Connection is successful with DataBase");
    }

    @When("I fill out Mortgage Application page using  following info")
    public void iFillOutMortgageApplicationPageUsingFollowingInfo(List<Map<String, String>> dataTable) {
        expectedMap = dataTable.get(0);

        Actions actions = new Actions(Driver.getDriver());

        new EmploymentAndIncomePage().Personal_Info();
//
//        new PreapprovalDetailsPage().realtorInfo.sendKeys(expectedMap.get("REALTOR INFO"));
//        new PreapprovalDetailsPage().estimatedPrice.sendKeys(expectedMap.get("ESTIMATED PURCHASE PRICE"));
//        new PreapprovalDetailsPage().downPaymentAmount.sendKeys(expectedMap.get("DOWN PAYMENT AMOUNT"));
//        new PreapprovalDetailsPage().downPaymentPercentage.sendKeys(expectedMap.get("DOWN PAYMENT PERCENTAGE"));
//        new PreapprovalDetailsPage().buttonNext.click();
        new PersonalInformationPage().b_firstName.sendKeys(expectedMap.get("FIRST NAME"));
        new PersonalInformationPage().b_lastName.sendKeys(expectedMap.get("LAST NAME"));
        new PersonalInformationPage().b_email.sendKeys(expectedMap.get("EMAIL"));
        new PersonalInformationPage().b_ssn.sendKeys(expectedMap.get("SSN"));
        actions.click(new PersonalInformationPage().b_marital).sendKeys(Keys.ARROW_UP, (expectedMap.get("MaterialStatus")), Keys.ENTER).perform();
        SeleniumUtils.scroll(0, 200);
        new PersonalInformationPage().b_cell.sendKeys(expectedMap.get("CELL PHONE"));
        SeleniumUtils.jsClick(new PersonalInformationPage().buttonNext);
        new ExpensesPage().monthlyRentalPayment.sendKeys(expectedMap.get("MONTHLY RENTAL PAYMENT"));
        new ExpensesPage().buttonNext.click();
        new EmploymentAndIncomePage().employer.sendKeys(expectedMap.get("EMPLOYER NAME"));
        new EmploymentAndIncomePage().position.sendKeys(expectedMap.get("POSITION"));
        new EmploymentAndIncomePage().city.sendKeys(expectedMap.get("CITY"));
        //new EmploymentAndIncomePage().state.sendKeys(expectedMap.get("STATE"));
        new EmploymentAndIncomePage().startDate.sendKeys(expectedMap.get("START DATE"));
        new EmploymentAndIncomePage().grossMonthlyIncome.sendKeys(expectedMap.get("GROSS MONTHLY INCOME"));
        new EmploymentAndIncomePage().buttonnext.click();
        new CreditReportPage().buttonnext.click();
//        new PersonalInformationPage().Credit_Report();
//        new PersonalInformationPage().EconcentPage();
//       new PersonalInformationPage().Summary();


    }

    @Then("I am able move to next page")
    public void iAmAbleMoveToNextPage() {
        String expected = "PreApproval Inquiry";
        String pageSource = Driver.getDriver().getPageSource();
        Assert.assertTrue(pageSource.contains(expected));

       // new PersonalInformationPage().Credit_Report();
        new PersonalInformationPage().EconcentPage();
        new PersonalInformationPage().Summary();
    }


    @Then("The database should  have the correct info")
    public void theDatabaseShouldAlsoHaveTheCorrectInfo() throws SQLException {

//        String expectedRealtorInfo = expectedMap.get("REALTOR INFO");
//        String expectedEstPrice = expectedMap.get("ESTIMATED PURCHASE PRICE");
//        String expectedDownPayment = expectedMap.get("DOWN PAYMENT AMOUNT");//50
//        String expectedDownPaymentPercentage = expectedMap.get("DOWN PAYMENT PERCENTAGE");
        String expectedFirstName = expectedMap.get("FIRST NAME");
        String expectedLastName = expectedMap.get("LAST NAME");
        String expectedSSN = expectedMap.get("SSN");
        String expectedMaterialStatus = expectedMap.get("MaterialStatus");
        String expectedCellPhone = expectedMap.get("CELL PHONE");
        String expectedMonthlyRentalPayment = expectedMap.get("MONTHLY RENTAL PAYMENT");
        String expectedEmployerName = expectedMap.get("EMPLOYER NAME");
        String expectedPosition = expectedMap.get("POSITION");
        String expectedCity = expectedMap.get("CITY");
        //String expectedState = expectedMap.get("STATE");
        String expectedStartDate = expectedMap.get("START DATE");
        String expectedGrossMonthlyIncome = expectedMap.get("GROSS MONTHLY INCOME");




        String query = "select * from tbl_mortagage where b_firstName ='" + expectedFirstName.trim() + "'";

        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps(query);
        Map<String, Object> actualMap = queryResultListOfMaps.get(0);

//
//
//        String actualRealtorInfo = (String) (actualMap.get("realtor_info"));
//        String actualEstPrice = (String) (actualMap.get("est_purchase_price"));
//        String actualDownPayment = (String) (actualMap.get("down_payment"));//2999
//        String actualDownPaymentPercentage = (String) (actualMap.get("down_payment_percent"));
        String actualFirstName = (String)(actualMap.get("b_firstName"));
        String actualLastName= (String)(actualMap.get("b_lastName"));
        String actualSSN= (String)(actualMap.get("b_ssn"));
        String actualMaterialStatus = (String)(actualMap.get("b_marital"));
        String actualCellPhone = (String)(actualMap.get("b_cell"));
        String actualMonthlyRentalPayment = (String) (actualMap.get("monthly_rental_payment"));

        String actualEmployerName = (String) (actualMap.get("employer_name"));
        String actualPosition =(String) (actualMap.get("position"));
        String actualCity = (String) (actualMap.get("city"));
        String actualStartDate = (String) (actualMap.get("start_date"));

        String actualGrossMonthlyIncome = (String) (actualMap.get("gross_monthly_income"));



        SoftAssertions softAssertions = new SoftAssertions();
//        softAssertions.assertThat(expectedRealtorInfo).isEqualTo(actualRealtorInfo);
//        softAssertions.assertThat(expectedEstPrice).isEqualTo(actualEstPrice);
//        softAssertions.assertThat(expectedDownPayment).isEqualTo(actualDownPayment);
//        softAssertions.assertThat(expectedDownPaymentPercentage).isEqualTo(actualDownPaymentPercentage);
        softAssertions.assertThat(expectedFirstName).isEqualTo(actualFirstName);
        softAssertions.assertThat(expectedLastName).isEqualTo(actualLastName);
        softAssertions.assertThat(expectedSSN).isEqualTo(actualSSN);
        softAssertions.assertThat(expectedMaterialStatus).isEqualTo(actualMaterialStatus);
        softAssertions.assertThat(expectedCellPhone).isEqualTo(actualCellPhone);
        softAssertions.assertThat( expectedMonthlyRentalPayment).isEqualTo(actualMonthlyRentalPayment);
        softAssertions.assertThat(expectedEmployerName).isEqualTo(actualEmployerName);
        softAssertions.assertThat(expectedPosition).isEqualTo(actualPosition);
        softAssertions.assertThat(expectedCity).isEqualTo(actualCity);
       // softAssertions.assertThat(expectedState).isEqualTo(actualState);
        softAssertions.assertThat(expectedStartDate).isEqualTo(actualStartDate);
        softAssertions.assertThat(expectedGrossMonthlyIncome).isEqualTo(actualGrossMonthlyIncome);





        softAssertions.assertAll();

        DBUtility.updateQuery("delete from tbl_mortagage where b_firstName='" + expectedFirstName + "'");
        DBUtility.close();


    }






    List<List<Object>> queryResultAsListOfLists;

    @When("I send a query to check for duplicate SSN")
    public void iSendAQueryToCheckForDuplicateSSN() {

        queryResultAsListOfLists = DBUtility.getQueryResultAsListOfLists("select b_ssn, count(b_ssn) from tbl_mortagage group by b_ssn having count(b_ssn)>1");
    }


    @Then("The returned result list should be empty.")
    public void theReturnedResultListShouldBeEmpty() {
        Assert.assertFalse("The list is not empty and the size is " + queryResultAsListOfLists.size(), queryResultAsListOfLists.isEmpty() );

    }
String updatedPosition;
    @When("I update first name {string} position  to  {string}")
    public void iUpdateFirstNamePositionTo(String firstName, String updatedPosition1) throws SQLException {

        updatedPosition = updatedPosition1;
        DBUtility.updateQuery("update tbl_mortagage set position ='"+updatedPosition1+"' where b_firstName='"+firstName+"'");


    }

    @Then("I should see the updated position on the DB")
    public void iShouldSeeTheUpdatedPositionOnTheDB() {

        Assert.assertTrue("The list is updated "+ DBUtility.getQueryResultAsListOfLists("select * from tbl_mortagage where position ='"+updatedPosition+"'"), queryResultAsListOfLists.contains(updatedPosition) );


    }

    @When("I send a query to check for email duplicate")
    public void iSendAQueryToCheckForEmailDuplicate() {

        queryResultAsListOfLists = DBUtility.getQueryResultAsListOfLists("select b_email, count(b_email) from tbl_mortagage group by b_email having count(b_email)>1");
    }

}

