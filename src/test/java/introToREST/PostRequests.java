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
	
	// 3. POST request using POJO class.
	
//	@Test(priority = 1)
	void postUsingPOJO() {
		
		POJOPostRequest data = new POJOPostRequest(); // Creating object of the POJO class for using the setter and getters.
		
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
		
		File f = new File("./jsonData.json"); // To access the file. Provided by Java itself.
		
		FileReader fr = new FileReader(f); // To read the data from file, as name suggests. Provided by Java.
		
		JSONTokener jt = new JSONTokener(fr); // Takes a source string and extracts characters and tokens from it.
											  // It is used by JSONObject and JSONArray to parse JSON string.
		
		JSONObject data = new JSONObject(jt); // Extracting data in form of JSON object.
		
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
