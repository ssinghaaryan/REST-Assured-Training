package introToREST;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class PostRequests {
	
	// 1. POST request using HashMap.
	
//	@Test(priority = 1)
	void postUsingHashMap() {
		
	HashMap data = new HashMap();
	
	data.put("name", "Daniel");
	data.put("location", "delhi");
	data.put("phone", "4444"); 

	String courseArr[] = {"Python", "Rust"};
	data.put("courses", courseArr);
	
	given()
		.contentType("application/json")
		.body(data)
	
	.when()
		.post("http://localhost:3000/list")
	
	.then()
		.statusCode(201)
		.body("name", equalTo("Daniel"))
		.body("location", equalTo("delhi"))
		.body("phone", equalTo("4444"))
		.body("courses[0]", equalTo("Python"))
		.body("courses[1]", equalTo("Rust"))
		.log().all();
		
	}
	
	// 2. POST request using org.json library.
	
//	@Test(priority = 1)
	void postUsingJsonLibrary() {
		
		JSONObject data = new JSONObject();
		
		data.put("name", "Daniel");
		data.put("location", "delhi");
		data.put("phone", "4444"); 

		String courseArr[] = {"Python", "Rust"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data.toString()) // "data" JSONObject is in the JSON format and the body needs to have it in the string format, hence the conversion.
		
		.when()
			.post("http://localhost:3000/list")

		.then()
			.statusCode(201)
			.body("name", equalTo("Daniel"))
			.body("location", equalTo("delhi"))
			.body("phone", equalTo("4444"))
			.body("courses[0]", equalTo("Python"))
			.body("courses[1]", equalTo("Rust"))
			.log().all();
	}
	
	// 3. POST request using POJO
	
//	@Test(priority = 1)
	void postUsingPOJO() {
		
		POJOPostRequest data = new POJOPostRequest();
		
		data.setName("Daniel");
		data.setLocation("delhi");
		data.setPhone("4444");
		
		String coursesArr[] = {"Python", "Rust"};
		data.setCourses(coursesArr);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/list")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Daniel"))
			.body("location", equalTo("delhi"))
			.body("phone", equalTo("4444"))
			.body("courses[0]", equalTo("Python"))
			.body("courses[1]", equalTo("Rust"))
			.log().all();
			
	}
	
	// 4. POST request using external JSON file
	@Test(priority = 1)
	void postUsingExternalJSONFile() throws FileNotFoundException {
		
		File f = new File("./jsonData.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		given()
			.contentType("application/json")
			.body(data.toString()) // "data" JSONObject is in the JSON format and the body needs to have it in the string format, hence the conversion.
	
		.when()
			.post("http://localhost:3000/list")

		.then()
			.statusCode(201)
			.body("name", equalTo("Daniel"))
			.body("location", equalTo("delhi"))
			.body("phone", equalTo("4444"))
			.body("courses[0]", equalTo("Python"))
			.body("courses[1]", equalTo("Rust"))
			.log().all();
		
	}
	
	@Test(priority = 2)
	void delete() {
		
		given()
		
		.when()
			.delete("http://localhost:3000/list/student/4")
		
		.then()
			.statusCode(200);
		
	}
	
}
