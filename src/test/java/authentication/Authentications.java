package authentication;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentications {

	// 1. Basic Auth Authorization
	@Test(priority = 1)
	void testBasicAuth() {

		given().auth().basic("postman", "password")

				.when().get("https://postman-echo.com/digest-auth")

				.then().statusCode(200).body("authenticated", equalTo(true));

	}

	// 2. Digest Auth Authorization
	@Test(priority = 2)
	void testDigestAuth() {

		given().auth().digest("postman", "password")

				.when().get("https://postman-echo.com/digest-auth")

				.then().statusCode(200).body("authenticated", equalTo(true));

	}

	// 3. Preemptive Auth Authorization
	@Test(priority = 3)
		void testPreemptiveAuth() {
			
			given()
				.auth().preemptive().basic("postman", "password") // By using .preemptive() before .basic(), RESTAssured takes initiative of including the credentials.
			
			.when()
				.get("https://postman-echo.com/basic-auth")
			
			.then()
				.statusCode(200)
				.body("authenticated", equalTo(true));
			
	}
	
	// 4. Bearer Token Authorization
	@Test
	void testBearerTokenAuth() {
		
		String token = "adsadsar4e1";
		
		given()
			.header("Authorization", "Bearer " + token)
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200);
		
	}
	
	// 5. OAuth2 Authorization
	@Test
	void testOAuth2Auth() {
		
		given()
			.auth().oauth2("") // Token needs to be passed here as the value to oauth2.
		.when()
			.get() //
		
		.then()
			.statusCode(200);
		
	}
	
	// 6. API Key Authorization
	@Test
	void apiKeyAuth() {
		
		given()
			.pathParam("path", "data/2.5/forecast/daily")
			.queryParam("q", "delhi")
			.queryParam("units", "metric")
			.queryParam("cnt", "7")
			.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
		
		.when()
			.get("https://api.openweathermap.org/{path}")
		
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	
	
	
	
}
