package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

public class EmploymentAndIncomePage {


    public EmploymentAndIncomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "employername1")
    public WebElement employer;

    @FindBy(id = "position1")
    public WebElement position;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "state1")
    public WebElement state;

    @FindBy(id = " start_date1")
    public WebElement startDate;

    @FindBy(id = "end_date1")
    public WebElement endDate;

    @FindBy(id = "addemployer")
    public WebElement addEmployer;

    @FindBy(id = "grossmonthlyincome")
    public WebElement grossMonthlyIncome;

    @FindBy(id = "monthlyovertime")
    public WebElement monthlyOverTime;

    @FindBy(id = "monthlybonuses")
    public WebElement monthlyBonuses;

    @FindBy(id = "monthlycommission")
    public WebElement monthlyCommission;

    @FindBy(id = "monthlydividents")
    public WebElement monthlyDividents;

    @FindBy(xpath = "//div[@class='borrowertotalmonthlyincome']")
    public WebElement totalMonthlyIncome;

    @FindBy(id = "incomesource1")
    public WebElement incomesource1;

    @FindBy(id = "incomesource2")
    public WebElement incomesource2;

    @FindBy(linkText = "Employment and Income")
    public WebElement Employment_and_Income;

    @FindBy(id = "incomesource3")
    public WebElement incomesource3;


    @FindBy(id = "amount1")
    public WebElement amount1;

    @FindBy(id = "amount2")
    public WebElement amount2;


    public void getToEmploymentAndIncome() {
        PreapprovalDetailsPage preapproval_details_page = new PreapprovalDetailsPage();
        PersonalInformationPage personalInformationPage = new PersonalInformationPage();
        ExpensesPage expensesPage = new ExpensesPage();
        preapproval_details_page.setPreapproval_method("Mark", 500000, 100000);
        personalInformationPage.Personal_Info_method("Mira", "Mar", "miramar@gmail.com", 123456789, 897543443);
        expensesPage.passExpensesPage();

    }

    public void getToCreditReport() {
        getToEmploymentAndIncome();
        employer.sendKeys("White House");
        grossMonthlyIncome.sendKeys("1");


    }
}