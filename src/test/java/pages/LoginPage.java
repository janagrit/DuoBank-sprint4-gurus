package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

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


    @FindBy(xpath = "//div[@class='user-nav d-sm-flex d-none']" )
    public WebElement logInIcon;

    @FindBy(xpath = "//a[@class='dropdown-item']" )
    public WebElement logOut;

    @FindBy(xpath = "// //div[@class='card-title']" )
    public WebElement LoginFailedMsg;


    @FindBy(name="first_name")
    public WebElement signUPname;

    @FindBy(name="last_name")
    public WebElement signUPsurname;

    @FindBy(name="email")
    public WebElement signUpEmail;

    @FindBy(name="password")
    public WebElement signUPass;



    public void signUpMethod(String firstName, String lastName, String email, String password ){

        Driver.getDriver().findElement(By.name("first_name")).sendKeys(firstName);
        Driver.getDriver().findElement(By.name("last_name")).sendKeys(lastName);
        Driver.getDriver().findElement(By.name("email")).sendKeys(email);
        Driver.getDriver().findElement(By.name("password")).sendKeys(password);

        if(emailerror.isDisplayed()){
            System.out.println(email + " -> " + emailerror.getText());

        }else{
            Driver.getDriver().findElement(By.name("register")).click();
            System.out.println(email + " -> " + textRegistration.getText());

        }


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
    }



}
