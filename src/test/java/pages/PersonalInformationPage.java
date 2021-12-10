package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.SeleniumUtils;

public class PersonalInformationPage {

    public  PersonalInformationPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id="b_firstName")
    public WebElement b_firstName;

    @FindBy(id="b_lastName")
    public WebElement b_lastName;

    @FindBy(id="b_email")
    public WebElement b_email;

    @FindBy(id="b_ssn")
    public WebElement b_ssn;

    @FindBy(id="b_cell")
    public WebElement b_cell;

    @FindBy(id="select2-b_marital-container")
    public WebElement b_marital;

    @FindBy(xpath=" //a[@href='#next'] ")
    public WebElement buttonnext;

    public void Personal_Info_method(String name, String last, String email, int soc, int phone){

        b_firstName.sendKeys(name);
        b_lastName.sendKeys(last);
        b_email.sendKeys(email);
        b_ssn.sendKeys(Integer.toString(soc));
        b_cell.sendKeys(Integer.toString(phone));
        SeleniumUtils.jsClick(b_marital);

      //  new Actions((WebDriver) b_marital).sendKeys("married").perform();
        buttonnext.click();
    }





}
