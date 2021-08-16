package stepsDefinitions;


import Utils.ApiResources;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import utils.DataHelper;
import utils.TestDataBuild;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.DataHelper.getJsonPath;
import static utils.DataHelper.getJsonPayload;

public class StepsDefinitions extends BaseSteps{
    RequestSpecification requestSpecification;
    Response response;
    String requestBody;
    TestDataBuild testDataBuild = new TestDataBuild();
    static String userId;

    @Given("The {string} payload is loaded")
    public void the_payload_is_loaded(String string) throws IOException {
        requestBody = getJsonPayload("");
        requestSpecification = given().spec(requestSpecificationSetup())
                .body(testDataBuild.createUserPayload(requestBody));
    }

    @And("The request body contains next params")
    public void the_request_body_contains_next_params(DataTable dataTable) throws JsonProcessingException {
        JSONObject json = new JSONObject(requestBody);
        Map<String, String> rows = dataTable.asMap(String.class, String.class);

        for (Map.Entry<String, String> element: rows.entrySet())
            json.put(element.getKey(), element.getValue());

        requestBody = json.toString();
        requestSpecification = given().spec(requestSpecificationSetup()).body(testDataBuild.createUserPayload(requestBody));
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        if(method.equalsIgnoreCase("POST"))
            response = requestSpecification.when().post(ApiResources.valueOf(resource).getResource());
        else if(method.equalsIgnoreCase("GET"))
            response = requestSpecification.when().get(ApiResources.valueOf(resource).getResource());
        else if(method.equalsIgnoreCase("DELETE"))
            response = requestSpecification.when().delete(ApiResources.valueOf(resource).getResource());

    }

    @Then("The API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(int expectedValue) {
        assertEquals(response.getStatusCode(), expectedValue);
    }

    @Then("Verify userId created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String propertyName, String resource) {
        userId = getJsonPath(response, "data.id");
        requestSpecification = given().spec(requestSpecificationSetup()).pathParam("userId", userId);
        user_calls_with_http_request(resource, "GET");
        assertEquals(propertyName, getJsonPath(response,"data.email"));
    }
}
