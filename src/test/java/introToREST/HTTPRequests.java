package introToREST;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPRequests {
	
	int userId;
	
	@Test(priority = 1)
	void getUsers() {
	
			given()
			
			.when()
				.get("https://reqres.in/api/users?page=2")
				
			.then()
				.statusCode(200)
				.body("page", equalTo(2))
				.log().all();
		
	}
	
	@Test(priority = 2)
	void createUser() {
		
			HashMap hm = new HashMap();
			hm.put("name", "tester");
			hm.put("job", "qa");
			
			userId = given() // userId declared globally above.
				.contentType("application/json")
				.body(hm)
			
			.when()
				.post("https://reqres.in/api/users")
				.jsonPath().getInt("id"); // getInt function used as the id field is an integer. If had been string, would use getString.
			
//			.then()
//				.statusCode(201)
//				.log().all();
		
	}
	
	@Test(priority = 3, dependsOnMethods = "createUser")
	void updateUser() {
	
			HashMap hm = new HashMap();
			hm.put("name", "hero");
			hm.put("job", "sdet");
			
			given()
				.contentType("application/json")
				.body(hm)
			
			.when()
				.put("https://reqres.in/api/users/" + userId)
//				.jsonPath().getInt("id"); // getInt function used as the id field is an integer. If had been string, would use getString.
			
			.then()
				.statusCode(200)
				.log().all();
		
	}
	
	@Test(priority = 4)
	void deleteUser() {
		
		given()
		
		.when()
			.delete("https://reqres.in/api/users/" + userId)
		
		.then()
			.statusCode(204)
			.log().all();
		
	}
	
	
	
	
	
	
	
	
	

}
