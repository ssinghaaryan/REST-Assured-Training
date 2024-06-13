package introToREST;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	
	@Test
	void testCookies() {
		
		Response res = given()
		
		.when()
			.get("https://google.com");
		
		//Get single Cookie Info
//		String cookieInfo = res.getCookie("AEC");
//		System.out.println(cookieInfo);
		
		//Get all cookies info
		Map<String, String> cookieData = res.getCookies();
		
		for(String k : cookieData.keySet()) {
			
			String cookieInfo = res.getCookie(k);
			System.out.println(k + "     " + cookieInfo);
			
		}
	
	}

}
