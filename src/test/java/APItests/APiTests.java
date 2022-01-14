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
    //String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2xvYW5cL2FwaSIsImF1ZCI6Imh0dHA6XC9cL2xvY2FsaG9zdFwvbG9hblwvYXBpIiwiaWF0IjoxNjQxNzcxODk2LCJleHAiOjE2NDE3NzU0OTYsImRhdGEiOnsidXNlcl9pZCI6IjM1NjgiLCJ0eXBlIjoiMSJ9fQ.iKiMhW0dSBED_rHC-bA0WyTu2SihvIKoJKmsqd5Ilvc\n";
    String jwtToken;
    String api_adminLoginEmail = ConfigReader.getProperty("api_adminLoginEmail");
    String api_adminLoginPassword = ConfigReader.getProperty("api_adminLoginPass");
   String token = ConfigReader.getProperty("token");

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
        System.out.println(jwtToken);

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
        //   String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2xvYW5cL2FwaSIsImF1ZCI6Imh0dHA6XC9cL2xvY2FsaG9zdFwvbG9hblwvYXBpIiwiaWF0IjoxNjQxNTc0NDExLCJleHAiOjE2NDE1NzgwMTEsImRhdGEiOnsidXNlcl9pZCI6IjM1NjYiLCJ0eXBlIjoiMiJ9fQ.JtiKZs6VXyN005QjnSSB7DcRXpqqcORDrTfQUPZj1B4";

        //String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2xvYW5cL2FwaSIsImF1ZCI6Imh0dHA6XC9cL2xvY2FsaG9zdFwvbG9hblwvYXBpIiwiaWF0IjoxNjQxNTc0NDExLCJleHAiOjE2NDE1NzgwMTEsImRhdGEiOnsidXNlcl9pZCI6IjM1NjYiLCJ0eXBlIjoiMiJ9fQ.JtiKZs6VXyN005QjnSSB7DcRXpqqcORDrTfQUPZj1B4";
        given().
                header("Authorization", token).
                body("{\n" + " \"id\": \"582\"" + "}").
                when().log().all().
                post("/mortagagedetails.php").
                then().log().all().
                statusCode(200).
                body(containsString("single_application"));


    }

}