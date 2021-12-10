package pages;

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

    @FindBy (xpath = "// //input[@value='Rent']")
    public WebElement checkBoxRent;

    @FindBy (xpath = "// //input[@value='Own']")
    public WebElement checkBoxOwn;

    @FindBy(id="monthlyrentalpayment")
    public WebElement monthlyrentalpayment;



}
