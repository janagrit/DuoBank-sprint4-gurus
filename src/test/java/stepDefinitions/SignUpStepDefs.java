package stepDefinitions;

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

    @Given("on login page")
    public void onLoginPage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }

    @Then("I click on sign up page")
    public void iClickOnSignUpPage() {
        new LoginPage().signUp.click();
    }


    @When("Register a new user: {string}, {string}, {string}, {string} click sign up button")
    public void registerANewUserClickSignUpButton(String name, String lastName, String email, String password) throws IOException {

        LoginPage log = new LoginPage();
        log.signUPname.sendKeys(name);
        log.signUPsurname.sendKeys(lastName);
        log.signUpEmail.sendKeys(email);
        log.signUPass.sendKeys(password);
       // log.signUpMethod(name, lastName, email, password);
    }


    @Then("I verify msg {string} or {string}")
    public void iVerifyMsgOr(String confmsg, String errormsg) {

        SoftAssertions softAssert = new SoftAssertions();
        if (new LoginPage().emailerror.isDisplayed()) {
            softAssert.assertThat(errormsg).isEqualTo(new LoginPage().emailerror.getText());
        } else
            softAssert.assertThat(confmsg).isEqualTo(new LoginPage().textRegistration.getText());
    }


//    @When("I register a new user using data from the Excel file {string}")
//    public void iRegisterANewUserUsingDataFromTheExcelFile(String file) {
//
//        ExcelUtils excelUtils = new ExcelUtils(file, "Sheet2");
//        List<Map<String, String>> dataAsListOfMaps = excelUtils.getDataAsListOfMaps();
//
//        LoginPage log = new LoginPage();
//        for (int i = 1; i <= dataAsListOfMaps.size(); i++) {
//
//            String cellName = excelUtils.getCellData(i, 0);
//            String cellLastName = excelUtils.getCellData(i, 1);
//            String cellEmail = excelUtils.getCellData(i, 2);
//            String cellPass = excelUtils.getCellData(i, 3);
//
//            log.signUpMethod(cellName, cellLastName, cellEmail, cellPass);
//
//            try {
//                if (log.text_notification.isDisplayed()) {
//                    System.out.println("Sign Up with  " + cellEmail + " Failed");
//                    excelUtils.setCellData("Fail", "Status", i);
//                }
//
//            } catch (Exception e) {
//                excelUtils.setCellData("Pass", "Status", i);
//
//            }
//        }
//    }
}
