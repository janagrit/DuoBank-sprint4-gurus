package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import utilities.APIUtils;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class API_SighUp_LoginStepDefs {


    RequestSpecification requestSpecification;
    Response response;
    String token;



    static{ baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";}

    @Given("I send POST {string} request as a valid user with email {string} and password {string}")
    public void iSendPOSTRequestAsAValidUserWithEmailAndPassword(String endpointPOST, String user, String pass) {


        requestSpecification = given().
                body("{\n" +
                        "\"email\" : \"" + user + "\",\n" +
                        "\"password\" : \"" + pass + "\"\n" +
                        "}");

        response = requestSpecification.when().log().all().  // indicate what type of request and the endpoint
                post(endpointPOST);

        token = APIUtils.generateToken(user, pass);
        System.out.println(token);


    }

    @And("The response body should contain the confirmation message {string}")
    public void theResponseBodyShouldContainTheConfirmationMessage(String msg) {

        response.then().log().all().
                body("message", equalTo("You have successfully logged in.")).extract().response();
    }


    @Then("The status code should be {int}")
    public void theStatusCodeShouldBe(int statusCode) {

        response.then().log().all().
                statusCode(statusCode).extract().response().
                statusLine();
    }



    @Given("I send POST {string} request as an invalid user with email {string} and password {string}")
    public void iSendPOSTRequestAsAnInvalidUserWithEmailAndPassword(String endpointPOST, String user, String pass) {

        requestSpecification = given().
                body("{\n" +
                        "\"email\" : \"" + user + "\",\n" +
                        "\"password\" : \"" + pass + "\"\n" +
                        "}");

        response = requestSpecification.when().log().all().  // indicate what type of request and the endpoint
                post(endpointPOST);

    }

    @And("The response body should contain the informative message {string}")
    public void theResponseBodyShouldContainTheInformativeMessage(String msg2) {
        response.then().
                body("status", equalTo(42)).
                body("message", equalTo(msg2)).extract().response();
    }





}
