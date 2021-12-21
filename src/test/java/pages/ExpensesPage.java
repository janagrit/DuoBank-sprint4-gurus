package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ExpensesPage {

    public ExpensesPage(){
        PageFactory.initElements(Driver.getDriver(), this);

    }

    @FindBy (xpath = "//h6[.='Current Monthly Housing Expenses']")
    public WebElement textonthepage;

    @FindBy (xpath = "//*[@for='expense1']")
    public WebElement checkBoxRent;

    @FindBy(xpath = "//*[@for='expense2']")
    public WebElement checkBoxOwn;

    @FindBy (css = "//input[@value='Rent']")
    public WebElement checkBoxR;

    // driver.findElement(By.cssSelector("input[type='checkbox']")).click();
    @FindBy(xpath = "//input[@id='firtmortagagetotalpayment']")
    public WebElement firstMortgageTotalPayment;

    @FindBy(xpath="//input[@name='monthly_rental_payment']")
    public WebElement monthlyRentalPayment;


    @FindBy(xpath="//a[@href='#next']")
    public WebElement buttonNext;


    public void clickRent( ){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click()",checkBoxRent);
    }

    public void clickOwn( ){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click()",checkBoxOwn);
    }


    public void passExpensesPage(){
        clickRent();
        monthlyRentalPayment.sendKeys("2000");
        buttonNext.click();

    }



}
