 package parsingResponseData;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONResponseData {
	
	// Approach 1
//	@Test
	void testJsonResponse1() {
		
		given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("http://localhost:3000/list")
		
		.then()
			.statusCode(200)
			.body("student[1].location", equalTo("pakistan"));
			
	}
	
	// Approach 2
//	@Test
	void testJsonResponse2() {
		
		Response res = given()
			.contentType("ContentType.JSON")
		
		.when()
			.get("http://localhost:3000/list");
		
		String phone = res.jsonPath().get("student[2].phone").toString();
		Assert.assertEquals(phone, "3333");
		
	}
	
	// Approach 3 - This is the better way of capturing and working with the Response Body.
	@Test
	void testJsonResponse3() {
		
		boolean status = false;
		
		Response res = given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("http://localhost:3000/list");
		
		JSONObject jo = new JSONObject(res.asString());
		
		// Searching and validating name of the student in JSON.
		for(int i = 0; i < jo.getJSONArray("student").length(); i++) {
			
			String name = jo.getJSONArray("student").getJSONObject(i).get("name").toString();
//			System.out.println(name);
			
			if(name.equals("Bobby")) {
				
				status = true;
				break;
				
			}	
		}
			Assert.assertEquals(status, true);
			
		// Validating Total
			
	}
}
















