package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import pages.LoginPage;
import utilities.Driver;
import utilities.ExcelUtils;

import java.util.List;
import java.util.Map;


public class LoginStepDefs {


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


    @When("I am on login page using the given excel file")
    public void iAmOnLoginPageUsingTheGivenExcelFile() throws InterruptedException {

        ExcelUtils excelUtils = new ExcelUtils("SignUp_Data.xlsx", "Sheet1");
        List<Map<String, String>> dataAsListOfMaps = excelUtils.getDataAsListOfMaps();
        System.out.println(dataAsListOfMaps);


        LoginPage login = new LoginPage();

        Throwable ex = null;

        for (int i = 0; i < dataAsListOfMaps.size(); i++) {
            Map<String, String> row = dataAsListOfMaps.get(i);

         //   login.LoginMethod(row.get("email"), row.get("name"));
            System.out.println(dataAsListOfMaps.get(i).hashCode());
      //  XSSFRow row=null;
       // XSSFCell cell=null;
//            String email=null;
//            String password=null;
//
//
//            for (int i=1; i < dataAsListOfMaps.size(); i++)
//            {
//                Map<String, String> row = dataAsListOfMaps.get(i);
//                System.out.println(row);
//
//                for ( int j = 0; j < row.size();j++)
//                {
//                    Map<String, String> cell = dataAsListOfMaps.get(j);
//
//                    if(j==0) // We can use Column Name as well, will see in upcoming sessions
//                    {
//                        email= cell.toString();
//                    }
//                    if(j==1) // We can use Column Name as well, will see in upcoming sessions
//                    {
//                        password = cell.toString();
//                    }
//                }
//
//                login.LoginMethod(email, password);
//
//                String result = null;
//                try
//                {
//                    Boolean isLoggedIn = login.logOut.isDisplayed();
//                    if(isLoggedIn==true)
//                    {
//                        result="PASS";
//                        // Writing to an excel
//
//
//                    }
//                    System.out.println("email : " + email + " ---- > " + "Password : "  + password + "-----> Login success ? ------> " + result);
//                    //System.out.println("Login successfull : " + isLoggedIn);
//                    login.logOut.click();
//                }
//                catch(Exception e)
//                {
//
//                    Boolean isError = login.LoginFailedMsg.isDisplayed();
//                    if(isError == true)
//                    {
//                        result="FAIL";
//
//                    }
//                    System.out.println("Email : " + email + " ---- > " + "Password : "  + password + "-----> Login success ? ------> " + result);
//                }
//                Thread.sleep(2000);
//                login.loginButton.click();
//            }


            // We can pass this values in to web application for testing Test user Accounts.
//            }
//
//            if(row.get("first_name").equalsIgnoreCase("y")){
//                login.clickOnProductLink(row.get("Product"));
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

        }}}





