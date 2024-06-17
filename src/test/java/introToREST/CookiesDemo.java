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
		
		// Get single Cookie info
//		String cookieInfo = res.getCookie("AEC");
//		System.out.println(cookieInfo);
		
		// Get all Cookies info
		Map<String, String> cookieData = res.getCookies(); // "getCookies" method returns a Map of cookie values.
		
//		cookieData.keySet() // .keySet() return all the keys in the HashMap and not the values.
		for(String k : cookieData.keySet()) {
			
			String cookieInfo = res.getCookie(k);
			System.out.println(k + "     " + cookieInfo);
			
//		.then()
//			.cookie("AEC", "{Dynamic Cookie code here}")
//			.log().all();
		}
	
	}

}
