package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ExcelUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SignUpStepDefs {

    LoginPage signuppage = new LoginPage();

    @Given("on login page")
    public void onLoginPage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }

    @Then("I click on sign up page")
    public void iClickOnSignUpPage() {
        signuppage.signUp.click();
    }


    @When("Register a new user: {string}, {string}, {string}, {string} click sign up button")
    public void registerANewUserClickSignUpButton(String name, String lastName, String email, String password) throws IOException {


        signuppage.signUpMethod(name, lastName, email, password);
        signuppage.registerButton.click();

    }


    @Then("The msg: {string} should appear on the sign up page")
    public void theMsgShouldAppearOnTheSignUpPage(String emailIsAlreadyUsed)  {

        Assert.assertTrue(signuppage.emailUsedError.isDisplayed());
        Assert.assertTrue(emailIsAlreadyUsed, equals(signuppage.emailUsedError.getText()));
        System.out.println(signuppage.emailUsedError.getText());

    }


    @When("I register a new user with Faker class")
    public void iRegisterANewUserWithFakerClass() {

        Faker fake = new Faker();
        signuppage.signUpMethod (

                fake.name().firstName(),
                fake.name().lastName(),
                fake.internet().emailAddress(),
                fake.internet().password()      );

        signuppage.registerButton.click();

    }

    @Then("The msg: {string} should appear and user is redirected on Login page")
    public void theMsgShouldAppearAndUserIsRedirectedOnLoginPage(String msg) {
        LoginPage signUpPage = new LoginPage();

        Assert.assertTrue(signUpPage.textRegistrationSuccessfull.isDisplayed());
        Assert.assertTrue(signUpPage.welcomeLoginWithEmail.isDisplayed());
        System.out.println(signUpPage.welcomeLoginWithEmail.getText());
    }



    @When("I register a new user using data from the Excel file {string}")
    public void iRegisterANewUserUsingDataFromTheExcelFile(String file) throws InterruptedException {

        ExcelUtils excelUtils = new ExcelUtils(file, "Sheet2");
        List<Map<String, String>> dataAsListOfMaps = excelUtils.getDataAsListOfMaps();
        Thread.sleep(2000);


        for (int i = 1; i <= dataAsListOfMaps.size(); i++) {

            String cellName = excelUtils.getCellData(i, 0);
            String cellLastName = excelUtils.getCellData(i, 1);
            String cellEmail = excelUtils.getCellData(i, 2);
            String cellPass = excelUtils.getCellData(i, 3);

            signuppage.signUpMethod(cellName, cellLastName, cellEmail, cellPass);
            signuppage.registerButton.click();

            try {
                if (signuppage.emailUsedError.isDisplayed()) {
                    System.out.println("This email  " + cellEmail + " is already used");
                    excelUtils.setCellData("Fail", "Status", i);
                    Driver.getDriver().navigate().refresh();
                }

            } catch (Exception e) {
                System.out.println("This email  " + cellEmail + " is Passing");
                excelUtils.setCellData("Pass", "Status", i);
                Driver.getDriver().navigate().back();
                Driver.getDriver().navigate().refresh();
            }

        }
    }



}
