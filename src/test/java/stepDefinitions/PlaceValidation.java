package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.UrlEndPointsEnums;
import resources.Utils;

import java.io.IOException;

import static org.junit.Assert.*;


import static io.restassured.RestAssured.given;


public class PlaceValidation extends Utils {

    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild dataBuild = new TestDataBuild();

    @Given("Add place payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {

        res=given().spec(getRequestSpecification())
               .body(dataBuild.addPlacePayload(name, language, address));
    }

    @When("user calls {string} with post http request")
    public void user_calls_with_post_http_request(String endPointUrl) {


    }

    @When("user calls {string} with {string} http request")
    public void userCallsWithHttpRequest(String endPointUrl, String httpMethod) {

        UrlEndPointsEnums endPointsEnumValue  = UrlEndPointsEnums.valueOf(endPointUrl);

        resspec =new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        if (httpMethod.equalsIgnoreCase("POST"))
            response =res.when().post(endPointsEnumValue.getUrlEndPoint());
         else if (httpMethod.equalsIgnoreCase("GET"))
            response =res.when().get(endPointsEnumValue.getUrlEndPoint());

//        response =res.when().post(apiResources.getResource()).
//                then().spec(resspec).extract().response();
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(), 200);
    }

    @Then("{string} in response is {string}")
    public void in_response_is(String key, String value) {
        String keyValue = getJsonPathvalueFromKey(response,key);
        assertEquals( keyValue, value);
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String getPlaceAPI) throws IOException {

        String placeId = getJsonPathvalueFromKey(response,"place_id" );

        res=given().spec(getRequestSpecification())
                .queryParam("place_id",placeId);

        userCallsWithHttpRequest("GETPLACEAPI", "get");
        String actualName = getJsonPathvalueFromKey(response,"name" );
        assertEquals(actualName, expectedName );

    }

    @Given("Payload to delete")
    public void payload_to_delete() {

    }

}
