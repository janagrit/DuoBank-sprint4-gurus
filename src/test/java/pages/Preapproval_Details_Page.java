package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class Preapproval_Details_Page {


        public  Preapproval_Details_Page() {
            PageFactory.initElements(Driver.getDriver(), this);}

        @FindBy(xpath = "//span[@data-i18n='eCommerce']")
        public WebElement Mortgage_link;

        @FindBy(id="realtorinfo")
        public WebElement realtorinfo;

        @FindBy(id="estimatedprice")
        public WebElement estimatedprice;

    @FindBy(id="downpayment")
    public WebElement downpayment;

//    @FindBy(id="downpaymentpercentage")
//    public WebElement downpaymentpercentage;

    @FindBy(xpath=" //a[@href='#next'] ")
    public WebElement buttonnext;

    public void setPreapproval_method(String realtor, int num, int dpayment){
        realtorinfo.sendKeys(realtor);
        estimatedprice.sendKeys(Integer.toString(num));
        downpayment.sendKeys(Integer.toString(dpayment));
        buttonnext.click();
    }








}
