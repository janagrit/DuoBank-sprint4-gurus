package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;

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



    public void setSignUp(String firstName, String lastName, String email, String password ){

        Driver.getDriver().findElement(By.name("first_name")).sendKeys(firstName);
        Driver.getDriver().findElement(By.name("last_name")).sendKeys(lastName);
        Driver.getDriver().findElement(By.name("email")).sendKeys(email);
        Driver.getDriver().findElement(By.name("password")).sendKeys(password);
        Driver.getDriver().findElement(By.name("register")).click();

        // String.valueOf(
    }

    public void setLoginButton(String email, String password ) throws InterruptedException {


        Driver.getDriver().findElement(By.id("exampleInputEmail1")).sendKeys(email);
        Driver.getDriver().findElement(By.id("exampleInputPassword1")).sendKeys(password);
        Thread.sleep(2000);
        Driver.getDriver().findElement(By.name("login")).click();


    }



}
