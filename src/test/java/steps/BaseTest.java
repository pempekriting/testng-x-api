package steps;

import io.cucumber.java.Before;
import utilities.RestAssuredExtension;


public class BaseTest {

	@Before
	public void setUp() {
		RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
	}
}
