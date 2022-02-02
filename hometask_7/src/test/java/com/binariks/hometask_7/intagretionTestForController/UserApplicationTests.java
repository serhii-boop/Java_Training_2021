package com.binariks.hometask_7.intagretionTestForController;

import com.binariks.hometask_7.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class )
@SpringBootTest
@WebAppConfiguration
class UserApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mvc;

	@BeforeEach
	public void setup(){
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}


	@Test
	void returnsAllUsers() throws Exception {

		mvc.perform(MockMvcRequestBuilders
				.get("/users")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*].user_id").isNotEmpty());
	}

	@Test
	void returnsUserById() throws Exception {

		mvc.perform( MockMvcRequestBuilders
				.get("/users/{id}", 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.user_id").value(2));
	}

	@Test
	public void createUser() throws Exception
	{
		User user = new User();
		user.setName("hasdg");
		user.setSurname("asfda");
		user.setEmail("sfsafd");
		user.setAddressList(null);
		mvc.perform( MockMvcRequestBuilders
				.post("/users/add")
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Test
	public void updateUser() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders
				.put("/users/update/{id}", 2)
				.content(asJsonString(new User("firstName", "lastName", "asfs@gmail.com")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("firstName"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("lastName"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("asfs@gmail.com"));
	}

	@Test
	public void deleteUser() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders.delete("/users/delete/{id}", 25) )
				.andExpect(status().isOk());
	}


}
