package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

public class CreditReportPage {


    public CreditReportPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id="creditreport1")
    public WebElement clickYes;

    @FindBy(id="creditreport2")
    public WebElement clickNo;


    @FindBy(xpath="//a[@href='#next']")
    public WebElement buttonnext;


}
