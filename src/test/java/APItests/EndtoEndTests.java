package APItests;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import pojos.UserInfo;
import pojos.UserLoginPOJO;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class EndtoEndTests {


    // multi-step workflow with several endpoints
    // main idea to verify the main issues between endpoints
    // integration testing



    @BeforeClass
    public static void setupBaseUri() {
        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";

    }

    String expectedPasswordMd5;

    @Test
    public void endToEndTest(){

        // Create a new user with POST

        Faker fake = new Faker();
        String name = fake.name().firstName();
        String lastName = fake.name().lastName();
        String email = fake.internet().emailAddress();
        String password = fake.internet().password();
        expectedPasswordMd5 = DigestUtils.md5Hex(password);


        UserInfo newUser = new UserInfo(name, lastName, email,  expectedPasswordMd5);

        given().
                body(newUser).
                when().log().all().
                post("/register.php").
                then().log().all().
                statusCode(200).
                body("message", equalTo("You have successfully registered."));
        System.out.println(newUser.toString());

        // Verify a new user wit with GET


        UserLoginPOJO loginPOJO = new UserLoginPOJO(email, expectedPasswordMd5);


        given().
                body("{\n" +
                        "\"email\" : \"" + email + "\",\n" +
                        "\"password\" : \"" + expectedPasswordMd5 + "\"\n" +
                        "}").
                when().log().all().
                post("/login.php").
        then().log().all().
                statusCode(200).
                body("message", equalTo("You have successfully logged in."));
        System.out.println(loginPOJO.toString());



        // Update the same user info with PUT -> cannot since we can update on db not on ui


        // Delete the same user with DELETE

        given().
                body(newUser).
                when().log().all().
                delete("/register.php").
                then().log().all().
                statusCode(200);
               // body("message", equalTo("You have successfully registered."));
      //  System.out.println(newUser.toString());


        // Verify the same user no longer exists with GET




    }








}
