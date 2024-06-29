package com.learning.aop;

import com.learning.aop.service.GreetService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AopApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void givenObjectSerializedThenTrueReturned() throws JsonSerializationException, JSONException {
		Person person = new Person("soufiane", "cheouati", "34");
		ObjectToJsonConverter serializer = new ObjectToJsonConverter();
		String jsonString = serializer.convertToJson(person);
		JSONAssert.assertEquals(
				"{\"age\":\"34\",\"first_name\":\"Soufiane\",\"last_name\":\"Cheouati\"}",
				jsonString, JSONCompareMode.LENIENT);
	}

	@Test
	void givenName_whenGreet_thenReturnCorrectResult() {
		GreetService.greetPeople();
	}

}
