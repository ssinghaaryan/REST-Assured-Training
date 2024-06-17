package introToREST;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeaderDemo {
	
//	@Test
 	void testHeaders() {
		
		given()
		
		.when()
			.get("https://google.com")
			

		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and() // Not necessary to add this add() method.
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws");
	}
 	
 	@Test
 	void getHeaders() {
		
		Response res = given()
		
		.when()
			.get("https://google.com");
		
		// Get single header info
//		String headerInfo = res.getHeader("Content-Type");
//		System.out.println("The header info is " + headerInfo);
		
		//Get all header info
		Headers allHeaders = res.getHeaders();		
		
		for(Header hd:allHeaders) {
			
			System.out.println(hd.getName() + "   " + hd.getValue());
			
		}
		
	}

}
