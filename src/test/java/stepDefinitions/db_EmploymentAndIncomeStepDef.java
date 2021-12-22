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

        new PreapprovalDetailsPage().realtorInfo.sendKeys(expectedMap.get("REALTOR INFO"));
        new PreapprovalDetailsPage().estimatedPrice.sendKeys(expectedMap.get("ESTIMATED PURCHASE PRICE"));
        new PreapprovalDetailsPage().downPaymentAmount.sendKeys(expectedMap.get("DOWN PAYMENT AMOUNT"));
        new PreapprovalDetailsPage().downPaymentPercentage.sendKeys(expectedMap.get("DOWN PAYMENT PERCENTAGE"));
        new PreapprovalDetailsPage().buttonNext.click();
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
        new EmploymentAndIncomePage().state.sendKeys(expectedMap.get("STATE"));
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

        String expectedRealtorInfo = expectedMap.get("REALTOR INFO");
        String expectedEstPrice = expectedMap.get("ESTIMATED PURCHASE PRICE");
        String expectedDownPayment = expectedMap.get("DOWN PAYMENT AMOUNT");
        String expectedDownPaymentPercentage = expectedMap.get("DOWN PAYMENT PERCENTAGE");
        String expectedFirstName = expectedMap.get("FIRST NAME");
        String expectedLastName = expectedMap.get("LAST NAME");
        String expectedSSN = expectedMap.get("SSN");
        String expectedMaterialStatus = expectedMap.get("MaterialStatus");
        String expectedCellPhone = expectedMap.get("CELL PHONE");
        String expectedMonthlyRentalPayment = expectedMap.get("MONTHLY RENTAL PAYMENT");
        String expectedEmployerName = expectedMap.get("EMPLOYER NAME");
        String expectedPosition = expectedMap.get("POSITION");
        String expectedCity = expectedMap.get("CITY");
        String expectedState = expectedMap.get("STATE");
        String expectedStartDate = expectedMap.get("START DATE");
        String expectedGrossMonthlyIncome = expectedMap.get("GROSS MONTHLY INCOME");




        String query = "select * from tbl_mortagage where realtor_info ='" + expectedRealtorInfo + "'";

        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps(query);
        Map<String, Object> actualMap = queryResultListOfMaps.get(0);


        String actualRealtorInfo = (String) (actualMap.get("REALTOR INFO"));
        String actualEstPrice = (String) (actualMap.get("ESTIMATED PURCHASE PRICE"));
        String actualDownPayment = (String) (actualMap.get("DOWN PAYMENT AMOUNT"));
        String actualDownPaymentPercentage = (String) (actualMap.get("DOWN PAYMENT PERCENTAGE"));
        String actualFirstName = (String) (actualMap.get("FIRST NAME"));
        String actualLastName = (String) (actualMap.get("LAST NAME"));
        String actualSSN = (String) (actualMap.get("SSN"));
        String actualMaterialStatus = (String) (actualMap.get("MaterialStatus"));
        String actualCellPhone = (String) (actualMap.get("CELL PHONE"));
        String actualMonthlyRentalPayment = (String) (actualMap.get("MONTHLY RENTAL PAYMENT"));
        String actualEmployerName = (String) (actualMap.get("EMPLOYER NAME"));
        String actualPosition =(String) (actualMap.get("POSITION"));
        String actualCity = (String) (actualMap.get("CITY"));
        String actualState = (String) (actualMap.get("STATE"));
        String actualStartDate = (String) (actualMap.get("START DATE"));
        String actualGrossMonthlyIncome = (String) (actualMap.get("GROSS MONTHLY INCOME"));




        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(expectedRealtorInfo).isEqualTo(actualRealtorInfo);
        softAssertions.assertThat(expectedEstPrice).isEqualTo(actualEstPrice);
        softAssertions.assertThat(expectedDownPayment).isEqualTo(actualDownPayment);
        softAssertions.assertThat(expectedDownPaymentPercentage).isEqualTo(actualDownPaymentPercentage);
        softAssertions.assertThat(expectedFirstName).isEqualTo(actualFirstName);
        softAssertions.assertThat(expectedLastName).isEqualTo(actualLastName);
        softAssertions.assertThat(expectedSSN).isEqualTo(actualSSN);
        softAssertions.assertThat(expectedMaterialStatus).isEqualTo(actualMaterialStatus);
        softAssertions.assertThat(expectedCellPhone).isEqualTo(actualCellPhone);
        softAssertions.assertThat( expectedMonthlyRentalPayment).isEqualTo(actualMonthlyRentalPayment);
        softAssertions.assertThat(expectedEmployerName).isEqualTo(actualEmployerName);
        softAssertions.assertThat(expectedPosition).isEqualTo(actualPosition);
        softAssertions.assertThat(expectedCity).isEqualTo(actualCity);
        softAssertions.assertThat(expectedState).isEqualTo(actualState);
        softAssertions.assertThat(expectedStartDate).isEqualTo(actualStartDate);
        softAssertions.assertThat(expectedGrossMonthlyIncome).isEqualTo(actualGrossMonthlyIncome);





        softAssertions.assertAll();

        DBUtility.updateQuery("delete from tbl_mortagage realtor_info ='" + expectedRealtorInfo + "'");
        DBUtility.close();


    }


}
