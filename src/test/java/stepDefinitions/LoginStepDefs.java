package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.LoginPage;
import utilities.Driver;
import utilities.ExcelUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class LoginStepDefs  {

    LoginPage loginPage = new LoginPage();


    @Then("filling the email {string} and password {string} and click Login button")
    public void fillingTheEmailAndPasswordAndClickLoginButton(String email, String pass) throws InterruptedException {
        loginPage.LoginMethod(email, pass);
        System.out.println(Driver.getDriver().getTitle());
    }

    @When("I am on login page I filling out email and password and click login button")
    public void iAmOnLoginPageIFillingOutEmailAndPasswordAndClickLoginButton() {
        loginPage.GurusLoginMethod();
        System.out.println(Driver.getDriver().getTitle());

    }


    @When("I login with invalid email {string} and password {string} and click login button")
    public void iLoginWithInvalidEmailAndPasswordAndClickLoginButton(String email, String pass) {
        loginPage.LoginMethod(email, pass);
        loginPage.loginButton.click();
        System.out.println("Your email " + email + " has result as " + new LoginPage().textLoginFailed.getText());
    }

    @Then("I should not be able to login and an error message should be displayed")
    public void iShouldNotBeAbleToLoginAndAnErrorMessageShouldBeDisplayed() {
        System.out.println("Result of entry: " + loginPage.textLoginFailed.getText());
        System.out.println(loginPage.textLoginFailed.getText());
        Assert.assertTrue(loginPage.textLoginFailed.isDisplayed());
    }




    @When("I am on login page using data from the Excel file {string}")
    public void iAmOnLoginPageUsingDataFromTheExcelFile(String ExcelFile) throws InterruptedException, IOException {


        ExcelUtils excelUtils = new ExcelUtils(ExcelFile, "Sheet1");
        List<Map<String, String>> dataAsListOfMaps = excelUtils.getDataAsListOfMaps();


        for (int i = 1; i <= dataAsListOfMaps.size(); i++) {
            String cellEmail = excelUtils.getCellData(i, 2);
            String cellPass = excelUtils.getCellData(i, 3);
            loginPage.LoginMethod(cellEmail, cellPass);
            loginPage.loginButton.click();

            try {
                if (loginPage.textLoginFailed.isDisplayed()) {
                    System.out.println(cellEmail + " Login Failed");
                    excelUtils.setCellData("Fail", "Status", i);

                    Driver.getDriver().navigate().refresh();
                }

            } catch (Exception e) {
                excelUtils.setCellData("Pass", "Status", i);
                System.out.println(cellEmail + " Logged in");
                Driver.getDriver().navigate().back();
                Driver.getDriver().navigate().refresh();
            }
        }
    }





    @When("I login with the correct email {string} and invalid password {string} and click login button")
    public void iLoginWithTheCorrectEmailAndInvalidPasswordAndClickLoginButton(String email, String pass) {
        loginPage.LoginMethod(email, pass);
        loginPage.loginButton.click();
        System.out.println("your password is incorrect: " + loginPage.textLoginFailed.getText());
    }

    @When("I login with no email {string} and no password {string} and click login button")
    public void iLoginWithNoEmailAndNoPasswordAndClickLoginButton(String email, String pass) {
        loginPage.LoginMethod(email, pass);
        loginPage.loginButton.click();
        System.out.println(Driver.getDriver().getTitle());
    }


    @When("Login a user with {string}, {string}")
    public void loginAUserWith(String email, String password) {
        loginPage.LoginMethod(email, password);
        loginPage.loginButton.click();
    }


    @When("The user enters the valid credentials as {string} for username and {string} for password")
    public void theUserEntersTheValidCredentialsAsForUsernameAndForPassword(String email, String password) {
        loginPage.LoginMethod(email, password);
        loginPage.loginButton.click();
    }

    @Then("The user should be able to login and land on the homepage")
    public void theUserShouldBeAbleToLoginAndLandOnTheHomepage() {
        Assert.assertTrue(Driver.getDriver().getTitle().equals("Loan Application"));
        //Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("http://qa-duobank.us-east-2.elasticbeanstalk.com/dashboard.php"));
    }



}





