package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    @FindBy(id="select2-src_down_payment-x9-container")
    public WebElement sourceOfDownPayment;

    @FindBy(id="select2-src_down_payment-x9-result-tq80-Checking/Savings (most recent bank statement)")
    public WebElement checkingSavings;

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


}
