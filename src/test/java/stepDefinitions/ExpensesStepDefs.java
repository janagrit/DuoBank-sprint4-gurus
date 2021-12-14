package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ExpensesPage;
import pages.PersonalInformationPage;
import utilities.Driver;

public class ExpensesStepDefs {

    ExpensesPage expensesPage = new ExpensesPage();

    @Then("I am on Personal Information Page fill in the basic information in order to move forward")
    public void iAmOnPersonalInformationPageFillInTheBasicInformationInOrderToMoveForward() {
        PersonalInformationPage personalPage = new PersonalInformationPage();
        personalPage.Personal_Info_method("Tom", "Sam", "tom@yahoo.com", 111-11-1111, 222-22-2222);
    }




    @When("I selecting the Rent checkbox")
    public void iSelectingTheRentCheckbox() {

        if (!expensesPage.checkBoxRent.isSelected()) {
            expensesPage.clickRent();
        }
    }

    @Then("I enter random digits in the Monthly Rental Payment field")
    public void iEnterRandomDigitsInTheMonthlyRentalPaymentField() {
        Faker faker = new Faker();
        expensesPage.monthlyRentalPayment.sendKeys(faker.number().digits(4));
        }


    @Then("I go to next page")
    public void iGoToNextPage() { expensesPage.buttonNext.click(); }



    @Then("I enter huge amount of digits in the Monthly Rental Payment field")
    public void iEnterHugeAmountOfDigitsInTheMonthlyRentalPaymentField() {
        Faker faker = new Faker();
        expensesPage.monthlyRentalPayment.sendKeys(faker.number().digits(12));
    }

    @When("I selecting the Own checkbox")
    public void iSelectingTheOwnCheckbox() {
        if (!expensesPage.checkBoxOwn.isSelected()) {
            expensesPage.clickOwn();
        }
    }

    @Then("I enter random digits in the First Mortagage Payment field")
    public void iEnterRandomDigitsInTheFirstMortagagePaymentField() {
        Faker faker = new Faker();
        expensesPage.firstMortgageTotalPayment.sendKeys(faker.number().digits(4));
    }

    @Then("I enter random, trillion value, digits in the First Mortgage Payment field")
    public void iEnterRandomTrillionValueDigitsInTheFirstMortgagePaymentField() {
        Faker faker = new Faker();
        expensesPage.firstMortgageTotalPayment.sendKeys(faker.number().digits(12));
    }
}
