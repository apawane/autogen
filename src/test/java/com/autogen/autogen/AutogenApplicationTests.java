package com.autogen.autogen;

import com.autogen.autogen.todo.model.ToDoItemAddRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutogenApplicationTests {

	TestRestTemplate restTemplate = new TestRestTemplate();

	org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

	@Test
	@Ignore
	public void testGetTodo() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/todo/1000"), HttpMethod.GET, entity, String.class);
		String expected = "{\"id\":1000,\"text\":\"start walking\",\"createdAt\":\"2016-05-03T09:40:24.007+0000\",\"completed\":true}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	@Ignore
	public void testSaveTodo() throws JSONException, IOException {
		String text = "running";
		ToDoItemAddRequest toDoItemAddRequest = new ToDoItemAddRequest();
		toDoItemAddRequest.setText(text);
		HttpEntity<ToDoItemAddRequest> entity = new HttpEntity<ToDoItemAddRequest>(toDoItemAddRequest, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/todo"), HttpMethod.POST, entity, String.class);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(response.getBody());
		Assert.assertEquals(text, jsonNode.get("text").asText());
	}

	@Test
	@Ignore
	public void testValidateBrackets() throws JSONException, IOException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createURLWithPort("/tasks/validateBrackets"))
				.queryParam("input", "[]{}");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(
				builder.toUriString(), HttpMethod.GET, entity, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(response.getBody());
		Assert.assertEquals(true, jsonNode.get("balanced").asBoolean());
	}

	/*@Test
	public void testValidateBracketsUnbalanced() throws JSONException, IOException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createURLWithPort("/tasks/validateBrackets"))
				.queryParam("input", "%5B%5D%7B%7B");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(
				builder.toUriString(), HttpMethod.GET, entity, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(response.getBody());
		Assert.assertEquals(false, jsonNode.get("balanced").asBoolean());
	}*/

	private String createURLWithPort(String uri) {
		return "http://localhost:" + 8080 + uri;
	}
}
