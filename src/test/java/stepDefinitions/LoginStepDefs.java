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


    @When("I am on login page using the given excel file")
    public void iAmOnLoginPageUsingTheGivenExcelFile() throws InterruptedException, IOException {

        LoginPage log = new LoginPage();

        FileInputStream fis = new FileInputStream("SignUp_Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        XSSFRow row = sheet.getRow(1);  // Apache POI method indexes are zero-based
        XSSFCell cell = row.getCell(0);
        System.out.println("cell " + cell);

        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("physicalNumberOfRows " + physicalNumberOfRows);
        System.out.println("lastRowNum" + lastRowNum);
        XSSFRow columnRow = sheet.getRow(0);
        int physicalNumberOfCells = columnRow.getPhysicalNumberOfCells();
        System.out.println("physicalNumberOfCells " + physicalNumberOfCells);

//        for (int i = 0; i < physicalNumberOfRows; i++) {
//            for (int j = 0; j < physicalNumberOfCells; j++) {
//                System.out.print(sheet.getRow(i).getCell(j) + "\t");
//            }  System.out.println();
//        }

        // grab the cell that need to be
     //   XSSFCell cellStatus = sheet.getRow(1).getCell(2);
       // cellStatus.setCellValue("Pass");

//        FileOutputStream fos = new FileOutputStream("SignUp_Data.xlsx");
//        workbook.write(fos);

        // in order to use xml file we are creating the class ExcelUtils

        ExcelUtils excelUtils = new ExcelUtils("SignUp_Data.xlsx", "Sheet1");

      //  System.out.println(excelUtils.getCellData(2, 1));


        String[][] dataAs2DArray = excelUtils.getDataAs2DArray();

      //  System.out.println(Arrays.deepToString(dataAs2DArray));

        List<Map<String, String>> dataAsListOfMaps = excelUtils.getDataAsListOfMaps();

        for (Map<String, String> dataAsListOfMap : dataAsListOfMaps) {
            System.out.println(dataAsListOfMap);
        }

        while(excelUtils.rowCount() < excelUtils.rowCount()+1){
        String email = excelUtils.getCellData(1,1);
            log.LoginMethod(email, );
            excelUtils.setCellData("test", "Status", 1);

        for (int i = 0; i < physicalNumberOfRows; i++) {
            for (int j = 0; j < physicalNumberOfCells; j++) {
                System.out.print(sheet.getRow(i).getCell(j) + "\t");
                excelUtils.getCellData(i, j);
            }  System.out.println();
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
}





