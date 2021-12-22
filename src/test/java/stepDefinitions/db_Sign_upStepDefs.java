package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import pages.LoginPage;
import utilities.DBUtility;
import utilities.Driver;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class db_Sign_upStepDefs {


    LoginPage signPage = new LoginPage();
    Map<String, String>  expectedMap;
    String first;
    String last;
    String email;
    String pass;
    String expectedPasswordMd5;



    @When("I sign up with the following info")
    public void iSignUpWithTheFollowingInfo(List<Map<String,String>> dataTable) {

        expectedMap = dataTable.get(0);
        first = expectedMap.get("first");
        last = expectedMap.get("last");
        email = expectedMap.get("email");
        pass = expectedMap.get("password");
        expectedPasswordMd5 = DigestUtils.md5Hex(pass);

        signPage.signUpMethod(first, last, email, pass);
        signPage.registerButton.click();
    }


    @And("The database should also have the correct info with a new user")
    public void theDatabaseShouldAlsoHaveTheCorrectInfoWithANewUser() throws SQLException {


        String query  = "select * from tbl_user where email ='"+email+"'";

        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps(query);
        Map<String, Object> actualMap = queryResultListOfMaps.get(0);
        System.out.println(actualMap);

        String actualFirst= (String)(actualMap.get("first_name"));
        String actualLast = (String)(actualMap.get("last_name"));
        String actualEmail = (String)(actualMap.get("email"));
        String actualPassword = (String)(actualMap.get("password"));

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(first).isEqualTo(actualFirst);
        softAssertions.assertThat(last).isEqualTo( actualLast);
        softAssertions.assertThat(email).isEqualTo( actualEmail);
        softAssertions.assertThat(expectedPasswordMd5).isEqualTo( actualPassword);

        System.out.println(email);
        System.out.println(actualEmail);

        softAssertions.assertAll();

        DBUtility.updateQuery("delete from tbl_user where email='"+email+"'");
        DBUtility.close();
    }

    @When("I add a new user to the database with the following info")
    public void iAddANewUserToTheDatabaseWithTheFollowingInfo(List<Map<String,String>> dataTable) throws SQLException {

//        Map<String, String> expectedMap = dataTable.get(0);
//
//        String passwordAsMd5 = DigestUtils.md5Hex(expectedMap.get("password"));
//        String query = "INSERT INTO tbl_user (first_name, last_name, email, password) values ('"+expectedMap.get("first")+"', '"+expectedMap.get("last")+"', '"+expectedMap.get("email")+"', '"+passwordAsMd5+"')";
//
//        DBUtility.updateQuery(query);

        expectedMap = dataTable.get(0);
        System.out.println(expectedMap);

        expectedPasswordMd5 = DigestUtils.md5Hex(expectedMap.get("password"));

//        java.sql.SQLException: Field 'zone_id' doesn't have a default value
        String query ="  INSERT INTO tbl_user ( email, password, first_name, last_name, phone, image, type, created_at, modified_at, zone_id, church_id, country_id, active)" +
                " values " +
                "('"+expectedMap.get("first_name")+"','"+ expectedMap.get("last_name")+"', '"+expectedMap.get("email")
                +"', '"+expectedPasswordMd5+"', '"+expectedMap.get("phone")+"', " +
                " '"+expectedMap.get("image")+"', '"+expectedMap.get("type")+"', '"+expectedMap.get("created_at")+"' ," +
                "'"+expectedMap.get("modified_at")+"', '"+expectedMap.get("zone_id")+"', '"+expectedMap.get("church_id")+"', '"+expectedMap.get("country_id")+"', '"+expectedMap.get("active")+"'   ) ";

        DBUtility.updateQuery(query);
    }

    @Then("I should be able to log in with the email {string} and password {string} on the UI")
    public void iShouldBeAbleToLogInWithTheEmailAndPasswordOnTheUI(String email, String password) throws SQLException {

        Driver.getDriver().get("http://qa-duobank.us-east-2.elasticbeanstalk.com/index.php");
        LoginPage loginPage = new LoginPage();
        loginPage.LoginMethod(email, password);
        loginPage.loginButton.click();

        Assert.assertTrue(Driver.getDriver().getTitle().equals("Loan Application"));

        DBUtility.updateQuery("delete from tbl_user where email='"+email+"'");
        DBUtility.close();
    }


    List<String> actualColumnNames;
    @When("I retrieve the column names for the Songs users")
    public void iRetrieveTheColumnNamesForTheSongsUsers() {
        actualColumnNames = DBUtility.getColumnNames("select * from tbl_user limit 1");

    }

    @Then("It should be the following")
    public void itShouldBeTheFollowing(List<String> expected) {

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualColumnNames).isEqualTo(expected);

        DBUtility.close();
        softAssertions.assertAll();
    }




    String first1;
    String last1;
    String email1;
    String pass1;

    @When("I sign up with the following info  {string}  {string}  {string}  {string}")
    public void iSignUpWithTheFollowingInfo(String first, String last, String email, String pass) {

        first1 = first;
        last1 = last;
        email1 = email;
        pass1 = pass;

        signPage.signUpMethod(first, last, email, pass);
        signPage.registerButton.click();


    }



    @And("The database should also have the correct info without spaces")
    public void theDatabaseShouldAlsoHaveTheCorrectInfoWithoutSpaces() throws SQLException {

        String query  = "select * from tbl_user where email='"+email1.trim()+"'";
        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps(query);
        Map<String, Object> actualMap = queryResultListOfMaps.get(0);


        String actualFirst= (String)(actualMap.get("first_name"));
        String actualLast = (String)(actualMap.get("last_name"));
        String actualEmail = (String)(actualMap.get("email"));
        String actualPassword = (String)(actualMap.get("password"));

        String expectedFirst = first1.trim();
        String expectedLast = last1.trim();
        System.out.println(expectedFirst + "   " + expectedLast);


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(expectedFirst).isEqualTo(actualFirst);
        softAssertions.assertThat(expectedLast).isEqualTo( actualLast);
        softAssertions.assertThat(email1.trim()).isEqualTo( actualEmail);


        softAssertions.assertAll();

        DBUtility.updateQuery("delete from tbl_user where email='"+email1.trim()+"'");
        DBUtility.close();
    }


    List<List<Object>> queryResultAsListOfLists;
    @When("I send a query to check for duplicate usernames")
    public void iSendAQueryToCheckForDuplicateUsernames() {

        queryResultAsListOfLists = DBUtility.getQueryResultAsListOfLists("select email, count(*) from tbl_user group by email having count(*)>1");

    }

    @Then("The returned result list should be empty")
    public void theReturnedResultListShouldBeEmpty() {
        Assert.assertTrue("The list is not empty and the size is " + queryResultAsListOfLists.size(),
                queryResultAsListOfLists.isEmpty() );

    }

    String expectedPassword;
    @When("I fill up the fields with the following new user information")
    public void iFillUpTheFieldsWithTheFollowingNewUserInformation(List<Map<String, String>> dataTable) {
        first = dataTable.get(0).get("First Name");
        last = dataTable.get(0).get("Last Name");
        email = dataTable.get(0).get("Email");
        pass = dataTable.get(0).get("Password");
        expectedPassword = DigestUtils.md5Hex(pass);


        signPage.signUpMethod(first, last, email, pass);
        signPage.registerButton.click();



    }

    @Then("This information should be stored correctly in the database")
    public void thisInformationShouldBeStoredCorrectlyInTheDatabase() throws SQLException {

        String query = "select first_name, last_name, email, password from tbl_user where email = '"+email+"'";


        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps(query);
        Map<String, Object> map = queryResultListOfMaps.get(0);

        System.out.println(map);

        Assert.assertEquals(first, (String)(map.get("first_name")));
        Assert.assertEquals(last, (String)(map.get("last_name")));
        Assert.assertEquals(email, ((String)(map.get("email"))).toLowerCase());
        Assert.assertEquals(expectedPassword, (String)(map.get("password")));

        DBUtility.updateQuery("delete from tbl_user where email='"+email+"'");

    }
}
