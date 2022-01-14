package APItests;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import io.restassured.common.mapper.TypeRef;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pojos.UserInfo;
import utilities.ConfigReader;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class APiTests {

    Response response;

    String token = ConfigReader.getProperty("token");

    String jwtToken;
    String api_adminLoginEmail = ConfigReader.getProperty("api_adminLoginEmail");
    String api_adminLoginPassword = ConfigReader.getProperty("api_adminLoginPass");


    @BeforeClass
    public static void setupBaseUri() {
        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";

    }

    @Test
    public void loginWithValidAdminInfo() {

        response = given().log().all().
                body("{\n" +
                        "\"email\" : \"" + api_adminLoginEmail + "\",\n" +
                        "\"password\" : \"" + api_adminLoginPassword + "\"\n" +
                        "}").
                when().log().all().
                post("/login.php").
                then().log().all().
                statusCode(200).
                body("message", equalTo("You have successfully logged in.")).extract().response();

        String jsonString = response.asString();
        jwtToken = JsonPath.from(jsonString).get("token");

    }

    @Test
    public void getRequestForMortgageApplicationsWithValidAdminCredentials() {

        given().
                header("Authorization", token).
                when().log().all().
                get(" /getmortagage.php").
                then().log().all().
                statusCode(200);

    }

    @Test
    public void PostRequestForTheApplicationDetailsOfTheLoggedInUser() {

        given().
                header("Authorization", token).
                body("{\n" + " \"id\": \"582\"" + "}").
                when().log().all().
                post("/mortagagedetails.php").
                then().log().all().
                statusCode(200).
                body(containsString("single_application"));

        // body("eConsent_declarer_LastName", equalTo("Parker"));


    }

    @Test
    public void PostRequestForTheApplicationDetailsOfTheNonExistingUser() {

        given().
                header("Authorization", token).
                body("{\n" + " \"id\": \"2\"" + "}").
                when().log().all().
                post("/mortagagedetails.php").
                then().log().all().
                statusCode(200).
                body("single_application", equalTo(null));


    }


}