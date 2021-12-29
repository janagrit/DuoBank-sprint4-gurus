package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.ApplicationPage;
import utilities.DBUtility;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class db_ApplicationStepDefs {

    ApplicationPage ap = new ApplicationPage();
    String email;

    @When("I land on a Dashboard and I click on Application list")
    public void iLandOnADashboardAndIClickOnApplicationList() {
        ap.applicationList.click();
    }

    @When("I click view Details to open application")
    public void iClickViewDetailsToOpenApplication() {

        ap.entries.click();
        ap.allEntries.click();
        ap.viewDetails.click();
    }

    @Then("I verify Application details are matching to Application details in the DB")
    public void iVerifyApplicationDetailsAreMatchingToApplicationDetailsInTheDB() throws SQLException {


        String query = "SELECT * FROM tbl_mortagage WHERE b_email='"+ email +"'" ;

        System.out.println(query);

        DBUtility.getQueryResultListOfMaps(query);

        List<Map<String, Object>> queryResultMap = DBUtility.getQueryResultListOfMaps(query);

        Map<String, Object> map = queryResultMap.get(0);

        Assert.assertEquals(map.get("b_firstName"), ap.firstName.getText());
        Assert.assertEquals(map.get("b_lastName"), ap.lastName.getText());
        Assert.assertEquals(map.get("b_ssn"), ap.SSN.getText());
        Assert.assertEquals(map.get("realtor_info"), ap.realtorInfo.getText());
        Assert.assertEquals(map.get("purpose_loan"), ap.purposeLoan.getText());
        Assert.assertEquals((map.get("down_payment_percent")+" %"), ap.percentage.getText());
        Assert.assertEquals(map.get("city"), ("["+ ap.city.getText()+"]"));
        Assert.assertEquals(map.get("position"), ("["+ap.position.getText()+"]"));

        DBUtility.updateQuery("delete from tbl_user where username='"+email +"'");



    }
}
