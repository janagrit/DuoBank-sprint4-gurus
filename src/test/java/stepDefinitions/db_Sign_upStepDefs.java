package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.SoftAssertions;
import pages.LoginPage;
import utilities.DBUtility;
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
        expectedPasswordMd5 = DigestUtils.md5Hex(pass );

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
}
