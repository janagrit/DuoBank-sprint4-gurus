package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;
import utilities.DBUtility;

import java.sql.SQLException;

public class db_User_InfoStepDefs {

    LoginPage loginPage = new LoginPage();


    @Then("I can change my info on UI")
    public void iCanChangeMyInfoOnUI() {

        System.out.println("Current user's name is: " + loginPage.user_name_OnUI.getText());

        // !!! no option to update names on UI
    }

    String email1;
    String updatedFirst;
    String updatedLast;
    @When("I update First {string} & Last {string} name of user with email {string}")
    public void iUpdateFirstLastNameOfUserWithEmail(String newFirst, String newLast, String email) throws SQLException {

        email1 = email;
        updatedFirst = newFirst;
        updatedLast = newLast;
        DBUtility.updateQuery("update tbl_user set first_name='"+newFirst+"', last_name='"+newLast+"' where email='"+email+"' ");
    }

    @Then("I should see the updated First & Last name on the UI")
    public void iShouldSeeTheUpdatedFirstLastNameOnTheUI() {

        String actual = loginPage.user_name_OnUI.getText();
        System.out.println(actual);

        Assert.assertEquals(updatedFirst +" "+updatedLast, actual);

        DBUtility.close();

    }

}
