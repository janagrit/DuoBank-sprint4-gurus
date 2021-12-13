package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ExcelUtils;
import utilities.SeleniumUtils;

import java.util.List;
import java.util.Map;

public class LoginPage {


    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//a[.='Sign up']")
    public WebElement signUp;

    @FindBy(xpath = "//div[@class='card-header pb-1']")
    public WebElement textRegistration;

    @FindBy(id = "exampleInputEmail1")
    public WebElement emailInput;

    @FindBy(id = "exampleInputPassword1")
    public WebElement passwordlInput;

    @FindBy(xpath = "//button[@type='submit']" )
    public WebElement loginButton;

    @FindBy(id="emailerror")
    public WebElement emailerror;

    @FindBy(xpath= "//div[@class='card-title']")
    public WebElement textLoginFailed;




    @FindBy(xpath = "//a[@class='dropdown-item']" )
    public WebElement logOut;

    @FindBy(xpath = "//h4[@class='text-center mb-2']" )
    public WebElement welcomeLoginText_Msg;


    @FindBy(name="first_name")
    public WebElement signUPname;

    @FindBy(name="last_name")
    public WebElement signUPsurname;

    @FindBy(name="email")
    public WebElement signUpEmail;

    @FindBy(name="password")
    public WebElement signUPass;

    @FindBy(xpath = "//div[@class='brand-logo']")
    public WebElement logoSign_DuoBank;



    @FindBy(xpath = "//button[@type='submit']")
    public WebElement registerButton;

    public void signUpMethod(String firstName, String lastName, String email, String password ){

        signUPname.sendKeys(firstName);
        signUPsurname.sendKeys(lastName);
        signUpEmail.sendKeys(email);
        signUPass.sendKeys(password);

        if(emailerror.isDisplayed()){
            System.out.println(email + " -> " + emailerror.getText());

        }else
            registerButton.click();
            System.out.println(email + " -> " + textRegistration.getText());
    }




    public void GurusLoginMethod()   {
        emailInput.sendKeys(ConfigReader.getProperty("email"));
        passwordlInput.sendKeys(ConfigReader.getProperty("password"));
        SeleniumUtils.jsClick(loginButton);
    }

    public void LoginMethod(String email, String password)   {
        emailInput.sendKeys(email);
        passwordlInput.sendKeys(password);
        loginButton.click();
//
//        if(welcomeLoginText_Msg.isDisplayed()){
//            System.out.println("Login Failed = user is not registered");
//        }else if(logoSign_DuoBank.isDisplayed()){
//            System.out.println("User with email "+email+ " login successfully");
//        }
    }



}
