package steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.core.Is;
import org.json.JSONObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.Helper;
import utilities.RestAssuredExtension;

public class PostProduct {
	public static ResponseOptions<Response> response;
	JSONObject json;
	JSONObject jsonPut;
	JSONObject jsonMerged;
	int productId;

	@Given("I perform POST operation for {string} with payload")
	public void i_perform_post_operation_for_with_payload(String url, String docString) {
		json = new JSONObject(docString);
		response = RestAssuredExtension.PostOpsWithBody(url, docString);
		productId = response.getBody().jsonPath().get("data.id");
		
		assertThat(response.statusCode(), Is.is(200));
		assertThat(response.getBody().jsonPath().get("code"), Is.is(201));
	}

	@Given("I perform GET operation for {string} that I recently create")
	public void i_perform_get_operation_for_that_i_recently_create(String url) {
		response = RestAssuredExtension.GetOps(url + "/" + productId);
		
		assertThat(response.statusCode(), Is.is(200));
		assertThat(response.getBody().jsonPath().get("data.name"), equalTo(json.get("name")));
		assertThat(response.getBody().jsonPath().get("data.description"), equalTo(json.get("description")));
		assertThat(response.getBody().jsonPath().get("data.image"), equalTo(json.get("image")));
		assertThat(response.getBody().jsonPath().get("data.price"), equalTo(json.get("price").toString()));
		assertThat(response.getBody().jsonPath().get("data.name"), equalTo(json.get("name")));
		assertThat(response.getBody().jsonPath().get("data.status"), equalTo(json.get("status")));
		
		response.getBody().prettyPrint();
	}

	@Given("I perform PUT operation for {string} that I recently created to include")
	public void i_perform_put_operation_for_that_i_recently_created_to_include(String url, String docString) {
		jsonPut = new JSONObject(docString);
		jsonMerged = Helper.mergeJSONObjects(json, jsonPut);
		
		response = RestAssuredExtension.PutOpsWithBody(url + "/" + productId, jsonMerged.toString());
		
		assertThat(response.statusCode(), Is.is(200));
		assertThat(response.getBody().jsonPath().get("code"), Is.is(200));
		assertThat(response.getBody().jsonPath().get("data.discount_amount"), equalTo(jsonMerged.get("discount_amount").toString()));
		assertThat(response.getBody().jsonPath().get("data.status"), equalTo(jsonMerged.get("status")));
		
		response.getBody().prettyPrint();
	}

	@Then("I perform DELETE operation for {string} that I recently updated")
	public void i_perform_delete_operation_for_that_i_recently_updated(String url) {
		response = RestAssuredExtension.DeleteOps(url + "/" + productId);
		
		assertThat(response.statusCode(), Is.is(200));
		assertThat(response.getBody().jsonPath().get("code"), Is.is(204));
		
		response.getBody().prettyPrint();
	}
}
