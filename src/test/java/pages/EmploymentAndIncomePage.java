package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

public class EmploymentAndIncomePage {


    public EmploymentAndIncomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }






    @FindBy(linkText = "Employment and Income")
    public WebElement Employment_and_Income;

    @FindBy(xpath = "//div[@class='card-header pb-1']")
    public WebElement textRegistration;

    @FindBy(id = "exampleInputEmail1")
    public WebElement emailInput;

    @FindBy(id = "exampleInputPassword1")
    public WebElement passwordlInput;

    @FindBy(xpath = "//button[@type='submit']" )
    public WebElement login;



public void getToEmploymentAndIncome(){
   Preapproval_Details_Page preapproval_details_page=new Preapproval_Details_Page();
    PersonalInformationPage personalInformationPage=new PersonalInformationPage();
    ExpensesPage expensesPage=new ExpensesPage();
    preapproval_details_page.setPreapproval_method("Mark",500000,100000);
    personalInformationPage.Personal_Info_method("Mira","Mar","miramar@gmail.com",123456789,897543443);
    expensesPage.passExpensesPage();

    }




    public void setSignUp(String firstName, String lastName, String email, String password ){

        Driver.getDriver().findElement(By.name("first_name")).sendKeys(firstName);
        Driver.getDriver().findElement(By.name("last_name")).sendKeys(lastName);
        Driver.getDriver().findElement(By.name("email")).sendKeys(email);
        Driver.getDriver().findElement(By.name("password")).sendKeys(password);
        Driver.getDriver().findElement(By.name("register")).click();

        // String.valueOf(
    }

    public void GurusLoginMethod()   {
        emailInput.sendKeys(ConfigReader.getProperty("email"));
        passwordlInput.sendKeys(ConfigReader.getProperty("password"));
        SeleniumUtils.jsClick(login);
    }

    public void LoginMethod(String email, String password)   {
        emailInput.sendKeys(email);
        passwordlInput.sendKeys(password);
        login.click();
    }



}
