package utilities;

import java.net.URI;
import java.net.URISyntaxException;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class RestAssuredExtension {
	public static RequestSpecification Request;
	protected static RequestSpecification spec;
	PreemptiveOAuth2HeaderScheme authScheme = new PreemptiveOAuth2HeaderScheme();

	public RestAssuredExtension() {
		authScheme.setAccessToken("e3479c4068b18c10ce220f5665f1c206bff27b3e9a722bcc35cf52e36708a1fb");
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri("https://gorest.co.in/public-api");
		builder.setContentType(ContentType.JSON);
		builder.setAuth(authScheme);
		RequestSpecification requestSpec = builder.build();
		Request = RestAssured.given().spec(requestSpec);
	}

	public static ResponseOptions<Response> GetOps(String url) {
		try {
			return Request.get(new URI(url));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ResponseOptions<Response> PutOpsWithBody(String url, String docString) {
		Request.body(docString);
		return Request.put(url);
	}

	public static ResponseOptions<Response> DeleteOps(String url) {
		return Request.delete(url);
	}

	public static ResponseOptions<Response> PostOpsWithBody(String url, String docString) {
		Request.body(docString);
		return Request.post(url);
	}

}
