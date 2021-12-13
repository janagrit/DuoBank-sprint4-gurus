package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.util.List;

public class PersonalInformationPage {

    public  PersonalInformationPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//fieldset[@id='steps-uid-0-p-1']//h6[.='Personal Information']")
    public WebElement title;

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


    @FindBy(xpath="//a[@href=\"#next\"]")
    public WebElement buttonnext;

    @FindBy(xpath="//fieldset[@id='steps-uid-0-p-2']//h6")
    public WebElement expensesTitle;

    //@FindBy(xpath="//input[@id=\\\"coborrower1\\\"]")
    @FindBy(xpath="//label [@for='coborrower1']")
    public WebElement yesCheckBox;

    @FindBy(xpath="//ul[@role=\"tablist\"]//li")
    public List<WebElement> stepsApp;

    @FindBy(xpath="//div[@class='co-borrower']//h6[@class='py-50']")
    public WebElement coBorrowerInfo;

    @FindBy(id="c_firstName")
    public WebElement c_firstName;

    @FindBy(id="c_lastName")
    public WebElement c_lastName;

    @FindBy(id="c_email")
    public WebElement c_email;

    @FindBy(id="c_ssn")
    public WebElement c_ssn;

    @FindBy(id="c_cell")
    public WebElement c_cell;

    @FindBy(id="select2-c_marital-container")
    public WebElement c_marital;

    @FindBy(id="b_cell-error")
    public WebElement errorMessage;



//    public void clickOnProductLink(String product){
//        String xpath = "//input[@id=\"coborrower1\"]";
//        Driver.getDriver().findElement(By.xpath(xpath)).click();
//}

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
