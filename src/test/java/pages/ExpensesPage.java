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

    @FindBy (xpath = "//input[@value='Rent']")
    public WebElement checkBoxRent;

    @FindBy (css = "//input[@value='Rent']")
    public WebElement checkBoxR;

    // driver.findElement(By.cssSelector("input[type='checkbox']")).click();

    @FindBy (xpath = "// //input[@value='Own']")
    public WebElement checkBoxOwn;

    @FindBy (id = "expense2")
    public WebElement clickOwn;



    @FindBy(id="monthlyrentalpayment")
    public WebElement monthlyrentalpayment;

    @FindBy(xpath="//a[@href='#next']")
    public WebElement buttonNext;



    public void clickRent( ){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click()",checkBoxRent);
    }


    public void passExpensesPage(){
        clickRent();
        monthlyrentalpayment.sendKeys("2000");
        buttonNext.click();

    }



}
