package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class EconcentPage {

    public EconcentPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "eConsentdeclarerFirstName")
    public WebElement e_firstName;

    @FindBy(id = "eConsentdeclarerLastName")
    public WebElement e_lastName;

    @FindBy(id = "eConsentdeclarerEmail")
    public WebElement e_email;

    @FindBy(xpath = "//input[@id='agree']")
    public WebElement clickAgree;


    @FindBy(xpath = " //a[@href='#next'] ")
    public WebElement buttonNext;


}