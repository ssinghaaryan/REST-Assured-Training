package introToREST;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class PathAndQueryParameters {
	
//	https://reqres.in/api/users?page=2&id=1
	
	@Test
	void testQueryAndPathParameters() {
		
		given()
			.pathParam("path", "users")
			.queryParam("page", 2)
			.queryParam("id", 1)
		
		.when()
			.get("https://reqres.in/api/{path}") // https://reqres.in/api/users?page=2&id=1 is the link but page and id not needed as specified above in given().

		.then()
			.statusCode(200)
			.log().all();
	}

}
