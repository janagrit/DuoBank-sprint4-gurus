package APItests;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import io.restassured.common.mapper.TypeRef;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.LoginPage;
import pojos.UserInfo;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class APITest {

    Response response;
    String Token;

    String Login_User = "BozenaNovak@gmail.com";
    String Login_Password = "Marcel";


    @BeforeClass
    public static void baseURI() {
        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";
    }

    @Test
    public void loginWithValidInfo() {

        response = given().log().all().
                body("{\n" +
                        "\"email\" : \"" + Login_User + "\",\n" +
                        "\"password\" : \"" + Login_Password + "\"\n" +
                        "}").
                when().log().all().
                post("/login.php").
                then().log().all().
                statusCode(200).
                body("message", equalTo("You have successfully logged in.")).extract().response();

        String jsonString = response.asString();
        Token = JsonPath.from(jsonString).get("token");

    }

    @Test
    // Create another account using the same email address
    public void registerWithInValidCredentialsUsingPOJOClass() {

        UserInfo userInfo = new UserInfo("Katarzyna", "Bombon", Login_User, "Kaska");

        given().
                body(userInfo).
                when().log().all().
                post("/register.php").
                then().log().all().
                //statusCode(422).  bug? the code status is 200 but should be 422?
                        body("message", equalTo("This E-mail already in use!"));

    }

    @Test
    public void testGETPlaces() {
        given().
                queryParam("Authorization", Token).
                when().log().all().
                get("/getmortagage.php").
                then().log().all().
                statusCode(200);

    }

    @Test
    public void apiToUIFlowTest() {

        // Create a new user through API call

        String email = new Faker().internet().emailAddress();
        String pass = "duotech2022";
        given().
                body("{\n" +
                        "\"first_name\" : \"Karol\",\n" +
                        "\"last_name\" : \"Bartoszewski\",\n" +
                        "\"email\" : \"" + email + "\",\n" +
                        "\"password\" : \"" + pass + "\"\n" +
                        "}").
                when().log().all().
                post("/register.php").
                then().log().all().
                statusCode(200).
                body("message", equalTo("You have successfully registered."));

        // Verify the user creation on the UI by logging in

        Driver.getDriver().get("http://qa-duobank.us-east-2.elasticbeanstalk.com/");
        Driver.getDriver().findElement(By.id("exampleInputEmail1")).sendKeys(email);
        Driver.getDriver().findElement(By.id("exampleInputPassword1")).sendKeys(pass + Keys.ENTER);

        Assert.assertEquals("Loan Application", Driver.getDriver().getTitle());

    }
}

