package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.PreapprovalDetailsPage;
import utilities.ConfigReader;
import utilities.Driver;

public class PreapprovalDetailsStepDef {

    @Given("I am on the Main page")
    public void i_am_on_the_main_page() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }

    @When("I click on the Mortgage application link")
    public void i_click_on_the_mortgage_application_link(){

    }

    @Then("I land on the Mortgage application page")
    public void i_land_on_the_mortgage_application_page(){

    }

    @When("I click on the realtor check box")
    public void i_click_on_the_realtor_check_box(){

    new PreapprovalDetailsPage().realtorYes.click();
    }

    @Then ("Realtor box is checked")
    public  void realtor_box_is_checked(){

        new PreapprovalDetailsPage().realtorYes.isSelected();

    }

    @When ("I enter realtor information")
    public void i_enter_realtor_information(){

        new PreapprovalDetailsPage().realtorInfo.sendKeys("Eva");
    }

    @Then ("Realtor info is displayed")
    public void realtor_info_is_displayed() {

        Assert.assertEquals("Eva", new PreapprovalDetailsPage().realtorInfo.getText());
    }

    @When ("I click on the loan officer check box")
    public void i_click_on_the_loan_officer_check_box(){

        new PreapprovalDetailsPage().realtorYes.click();
    }

    @Then ("Loan officer box is checked")
    public  void loan_officer_box_is_checked(){

        new PreapprovalDetailsPage().loanOfficerYes.isSelected();

    }

    @When ("I choose the loan purpose")
    public void i_choose_the_loan_purpose(){

        new PreapprovalDetailsPage().purposeOfLoan.click();
        new PreapprovalDetailsPage().purposeOfLoan.sendKeys(Keys.DOWN, Keys.ENTER);

    }

    @Then ("Loan purpose is displayed")
    public  void loan_purpose_is_displayed(){

        Assert.assertEquals("Purchase a Home", new PreapprovalDetailsPage().purposeOfLoan.getText());

    }

    @When ("I enter estimated purchase price")
    public void i_enter_estimated_purchase_price(){

        new PreapprovalDetailsPage().estimatedPrice.sendKeys("500000");
    }

    @Then ("Purchase price is displayed")
    public  void purchase_price_is_displayed(){

        Assert.assertEquals("500000", new PreapprovalDetailsPage().estimatedPrice.getText());

    }

    @When ("I enter down payment amount")
    public void i_enter_down_payment_amount(){

        new PreapprovalDetailsPage().downPaymentAmount.sendKeys("50000");
    }

    @Then ("Down payment amount is displayed")
    public  void down_payment_amount_is_displayed(){

        Assert.assertEquals("50000", new PreapprovalDetailsPage().downPaymentAmount.getText());

    }
    @When ("I enter down payment percentage")
    public void i_enter_down_payment_percentage(){

        new PreapprovalDetailsPage().downPaymentPercentage.sendKeys("10");
    }

    @Then ("Down payment percentage is displayed")
    public  void down_payment_percentage_is_displayed(){

        Assert.assertEquals("10", new PreapprovalDetailsPage().downPaymentPercentage.getText());

    }

    @When ("I choose the source of down payment")
    public void i_choose_the_source_of_down_payment(){

        new PreapprovalDetailsPage().sourceOfDownPayment.click();
        new PreapprovalDetailsPage().sourceOfDownPayment.sendKeys(Keys.DOWN, Keys.ENTER);

    }

    @Then ("Source of down payment is displayed")
    public  void source_of_down_payment_is_displayed(){

        Assert.assertEquals("Equity on Pending Sale(executed sales contract)", new PreapprovalDetailsPage().sourceOfDownPayment.getText());

    }

}
