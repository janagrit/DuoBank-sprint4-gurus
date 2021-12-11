package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.LoginPage;
import pages.PreapprovalDetailsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

public class PreapprovalDetailsStepDef {

    @Given("I am on the Main page")
    public void i_am_on_the_main_page() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));
        new LoginPage().GurusLoginMethod();
    }

    @When("I click on the Mortgage application link")
    public void i_click_on_the_mortgage_application_link(){

        SeleniumUtils.jsClick(new PreapprovalDetailsPage().mortgageLink);

    }

    @Then("I land on the mortgage application page")
    public void i_land_on_the_mortgage_application_page(){

        Assert.assertTrue(Driver.getDriver().getPageSource().contains("Application Wizard"));

    }

    @When("I click on the realtor check box")
    public void i_click_on_the_realtor_check_box(){

    new PreapprovalDetailsPage().realtorNo.click();
    }

    @Then ("Realtor box is checked")
    public  void realtor_box_is_checked(){

        new PreapprovalDetailsPage().realtorNo.isSelected();

    }

//    @When ("I enter realtor information")
//    public void i_enter_realtor_information(){
//
//        new PreapprovalDetailsPage().realtorInfo.sendKeys("Eva");
//    }
//
//    @Then ("Realtor info is displayed")
//    public void realtor_info_is_displayed() {
//
//        Assert.assertEquals("Eva", new PreapprovalDetailsPage().realtorInfo.getText());
//    }

    @When ("I click on the loan officer check box")
    public void i_click_on_the_loan_officer_check_box(){

        new PreapprovalDetailsPage().loanOfficerNo.click();
    }

    @Then ("Loan officer box is checked")
    public  void loan_officer_box_is_checked(){

        new PreapprovalDetailsPage().loanOfficerNo.isSelected();

    }

//    @When ("I choose the loan purpose")
//    public void i_choose_the_loan_purpose(){
//
//        new PreapprovalDetailsPage().purposeOfLoan.click();
//        new PreapprovalDetailsPage().purposeOfLoan.sendKeys(Keys.DOWN, Keys.ENTER);
//
//    }
//
//    @Then ("Loan purpose is displayed")
//    public  void loan_purpose_is_displayed(){
//
//        Assert.assertEquals("Purchase a Home", new PreapprovalDetailsPage().purposeOfLoan.getText());
//
//    }

    @When ("I enter estimated purchase {String}")
    public void i_enter_estimated_purchase_price(String price) throws InterruptedException {

        SeleniumUtils.scroll(0, 500);
        new PreapprovalDetailsPage().estimatedPrice.sendKeys(price);
        Thread.sleep(2000);
    }

    @Then ("Purchase {String} is displayed")
    public  void purchase_price_is_displayed(String price){

        Assert.assertEquals(price, new PreapprovalDetailsPage().estimatedPrice.getAttribute("value"));

    }

    @When ("I enter down payment amount")
    public void i_enter_down_payment_amount(){

        SeleniumUtils.scroll(0, 500);
        new PreapprovalDetailsPage().estimatedPrice.sendKeys("500000");
        new PreapprovalDetailsPage().downPaymentAmount.sendKeys("50000");
    }

    @Then ("Down payment amount is displayed")
    public  void down_payment_amount_is_displayed(){
        SoftAssertions softassertion = new SoftAssertions();
        softassertion.assertThat(new PreapprovalDetailsPage().downPaymentAmount.getAttribute("value")).isEqualTo("50000");
        softassertion.assertThat(new PreapprovalDetailsPage().downPaymentPercentage.getAttribute("value")).isEqualTo("10");
        softassertion.assertThat(new PreapprovalDetailsPage().loanAmount.getAttribute("value")).isEqualTo("450000");


    }
    @When ("I enter down payment percentage")
    public void i_enter_down_payment_percentage(){

        SeleniumUtils.scroll(0, 500);
        new PreapprovalDetailsPage().estimatedPrice.sendKeys("500000");
        new PreapprovalDetailsPage().downPaymentPercentage.clear();
        new PreapprovalDetailsPage().downPaymentPercentage.sendKeys("10");
    }

    @Then ("Down payment percentage is displayed")
    public  void down_payment_percentage_is_displayed(){

        //SoftAssertions softassertion = new SoftAssertions();
        Assert.assertEquals("10", new PreapprovalDetailsPage().downPaymentPercentage.getAttribute("value"));
        Assert.assertEquals("50000", new PreapprovalDetailsPage().downPaymentAmount.getAttribute("value"));
        Assert.assertEquals("450000", new PreapprovalDetailsPage().loanAmount.getAttribute("value"));

    }

    @When ("I choose the source of down payment")
    public void i_choose_the_source_of_down_payment(){

        SeleniumUtils.scroll(0, 500);
        new PreapprovalDetailsPage().sourceOfDownPayment.click();
        new PreapprovalDetailsPage().checkingSavings.click();

    }

    @Then ("Source of down payment is displayed")
    public  void source_of_down_payment_is_displayed(){

        Assert.assertEquals("Checking/Savings(most recent bank statement)", new PreapprovalDetailsPage().sourceOfDownPayment.getText());

    }

}
