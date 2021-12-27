package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.time.Duration;

public class LoginPage {


    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//a[.='Sign up']")
    public WebElement signUp;

    @FindBy(xpath = "//div[@class='card-header pb-1']")
    public WebElement textRegistrationSuccessfull;

    @FindBy(id = "exampleInputEmail1")
    public WebElement emailInput;



    @FindBy(id = "exampleInputPassword1")
    public WebElement passwordlInput;

    @FindBy(xpath = "//button[@type='submit']" )
    public WebElement loginButton;

    @FindBy(xpath = "//span[.='This email already used']")
    public WebElement emailUsedError;

    @FindBy(xpath = " //div[@class='divider-text text-uppercase text-muted']")
    public WebElement welcomeLoginWithEmail;

    @FindBy(xpath= "//div[@class='card-title']")
    public WebElement textLoginFailed;



    @FindBy(xpath = "//span[@class='user-name']" )
    public WebElement user_name_OnUI;

    @FindBy(xpath = "//h4[@class='text-center mb-2']" )
    public WebElement text_notification;


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
    }




    public void GurusLoginMethod()   {
        emailInput.sendKeys(ConfigReader.getProperty("email"));
        passwordlInput.sendKeys(ConfigReader.getProperty("password"));
        SeleniumUtils.jsClick(loginButton);
    }

    public void LoginMethod(String email, String password)   {
        emailInput.sendKeys(email);
        passwordlInput.sendKeys(password);

        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}