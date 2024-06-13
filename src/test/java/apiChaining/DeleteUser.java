package apiChaining;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {

	@Test
	void testGetUser(ITestContext context) {
		
		int id = (Integer) context.getAttribute("userId");
		
		String bearerToken = "e2085ce9166749855360837edc6375f47f807dc68702529f756d4f016ab6011a";
		
		given()
			.header("Authorization", "Bearer " + bearerToken)
			.pathParam("id", id)
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
			.log().all();

	}
}
