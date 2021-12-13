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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SignUpStepDefs {

    @Given("on login page")
    public void onLoginPage() {   Driver.getDriver().get(ConfigReader.getProperty("url"));   }

    @Then("I click on sign up page")
    public void iClickOnSignUpPage() {
        new LoginPage().signUp.click();
    }



    @When("Register a new user: {string}, {string}, {string}, {string} click sign up button")
    public void registerANewUserClickSignUpButton(String name, String lastName, String email, String password) throws IOException {

        LoginPage log =  new LoginPage();
        log.signUpMethod(name, lastName, email, password);
    }


    @Then("I verify msg {string} or {string}")
    public void iVerifyMsgOr(String confmsg, String errormsg) {

        SoftAssertions softAssert = new SoftAssertions();
        if(new LoginPage().emailerror.isDisplayed()){
            softAssert.assertThat(errormsg).isEqualTo(new LoginPage().emailerror.getText());
        }else
        softAssert.assertThat(confmsg).isEqualTo(new LoginPage().textRegistration.getText());

    }

    @Then("The new user data should be stored in database file")
    public void theNewUserDataShouldBeStoredInDatabaseFile() throws IOException {


        FileInputStream fis = new FileInputStream("SignUp_Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet3");
        XSSFRow row = sheet.getRow(1);  // Apache POI method indexes are zero-based
        XSSFCell cell = row.getCell(0);
       // System.out.println("cell " + cell);

        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        int lastRowNum = sheet.getLastRowNum();

      //  System.out.println("physicalNumberOfRows " + physicalNumberOfRows);
      //  System.out.println("lastRowNum" + lastRowNum);


        XSSFRow columnRow = sheet.getRow(0);
        int physicalNumberOfCells = columnRow.getPhysicalNumberOfCells();

       //System.out.println("physicalNumberOfCells " + physicalNumberOfCells);


        for (int i = 0; i < physicalNumberOfRows; i++) {

            for (int j = 0; j < physicalNumberOfCells; j++) {
                System.out.print(sheet.getRow(i).getCell(j) + "\t");
            }
            System.out.println();

        }

        // grab the cell that need to be stored


        XSSFCell cellStatus = sheet.getRow(0).getCell(0);
        cellStatus.setCellValue("Email");

        FileOutputStream fos = new FileOutputStream("SignUp_Data.xlsx");
        workbook.write(fos);
    }


}
