package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlacePojo;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

public class StepDefination extends Utils {
	
	ResponseSpecification respSpec;
	RequestSpecification request;
	Response response;
	
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("Add payload of add place API with {string} {string} {string}")
	public void add_payload_of_add_place_api_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions

	   
      request = given().spec(requestSpecification()).body(data.AddPlacePayload(name, language, address));
		
	}
	
	@When("Hit {string} API with http {string} request")
	public void hit_api_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		
		APIResources resourceObj=APIResources.valueOf(resource);
		System.out.println(resourceObj.getResource());
		
		if(method.equalsIgnoreCase("POST")) {
			 response = request.when().post(resourceObj.getResource());
		}
		else if(method.equalsIgnoreCase("GET")) {
			response = request.when().get(resourceObj.getResource());
		}
		
		   respSpec =  new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//		   response = request.when().post(resourceObj.getResource());
				  
		  
	}
	
	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	   
		assertEquals(response.getStatusCode(),200);
	}
	
	@Then("and {string} in response is {string}")
	public void and_in_response_is(String keyValue, String expectValue) {
	    // Write code here that turns the phrase above into concrete actions
	   
	    assertEquals(getJsonPath(response, keyValue),expectValue);
	}
		
	@Then("Verify place Id created maps to {string} using {string} API")
	public void verify_place_id_created_maps_to_using_api(String expectedName, String resource) throws IOException {
	    
		place_id =getJsonPath(response,"place_id");
		request = given().spec(requestSpecification()).queryParam("place_id", place_id);
		hit_api_with_http_request(resource, "GET");
		String actName =getJsonPath(response, "name");
		assertEquals(expectedName,actName);		
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   
		request=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
}
