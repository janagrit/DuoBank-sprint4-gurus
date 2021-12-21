package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.SoftAssertions;
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
        PreapprovalDetailsPage preapprovalDetailsPage = new PreapprovalDetailsPage();
        PersonalInformationPage personalInformationPage = new PersonalInformationPage();
        ExpensesPage expensesPage = new ExpensesPage();
        EmploymentAndIncomePage employmentAndIncomePage = new EmploymentAndIncomePage();
        CreditReportPage creditReportPage = new CreditReportPage();
        EconcentPage econcentPage = new EconcentPage();
        SummaryPage summaryPage = new SummaryPage();

        preapprovalDetailsPage.realtorInfo.sendKeys(expectedMap.get("REALTOR INFO"));
        preapprovalDetailsPage.estimatedPrice.sendKeys(expectedMap.get("ESTIMATED PURCHASE PRICE"));
        preapprovalDetailsPage.downPaymentAmount.sendKeys(expectedMap.get("DOWN PAYMENT AMOUNT"));
        preapprovalDetailsPage.downPaymentPercentage.sendKeys(expectedMap.get("DOWN PAYMENT PERCENTAGE"));
        preapprovalDetailsPage.buttonNext.click();
        personalInformationPage.b_firstName.sendKeys(expectedMap.get("FIRST NAME"));
        personalInformationPage.b_lastName.sendKeys(expectedMap.get("LAST NAME"));
        personalInformationPage.b_email.sendKeys(expectedMap.get("EMAIL"));
        personalInformationPage.b_ssn.sendKeys(expectedMap.get("SSN"));
        actions.click(new PersonalInformationPage().b_marital).sendKeys(Keys.ARROW_UP, (expectedMap.get("MaterialStatus")), Keys.ENTER).perform();
        SeleniumUtils.scroll(0, 200);
        personalInformationPage.b_cell.sendKeys(expectedMap.get("CELL PHONE"));
        SeleniumUtils.jsClick(new PersonalInformationPage().buttonNext);
        expensesPage.monthlyRentalPayment.sendKeys(expectedMap.get("MONTHLY RENTAL PAYMENT"));
        expensesPage.buttonNext.click();
        employmentAndIncomePage.employer.sendKeys(expectedMap.get("EMPLOYER NAME"));
        employmentAndIncomePage.position.sendKeys(expectedMap.get("POSITION"));
        employmentAndIncomePage.city.sendKeys(expectedMap.get("CITY"));
        employmentAndIncomePage.state.sendKeys(expectedMap.get("STATE"));
        employmentAndIncomePage.startDate.sendKeys(expectedMap.get("START DATE"));
        employmentAndIncomePage.grossMonthlyIncome.sendKeys(expectedMap.get("GROSS MONTHLY INCOME"));
        employmentAndIncomePage.buttonnext.click();
        creditReportPage.buttonnext.click();
        econcentPage.e_firstName.sendKeys(expectedMap.get("e_first Name"));
        econcentPage.e_lastName.sendKeys(expectedMap.get("e_last Name"));
        econcentPage.e_email.sendKeys(expectedMap.get("e_email"));
        SeleniumUtils.jsClick(new EconcentPage().clickAgree);
        //SeleniumUtils.jsClick(new EconcentPage().buttonnext);
        econcentPage.buttonNext.click();
         summaryPage.clickSave.click();


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
        String expectedE_firstName = expectedMap.get("e_first Name");
        String expectedE_lastName = expectedMap.get("e_last Name");
        String expectedE_email = expectedMap.get("e_email");



        String query = "select * from tbl_mortagage where employer_name ='" + expectedEmployerName + "'";

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
        String actualE_firstName = (String) (actualMap.get("e_first Name"));
        String actualE_lastName =(String) (actualMap.get("e_last Name"));
        String actualE_email = (String) (actualMap.get("e_email"));



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
        softAssertions.assertThat(expectedE_firstName).isEqualTo(actualE_firstName);
        softAssertions.assertThat(expectedE_lastName).isEqualTo(actualE_lastName);
        softAssertions.assertThat(expectedE_email).isEqualTo(actualE_email);




        softAssertions.assertAll();

        DBUtility.updateQuery("delete from tbl_mortagage where employer_name ='" + expectedEmployerName + "'");
        DBUtility.close();


    }
}
