package product;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import products.Data;
import products.ProductResponse;

public class CreateProduct extends BaseTest {
	Data data;
	Response response;
	ObjectMapper mapper = new ObjectMapper();
	ProductResponse productResponse;
	
	@Test(priority = 0)
	public void createProduct() throws JsonMappingException, JsonProcessingException {

		data = new Data(0, "Azzam", "Desc", "https://loremflickr.com/250/250", "39549.05", null, true, null, null);

		response = RestAssured.given(spec).contentType(ContentType.JSON).body(data).post("/products");

		data.setId(response.jsonPath().getInt("data.id"));

		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);

	    productResponse = mapper.readValue(response.asPrettyString(), ProductResponse.class);

		Assert.assertEquals(productResponse.getData().toString(), data.toString(), "It's different data!");
	}
	
	@Test(priority = 1)
	public void getProduct() throws JsonMappingException, JsonProcessingException {
		
		response = RestAssured.given(spec).contentType(ContentType.JSON).get("/products/"+data.getId());
		
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);

		productResponse = mapper.readValue(response.asPrettyString(), ProductResponse.class);

		Assert.assertEquals(productResponse.getData().toString(), data.toString(), "It's different data!");	
	}
	
	@Test(priority = 2)
	public void editProduct() throws JsonMappingException, JsonProcessingException {
		
		Data dataEdit = new Data(0, "Azzam", "Desc", "https://loremflickr.com/250/250", "39549.05", "232.0", true, null, null);
		response = RestAssured.given(spec).contentType(ContentType.JSON).body(dataEdit).put("/products/"+data.getId());
		
		dataEdit.setId(response.jsonPath().getInt("data.id"));
		
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);

		productResponse = mapper.readValue(response.asPrettyString(), ProductResponse.class);

		Assert.assertEquals(productResponse.getData().toString(), dataEdit.toString(), "It's different data!");
	}
	
	@Test(priority = 3)
	public void deleteProduct() throws JsonMappingException, JsonProcessingException {
		response = RestAssured.given(spec).contentType(ContentType.JSON).delete("/products/"+data.getId());
		
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);

		productResponse = mapper.readValue(response.asPrettyString(), ProductResponse.class);
		
		Assert.assertEquals(productResponse.getData(), null);
		
		Response secondResponse = RestAssured.given(spec).contentType(ContentType.JSON).delete("/products/"+data.getId());
		
		assertThat(secondResponse.jsonPath().get("data.message"), equalTo("Resource not found"));
	}
}
