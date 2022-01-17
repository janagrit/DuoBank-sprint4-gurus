package stepDefinitions;

import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojos.UserInfo;
import utilities.ConfigReader;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class api_StepDefByT {


//    static {
//        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";
//    }

    RequestSpecification requestSpecification;
    Response response;
    String api_loginEmail = ConfigReader.getProperty("api_loginEmail");
    String api_loginPass = ConfigReader.getProperty("api_loginPass");
    Map<String, Object> map;


    String jwtToken;
    int validId;
    String path;
    RequestSpecification body;
    String firstName;
    String lastName;
    String email;
    String password;


    public void registerWithValidCredentialsUsingPOJOClass() {

        UserInfo userInfo = new UserInfo("Tom", "Simpson", email, password);

        given().
                body(userInfo).
                when().log().all().
                post("/register.php").
                then().log().all().
                statusCode(200).
                body("message", equalTo("You have successfully registered."));


    }


    @Given("I add the header {string} {string}")
    public void iAddTheHeader(String string, String token) {
        requestSpecification = given().
                header(string, jwtToken);


    }

    @When("I send a GET request to {string} endpoint")
    public void iSendAGETRequestToEndpoint(String endpoint) {
        response = requestSpecification.
                when().log().all().
                get(endpoint);


    }


    @Then("The response status code should be {int}")
    public void theResponseStatusCodeShouldBe(Integer int1) {

        response.then().log().all().
                statusCode(200);

    }


    @Given("I am authorized at endpoint {string}")
    public void iAmAuthorizedAtEndpoint(String endpoint) {

        response = given().log().all().
                header("Content-Type", "application/json").
                body("{\n" +
                        " \"email\": \"" + api_loginEmail + "\",\n" +
                        "\"password\": \"" + api_loginPass + "\" \n" +
                        "}").
                post(endpoint);

        String jsonString = response.asString();
        jwtToken = JsonPath.from(jsonString).get("token");


    }


    @Given("Valid id number at the endpoint {string}")
    public void validIdNumber(String endpoint) {
        response = given().log().all().
                header("Authorization", jwtToken).get(endpoint);
        String validId1 = response.path("mortagage_applications[0].id");
        validId = Integer.parseInt(validId1);


    }


    @When("I send a POST request to {string} endpoint using a valid id number")
    public void iSendAPOSTRequestToEndpointUsingAValidIdNumber(String endpoint) {
        response = given().log().all().
                header("Authorization", jwtToken).
                body(
                        "{  \"id\" : " + validId + " }").
                post(endpoint);
    }


    @Given("I am authorized as a user at endpoint {string}")
    public void iAmAuthorizedAsAUserAtEndpoint(String endpoint) {
        response = given().log().all().
                header("Content-Type", "application/json").
                body("{\n" +
                        " \"email\": \"" + api_loginEmail + "\",\n" +
                        "\"password\": \"" + api_loginPass + "\" \n" +
                        "}").
                post(endpoint);

        String jsonString = response.asString();
        jwtToken = JsonPath.from(jsonString).get("token");

    }

    @When("I send a GET request to {string} endpoint with user token")
    public void iSendAGETRequestToEndpointWithUserToken(String endpoint) {
        response = given().log().all().header("Authorization", jwtToken).
                get(endpoint);


    }

    @Then("The id should be {int}")
    public void theIdShouldBe(Integer id) {

        String mortgage_id = response.path("mortagage_applications[0].id");
        Integer mortgage_id1 = Integer.parseInt(mortgage_id);

        Assert.assertEquals(id, mortgage_id1);


    }


    @Then("The name should be {string}")
    public void theNameShouldBe(String name) {
        String mortgage_name = response.path("mortagage_applications[0].b_firstName");
        Assert.assertEquals(name, mortgage_name);

    }


    @When("I send a GET request to {string} endpoint without a valid token")
    public void iSendAGETRequestToEndpointWithoutAValidToken(String endpoint) {
        response = given().log().all().
                get(endpoint);

    }

    @Then("The body status should be {int}")
    public void theBodyStatusShouldBe(Integer int1) {
        response.then().log().all().
                body("status", equalTo(int1));


    }

    @Then("The body message should be {string}")
    public void theBodyMessageShouldBe(String message) {
        response.then().log().all().
                body("message", is(message));
    }


    @When("I send a POST request to {string} endpoint")
    public void iSendAPOSTRequestToEndpoint(String endpoint) {

        String bodyContains = "{\n" + " \"id\": \"33335\"" + "}";
        System.out.println(" BODYCONTAINS" + bodyContains);

        response = given().log().all().header("Authorization", jwtToken).

                body(bodyContains).post(endpoint);


    }


    @Then("The body should contain {string}")
    public void theBodyShouldContain(String string) {
        response.then().log().all().
                body(containsString("single_application"));


    }


    @Given("I add the headers {string} {string}")
    public void iAddTheHeaders(String accept, String jason) {

        requestSpecification = given().
                header(accept, jason);
    }

    @When("I POST info from POJO class to {string} path")
    public void iPOSTInfoFromPOJOClassToPath(String path) {


        UserInfo user = new UserInfo();
        user.setEmail("duotechb5@gmail.com");
        user.setPassword("duotechb5");

        response = requestSpecification.
                when().log().all().
                body(user)
                .post(path);
    }

    @Given("I add the headers {string}, {string} and {string}, {string}")
    public void iAddTheHeadersAnd(String contentType, String appJson, String accept, String appJson2) {


        requestSpecification = given().log().all().
                header(contentType, appJson).
                header(accept, appJson2);

    }

    @Given("I create a fake credentials using POJO class")
    public void iCreateAFakeCredentialsUsingPOJOClass() {

        Faker fake = new Faker();
        firstName = fake.name().firstName();
        lastName = fake.name().lastName();
        email = fake.internet().emailAddress();
        password = fake.internet().password();

        UserInfo user = new UserInfo(firstName, lastName, email, password);

        body = requestSpecification.body(user);
    }

    @When("I POST newly created user")
    public void iPOSTNewlyCreatedUser() {

        path = "register.php";

        response = body.
                when().log().all().
                post(path);
    }

    @Then("I verify the status code should be not greater than {int}")
    public void iVerifyTheStatusCodeShouldBeNotGreaterThan(Integer statusCode) {

        response.then().log().all().
                statusCode(not(greaterThan(statusCode)));

    }

    @Then("The success {string} should be {string}")
    public void theSuccessShouldBe(String message, String successMessage) {

        response.then().log().all().
                body(message, is(successMessage));
    }



}
