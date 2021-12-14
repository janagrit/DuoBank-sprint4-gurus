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


    @Then("filling the email {string} and password {string} and click Login button")
    public void fillingTheEmailAndPasswordAndClickLoginButton(String email, String pass) throws InterruptedException {
        new LoginPage().LoginMethod(email, pass);
        System.out.println(Driver.getDriver().getTitle());
    }

    @When("I am on login page I filling out email and password and click login button")
    public void iAmOnLoginPageIFillingOutEmailAndPasswordAndClickLoginButton() {
        new LoginPage().GurusLoginMethod();
        System.out.println(Driver.getDriver().getTitle());
        Assert.assertTrue(Driver.getDriver().getTitle().equals("Loan Application"));
    }


    @When("I login with invalid email {string} and password {string} and click login button")
    public void iLoginWithInvalidEmailAndPasswordAndClickLoginButton(String email, String pass) {
        new LoginPage().LoginMethod(email, pass);
        System.out.println("Your email " + email + " has result as " + new LoginPage().textLoginFailed.getText());
    }

    @Then("I should not be able to login and an error message should be displayed")
    public void iShouldNotBeAbleToLoginAndAnErrorMessageShouldBeDisplayed() {
        System.out.println("Result of entry: " + new LoginPage().textLoginFailed.getText());
        Assert.assertTrue(new LoginPage().textLoginFailed.isDisplayed());
    }


    @When("I am on login page using data from the Excel file {string}")
    public void iAmOnLoginPageUsingDataFromTheExcelFile(String ExcelFile) throws InterruptedException, IOException {


        ExcelUtils excelUtils = new ExcelUtils(ExcelFile,"Sheet1");
        List<Map<String, String>> dataAsListOfMaps = excelUtils.getDataAsListOfMaps();

        LoginPage log = new LoginPage();
        for (int i = 1; i <= dataAsListOfMaps.size()+1; i++) {
            String cellEmail = excelUtils.getCellData(i, 0);
            String cellPass = excelUtils.getCellData(i, 1);
            log.LoginMethod(cellEmail, cellPass);

            try {
                if(log.text_notification.isDisplayed()){
                    System.out.println("Sign up with  " +cellEmail+  " Failed");
                    excelUtils.setCellData("Fail", "Status", i);

                 }

            } catch (Exception e) {
                excelUtils.setCellData("Pass", "Status", i);

            }
        }
        }




    @When("I login with the correct email {string} and invalid password {string} and click login button")
    public void iLoginWithTheCorrectEmailAndInvalidPasswordAndClickLoginButton(String email, String pass) {
        new LoginPage().LoginMethod(email, pass);
        System.out.println("your password is incorrect: " + new LoginPage().textLoginFailed.getText());
    }

    @When("I login with no email {string} and no password {string} and click login button")
    public void iLoginWithNoEmailAndNoPasswordAndClickLoginButton(String email, String pass) {
        new LoginPage().LoginMethod(email, pass);
        System.out.println(Driver.getDriver().getTitle());
    }


    @When("Login a user with {string}, {string}")
    public void loginAUserWith(String email, String password) {
        new LoginPage().LoginMethod(email, password);

    }



}





