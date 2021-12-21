package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CreditReportPage {
    public CreditReportPage() {PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath="//input[@id='creditreport1']")
    public WebElement yesButton;


    @FindBy(xpath="//a[@href=\"#next\"]")
    public WebElement buttonnext;
}
