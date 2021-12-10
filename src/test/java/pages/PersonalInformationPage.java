package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
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
       //SeleniumUtils.jsClick(b_marital);
     // b_marital.click();
     // b_marital.sendKeys(Keys.DOWN,Keys.DOWN,Keys.ENTER);
        //new Actions((WebDriver) b_marital).sendKeys("married").perform();

        Actions actions = new Actions(Driver.getDriver());

        actions.click(b_marital).sendKeys("Married"+Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER).perform();



        buttonnext.click();
    }





}
