package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PersonalInformationPage;
import utilities.Driver;

public class PersonalInfoStepDefs {




    @Then("I am on the personal information page put name {string}, surname {string}, email {string}, social {int}, phone {int}")
    public void iAmOnThePersonalInformationPagePutNameSurnameEmailSocialPhone(String arg0, String arg1, String arg2, int arg3, int arg4) throws InterruptedException {
        new PersonalInformationPage().Personal_Info_method(arg0, arg1, arg2, arg3, arg4);
        Thread.sleep(4000);
        System.out.println(Driver.getDriver().getTitle());
       // Assert.
    }
}
