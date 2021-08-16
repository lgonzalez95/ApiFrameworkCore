package stepsDefinitions;

import io.cucumber.java.After;

import static io.restassured.RestAssured.given;
import static stepsDefinitions.BaseSteps.requestSpecificationSetup;

public class Hooks {

    @After("@CreateUsersValid")
    public void testEnd() {
         given().spec(requestSpecificationSetup()).delete("https://gorest.co.in/public/v1/users/" + StepsDefinitions.userId);
    }
}
