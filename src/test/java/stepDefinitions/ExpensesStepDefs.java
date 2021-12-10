package stepDefinitions;

import io.cucumber.java.en.Then;
import pages.ExpensesPage;

public class ExpensesStepDefs {

    @Then("user put {int} as a monthly payment and click next button")
    public void userPutAsAMonthlyPaymentAndClickNextButton(int pay) {

        System.out.println(new ExpensesPage().checkBoxRent.isDisplayed());
        new ExpensesPage().monthlyrentalpayment.sendKeys(Integer.toString(pay));
        new ExpensesPage().buttonNext.click();

    }

}
