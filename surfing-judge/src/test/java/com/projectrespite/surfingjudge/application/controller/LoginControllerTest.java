package com.projectrespite.surfingjudge.application.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectrespite.surfingjudge.domain.service.LoginService;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({ LoginController.class, LoginService.class })
public class LoginControllerTest {

	@Autowired
	private MockMvc mvc;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	@SneakyThrows
	public void loginPlayer() {
		val request = "{\"password\":\"player\"}";
		val response = mvc.perform(
				post("/login")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(request))
				.andExpect(status().is(200))
				.andReturn();
		Map<String, Object> responseMap = mapper.readValue(
				response.getResponse().getContentAsString(),
				new TypeReference<HashMap<String, Object>>() {
				});
		assertThat(responseMap.get("status"), is("success"));
		assertThat(responseMap.get("role"), is("player"));
		assertThat(responseMap.size(), is(2));
	}

	@Test
	@SneakyThrows
	public void loginJudge() {
		val request = "{\"password\":\"judge\"}";
		val response = mvc.perform(
				post("/login")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(request))
				.andExpect(status().is(200))
				.andReturn();
		Map<String, Object> responseMap = mapper.readValue(
				response.getResponse().getContentAsString(),
				new TypeReference<HashMap<String, Object>>() {
				});
		assertThat(responseMap.get("status"), is("success"));
		assertThat(responseMap.get("role"), is("judge"));
		assertThat(responseMap.size(), is(2));
	}

	@Test
	@SneakyThrows
	public void loginWrongPassword() {
		val request = "{\"password\":\"test\"}";
		mvc.perform(
				post("/login")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(request))
				.andExpect(status().is(401));
	}
}
