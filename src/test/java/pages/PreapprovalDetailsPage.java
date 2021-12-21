package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;

public class PreapprovalDetailsPage {

    public PreapprovalDetailsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[@data-i18n='eCommerce']")
    public WebElement mortgageLink;

    @FindBy(id="steps-uid-0-t-0")
    public WebElement preapprovalDetails;

    @FindBy(id="realtor1")
    public WebElement realtorYes;

    @FindBy(id="realtor2")
    public WebElement realtorNo;

    @FindBy(id="loanofficer1")
    public WebElement loanOfficerYes;

    @FindBy(id="loanofficer2")
    public WebElement loanOfficerNo;

    @FindBy(id="realtorinfo")
    public WebElement realtorInfo;

    @FindBy(xpath = "//span[@title='Purchase a Home']")
    public WebElement purposeOfLoan;

    @FindBy(id="estimatedprice")
    public WebElement estimatedPrice;

    @FindBy(id="downpayment")
    public WebElement downPaymentAmount;

    @FindBy(id="downpaymentpercentage")
    public WebElement downPaymentPercentage;

    @FindBy(id="loanamount")
    public WebElement loanAmount;

    @FindBy(name="src_down_payment")
    public WebElement sourceOfDownPayment;

    @FindBy(id="select2-src_down_payment-r2-result-tosd-Other type of Down Payment")
    public WebElement otherTypeOfDownPayment;


    @FindBy(id="additionalfunds")
    public WebElement additionalFunds;

    @FindBy(xpath=" //a[@href='#next'] ")
    public WebElement buttonNext;

    public void setPreapproval_method(String realtor, int num, int dpayment){
        realtorInfo.sendKeys(realtor);
        estimatedPrice.sendKeys(Integer.toString(num));
        downPaymentAmount.sendKeys(Integer.toString(dpayment));
        buttonNext.click();
    }

    public void realtorCheckBox(){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click()",realtorNo);

    }

    public void loanOfficerCheckBox(){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click()",loanOfficerNo);

    }

    public void sourceOfDownPaymentDropdown(){

        Select select = new Select(sourceOfDownPayment);

        select.selectByIndex(2);

    }


}
