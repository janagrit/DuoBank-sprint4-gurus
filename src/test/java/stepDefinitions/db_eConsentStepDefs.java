package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.EmploymentAndIncomePage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.DBUtility;
import utilities.Driver;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class db_eConsentStepDefs {


    String firstName;
    String lastName;
    String email;

    String expectedFirstName;
    String expectedLastName;
    String expectedEmail;
    String query;

    List<String> expectedColumns;
    List<String> actualColumns;

    String expectedFirstName2;
    String actualFirstName2;



    @Then("After I complete all required information I should land on Econcent page")
    public void afterICompleteAllRequiredInformationIShouldLandOnEconcentPage() {
        new EmploymentAndIncomePage().getToCreditReport();


    }
    @Then("verify that I am on Econcent page")
    public void verifyThatIAmOnEconcentPage() {

        Assert.assertEquals("Loan Application", Driver.getDriver().getTitle());


    }

    @When("I fill up the fields with the following user information")
    public void iFillUpTheFieldsWithTheFollowingUserInformation(List<Map<String, String>> dataTable) throws SQLException {

        firstName = dataTable.get(0).get("First Name");
        lastName = dataTable.get(0).get("Last Name");
        email = dataTable.get(0).get("Email");

//        EconsentPage econsentPage = new EconsentPage();
//
//        econsentPage.firsName.sendKeys(firstName);
//        econsentPage.lastName.sendKeys(lastName);
//        econsentPage.email.sendKeys(email);
//        econsentPage.agreeButton.click();
//        econsentPage.nextButton.click();

        String query1 = "update tbl_mortagage set eConsent_declarer_FirstName = '"+firstName+"', eConsent_declarer_LastName = '"+lastName+"', " +
                           "eConsent_declarer_Email = '"+email+"' where id ='528'";

        DBUtility.updateQuery(query1);


    }
    @Then("This information should be stored properly in the database")
    public void thisInformationShouldBeStoredProperlyInTheDatabase() throws SQLException{

        String query = "select eConsent_declarer_FirstName, eConsent_declarer_LastName, eConsent_declarer_Email from tbl_mortagage where eConsent_declarer_Email = '" +email+"'";


        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps(query);
        Map<String, Object> map = queryResultListOfMaps.get(0);

        System.out.println(map);

        Assert.assertEquals(firstName, (String) (map.get("eConsent_declarer_FirstName")));
        Assert.assertEquals(lastName, (String) (map.get("eConsent_declarer_LastName")));
        Assert.assertEquals(email, ((String) (map.get("eConsent_declarer_Email"))).toLowerCase());

    }



    @When("I send the query to update econsent page first name, last name and email")
    public void iSendTheQueryToUpdateEconsentPageFirstNameLastNameAndEmail() {
        expectedFirstName = "Cameron";
        expectedLastName = "Diaz";
        expectedEmail = "cam_diaz@hotmail.com";


        query = "select * from tbl_mortagage where eConsent_declarer_Email = '" + expectedEmail + "'";

    }


    @Then("The actual output from the query should match the expected one that I sent to query")
    public void theActualOutputFromTheQueryShouldMatchTheExpectedOneThatISentToQuery() throws SQLException {
        List<Map<String, Object>> listOfMaps = DBUtility.getQueryResultListOfMaps(query);
        Map<String, Object> map = listOfMaps.get(0);
        Assert.assertEquals(map.get("eConsent_declarer_Email"), expectedEmail);
        Assert.assertEquals(map.get("eConsent_declarer_FirstName"), expectedFirstName);
        Assert.assertEquals(map.get("eConsent_declarer_LastName"), expectedLastName);

        DBUtility.updateQuery("delete from tbl_mortagage where email='"+email+"'");


}

    @When("I send a query to database to retrieve the names of columns on the eConcent page table")
    public void iSendAQueryToDatabaseToRetrieveTheNamesOfColumnsOnTheEConcentPageTable() {

        expectedColumns = Arrays.asList("eConsent_declarer", "eConsent_declarer_FirstName", "eConsent_declarer_LastName", "eConsent_declarer_Email");

        actualColumns = DBUtility.getColumnNames("select eConsent_declarer,eConsent_declarer_FirstName,eConsent_declarer_LastName,eConsent_declarer_Email from tbl_mortagage limit 1;");
    }

    @Then("The retrieved columns should be similar to the expected columns")
    public void theRetrievedColumnsShouldBeSimilarToTheExpectedColumns() {
        Assert.assertEquals(actualColumns, expectedColumns);

    }

    @When("I send a query to update realtor info field with unicode value")
    public void iSendAQueryToUpdateRealtorInfoFieldWithUnicodeValue() throws SQLException {
        expectedFirstName2 = "黄 麗";
        query = "update tbl_mortagage set eConsent_declarer_FirstName ='"+expectedFirstName2+"' where id='315'";


        DBUtility.updateQuery(query);

        Map<String, Object> map = DBUtility.getQueryResultListOfMaps("select * from tbl_mortagage where id = '315'").get(0);
        actualFirstName2 = (String)(map.get("eConsent_declarer_FirstName"));


    }

    @Then("The database should update the entry")
    public void theDatabaseShouldUpdateTheEntry() {

        Assert.assertEquals(actualFirstName2, expectedFirstName2);

    }

    @When("I send query to update the primary key column with null value")
    public void iSendQueryToUpdateThePrimaryKeyColumnWithNullValue() {
        query = "update tbl_mortagage set id = null where id = '315'";

    }

    @Then("The database should throw an exception")
    public void theDatabaseShouldThrowAnException() {
        try{
            DBUtility.updateQuery(query);
            Assert.assertTrue(false);
        }catch(Exception exception1){
            Assert.assertTrue(true);
        }
    }






}

