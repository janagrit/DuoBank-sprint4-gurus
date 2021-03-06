package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    @FindBy(id="b_dob")
    public WebElement b_dob;

    @FindBy(id="b_ssn")
    public WebElement b_ssn;

    @FindBy(id="b_cell")
    public WebElement b_cell;

    @FindBy(id="select2-b_marital-container")
    public WebElement b_marital;


    @FindBy(xpath="//a[.=\"Next\"]")
    public WebElement buttonNext;

    @FindBy(xpath="//fieldset[@id ='steps-uid-0-p-2']//h6 [@class=\"py-50\"]")
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

    @FindBy(id="c_dob")
    public WebElement c_dob;

    @FindBy(id="c_ssn")
    public WebElement c_ssn;

    @FindBy(id="c_cell")
    public WebElement c_cell;

    @FindBy(id="select2-c_marital-container")
    public WebElement c_marital;

    @FindBy(id="b_cell-error")
    public WebElement errorMessage;

    @FindBy(xpath="//a[@href=\"#next\"]")
    public WebElement buttonnext;



    @FindBy(xpath="//input[@id='monthlyrentalpayment']")
    public WebElement monthlyrentalpayment;

    @FindBy(xpath="//input[@id='expense1']")
    public WebElement rentBox;


    @FindBy(xpath="//input[@id='co-employername1']")
    public WebElement coBorrowerEmpolyer;

    @FindBy(id="co-grossmonthlyincome")
    public WebElement coGrossMonthlyIncome;


    @FindBy(id="eConsentdeclarer")
    public WebElement eConsentDeclarer;

    @FindBy(id="eConsentdeclarerFirstName")
    public WebElement eConsentdeclarerFirstName;

    @FindBy(id="eConsentdeclarerLastName")
    public WebElement eConsentDeclarerLastName;

    @FindBy(id="eConsentdeclarerEmail")
    public WebElement eConsentDeclarerEmail;

    @FindBy(xpath = "//span[.='Application List']")
    public WebElement applicationList;

    @FindBy(xpath = "//td[.=\"??????  Cruz  \"]")
    public WebElement applicatInfo;


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


        Actions actions = new Actions(Driver.getDriver());

        actions.click(b_marital).sendKeys("Married"+Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER).perform();



        buttonNext.click();
    }

    public void Current_Monthly_Housing_Expenses() throws InterruptedException {

        SeleniumUtils.scroll(200,0);
        SeleniumUtils.jsClick(rentBox);
        Thread.sleep(400);
        monthlyrentalpayment.sendKeys("100");
        Thread.sleep(400);
        new ExpensesPage().buttonNext.click();
    }


    public  void Employment_and_Income(){

        new EmploymentAndIncomePage().employer.sendKeys("Bobo");
        new EmploymentAndIncomePage().grossMonthlyIncome.sendKeys("7000");

        SeleniumUtils.jsClick(new EmploymentAndIncomePage().buttonnext);

    }

    public void Credit_Report(){

        SeleniumUtils.jsClick(new CreditReportPage().yesButton);
        SeleniumUtils.jsClick(new CreditReportPage().buttonnext);
    }

    public void EconcentPage(){

        new eConcentPage().e_firstName.sendKeys("Bozen");
        new eConcentPage().e_lastName.sendKeys("Krzys");
        new eConcentPage().e_email.sendKeys("BK@gmail.com");
        SeleniumUtils.scroll(0,500);
        SeleniumUtils.jsClick(new eConcentPage().clickAgree);
        SeleniumUtils.jsClick(new eConcentPage().buttonNext);
    }

    public void eConsent(){

        eConsentDeclarer.sendKeys("Co-Borrower");
        eConsentdeclarerFirstName.sendKeys("Barabasz");
        eConsentDeclarerLastName.sendKeys("Mulate");
        eConsentDeclarerEmail.sendKeys("mulat@gmail.com");
        SeleniumUtils.scroll(0,500);
        SeleniumUtils.jsClick(new eConcentPage().clickAgree);
        SeleniumUtils.jsClick(new eConcentPage().buttonNext);
    }

    public void Summary(){

        SeleniumUtils.jsClick(new SummaryPage().clickSave);
    }

    public void coBorrower_Employment_Information(){

        coBorrowerEmpolyer.sendKeys("Apex");
        coGrossMonthlyIncome.sendKeys("800");

        SeleniumUtils.jsClick(new EmploymentAndIncomePage().buttonnext);

    }
}