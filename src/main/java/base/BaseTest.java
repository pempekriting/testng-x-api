package base;

import org.testng.annotations.BeforeTest;

import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

	public RequestSpecification spec;
	public PreemptiveOAuth2HeaderScheme authScheme = new PreemptiveOAuth2HeaderScheme();

	@BeforeTest
	public void setUp() {
		authScheme.setAccessToken("820d0959b7b29a2081f427265de6cb6b14f132b105a52bf0002492153ea4409d");
		spec = new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public-api").setAuth(authScheme).build();
	}
}
