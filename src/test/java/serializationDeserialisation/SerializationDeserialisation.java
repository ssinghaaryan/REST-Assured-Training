package serializationDeserialisation;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import introToREST.POJOPostRequest;

public class SerializationDeserialisation {

	// POJO -----> JSON (Serialization)
	@Test
	void convertPojoToJson() throws JsonProcessingException {

		// Created Java Object using POJO classs
		StudentPOJO data = new StudentPOJO();

		data.setName("Daniel");
		data.setLocation("delhi");
		data.setPhone("4444");
		String coursesArr[] = { "Python", "Rust" };
		data.setCourses(coursesArr);

		// Converting Java Object --> JSON Object (Serialization)
		ObjectMapper obj = new ObjectMapper();

		String jsonData = obj.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		System.out.println(jsonData);
	}

	// JSON ----> POJO (De-serialization)
	@Test
	void convertJsonToPojo() throws JsonProcessingException {

		String jsonData = "{{\n"
				+ "  \"location\" : \"delhi\",\n"
				+ "  \"phone\" : \"4444\",\n"
				+ "  \"courses\" : [ \"Python\", \"Rust\" ],\n"
				+ "  \"name\" : \"Daniel\"\n"
				+ "}";
		
		// Converting JSON Data ---> POJO Object
		ObjectMapper obj = new ObjectMapper();
		
		StudentPOJO student = obj.readValue(jsonData, StudentPOJO.class); // Converting JSON to POJO.
		
		student.getName();
		student.getLocation();
		student.getPhone();
		student.getCourses();		
		
	}

}
