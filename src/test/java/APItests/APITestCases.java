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

public class APITestCases {

    Response response;

    String email = new Faker().internet().emailAddress();
    String password = "tom2022";
    //String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2xvYW5cL2FwaSIsImF1ZCI6Imh0dHA6XC9cL2xvY2FsaG9zdFwvbG9hblwvYXBpIiwiaWF0IjoxNjQxNzQ5ODI2LCJleHAiOjE2NDE3NTM0MjYsImRhdGEiOnsidXNlcl9pZCI6IjM1NjYiLCJ0eXBlIjoiMiJ9fQ.bz4DbO3e189tqPjoYd6XC3EC9aVqsv0APaz5GMfemr8";
    String jwtToken;
    String api_loginEmail = ConfigReader.getProperty("api_loginEmail");
    String api_loginPassword = ConfigReader.getProperty("api_loginPass");
    String token = ConfigReader.getProperty("token");

    @BeforeClass
    public static void setupBaseUri() {
        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";

    }


    @Test
    public void resisterWithValidCredentialsUsingPOJOClass() {

        UserInfo userInfo = new UserInfo("Tom", "Simpson", email, password);

        given().
                body(userInfo).
                when().log().all().
                post("/register.php").
                then().log().all().
                statusCode(200).
                body("message", equalTo("You have successfully registered."));


    }


    @Test
    public void loginWithValidInfo() {

        response = given().log().all().
                body("{\n" +
                        "\"email\" : \"" + api_loginEmail + "\",\n" +
                        "\"password\" : \"" + api_loginPassword + "\"\n" +
                        "}").
                when().log().all().
                post("/login.php").
                then().log().all().
                statusCode(200).
                body("message", equalTo("You have successfully logged in.")).extract().response();

        String jsonString = response.asString();
        jwtToken = JsonPath.from(jsonString).get("token");
        System.out.println(jwtToken);


    }


    @Test
    public void GetRequestForMortgageApplicationValidAdminCredentials() {
        //  String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2xvYW5cL2FwaSIsImF1ZCI6Imh0dHA6XC9cL2xvY2FsaG9zdFwvbG9hblwvYXBpIiwiaWF0IjoxNjQxNTc0NDExLCJleHAiOjE2NDE1NzgwMTEsImRhdGEiOnsidXNlcl9pZCI6IjM1NjYiLCJ0eXBlIjoiMiJ9fQ.JtiKZs6VXyN005QjnSSB7DcRXpqqcORDrTfQUPZj1B4";
        given().
                header("Authorization", token).
                when().log().all().
                get(" /getmortagage.php").
                then().log().all().
                statusCode(200);


    }

    @Test
    public void PostRequestForTheApplicationDetailsOfTheLoggedInUser() {
        //   String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2xvYW5cL2FwaSIsImF1ZCI6Imh0dHA6XC9cL2xvY2FsaG9zdFwvbG9hblwvYXBpIiwiaWF0IjoxNjQxNTc0NDExLCJleHAiOjE2NDE1NzgwMTEsImRhdGEiOnsidXNlcl9pZCI6IjM1NjYiLCJ0eXBlIjoiMiJ9fQ.JtiKZs6VXyN005QjnSSB7DcRXpqqcORDrTfQUPZj1B4";

        //String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2xvYW5cL2FwaSIsImF1ZCI6Imh0dHA6XC9cL2xvY2FsaG9zdFwvbG9hblwvYXBpIiwiaWF0IjoxNjQxNTc0NDExLCJleHAiOjE2NDE1NzgwMTEsImRhdGEiOnsidXNlcl9pZCI6IjM1NjYiLCJ0eXBlIjoiMiJ9fQ.JtiKZs6VXyN005QjnSSB7DcRXpqqcORDrTfQUPZj1B4";
        given().
                header("Authorization", token).
                body("{\n" + " \"id\": \"33335\"" + "}").
                when().log().all().
                post("/mortagagedetails.php").
                then().log().all().
                statusCode(200).
                body(containsString("single_application"));
        System.out.println(jwtToken);


    }


    @Test
    public void GetRequestForMortgageApplicationUsingAdminCredentialsWithoutValidToken() {

        given().
                header("Content-Type", "application/json").
                body("{\n" +
                        " \"email\": \"" + api_loginEmail + "\",\n" +
                        "\"password\": \"" + api_loginPassword + "\" \n" +
                        "}").
                when().log().all().
                get("/getmortagage.php").
                then().log().all().
                body("status", equalTo(401)).
                body("message", is("Unauthorized"));

    }


    @Test
    public void GetRequestLoginAndVerifyNewUser() {

        given().
                header("Accept", "application/json").
                header("Authorization", token).
                body("{\n" +
                        "\"email\" : \"" + api_loginEmail + "\",\n" +
                        "\"password\" : \"" + api_loginPassword + "\"\n" +
                        "}").
                when().log().all().
                get("/getmortagage.php").
                then().log().all().
                statusCode(200).
                body("mortagage_applications.b_firstName", hasItem("Minnie")).
                body("mortagage_applications.total_loan_amount", hasItem("500000"));

    }

    @Test
    public void PostRequestMissingBody() {

        given().
                header("Accept", "application/json").
                when().log().all().
                post("/login.php").
                then().log().all().
                assertThat().
                statusCode(200).
                body("status", equalTo(422)).
                body("message", equalTo("Please Fill in all Required Fields!"));

    }
}
