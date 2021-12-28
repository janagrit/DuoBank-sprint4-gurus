package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.DBUtility;
import utilities.Driver;

import java.time.Duration;

public class Hooks {

//     Hook methods run before and after each scenario
    @Before
    public void setupScenario(){
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
        Driver.getDriver().manage().window().maximize();
    }


    @Before ("@db_Sprint_6")
    public void setupDb(){
        DBUtility.createConnection();
    }

    @After ("@db_Sprint_6")
    public void tearDownDb(){
        DBUtility.close();
    }

    @After
    public void tearDownScenario(Scenario scenario){

        if(scenario.isFailed()){

            byte[] screenshotAs = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotAs, "image/png", "failed_scenario_screenshot");
        }


        Driver.quitDriver();
    }

//    @After ("@db")
//    public void tearDownScenarioDB(){
//        System.out.println("Close the db connection");
//
//    }



}
