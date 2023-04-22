package net.codetojoy.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = Api.class) // This will only load the controller
public class ApiTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testApi() throws Exception {
		MvcResult result = mockMvc.perform(get("/v1/greeting/").contentType(MediaType.APPLICATION_JSON))

				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().is2xxSuccessful())
				.andReturn();
		assertThat(result.getResponse().getContentAsString()).isEqualTo("TRACER hello, bonjour!\n\n");

	}
}
