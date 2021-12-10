package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ExcelUtils;

import java.util.List;
import java.util.Map;


public class LoginStepDefs {


    @When("I am on login page I filling out email and password and click login button")
    public void iAmOnLoginPageIFillingOutEmailAndPasswordAndClickLoginButton() {
        new LoginPage().GurusLoginMethod();
        System.out.println(Driver.getDriver().getTitle());
        Assert.assertTrue(Driver.getDriver().getTitle().equals("Loan Application"));
    }


    @When("I am on login page using the given excel file")
    public void iAmOnLoginPageUsingTheGivenExcelFile() {

        ExcelUtils excelUtils = new ExcelUtils("SignUp_Data.xlsx", "Sheet1");
        List<Map<String, String>> dataAsListOfMaps = excelUtils.getDataAsListOfMaps();
        System.out.println(excelUtils);

        LoginPage login = new LoginPage();

        Throwable ex = null;

        for (int i = 0; i < dataAsListOfMaps.size(); i++) {
            Map<String, String> row = dataAsListOfMaps.get(i);
//
//            if(row.get("first_name").equalsIgnoreCase("y")){
//                homePage.clickOnProductLink(row.get("Product"));
//
//                try {
//                    Assert.assertEquals(row.get("Product"), productDetailsPage.productName.getText());
//                    Assert.assertEquals(row.get("Price"), productDetailsPage.price.getText());
//                    Assert.assertEquals(row.get("Model"), productDetailsPage.model.getText());
//                    Assert.assertEquals(row.get("Composition"), productDetailsPage.composition.getText());
//                    Assert.assertEquals(row.get("Styles"), productDetailsPage.style.getText());
//                    excelUtils.setCellData("PASS", "Status", i + 1);
//                }catch(Throwable e){
//                    ex = e;
//                    excelUtils.setCellData("FAIL", "Status", i + 1);
//                }
//
//                Driver.getDriver().navigate().back();
//            }else{
//                excelUtils.setCellData("SKIPPED", "Status", i + 1);
//            }
//
//
//        }
//
//        throw ex;

        } }


}


