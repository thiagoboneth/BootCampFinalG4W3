package com.mercadolibre.demo.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.demo.dto.TokenDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

@Transactional
@AutoConfigureTestEntityManager
@SpringBootTest
@AutoConfigureMockMvc
public class WareHouseControllerTest {

	private URI uri;

	@Autowired
	MockMvc mockMvc;

	private TokenDTO tokenDTO;

	@BeforeEach
	public void testandoAutenticacao() throws Exception {
		String json = "{\"user\": \"thiago\", \"senha\": \"123\"}";
		uri = new URI("/auth");

		MvcResult resultContendoToken = mockMvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		tokenDTO = new ObjectMapper().readValue(resultContendoToken.getResponse().getContentAsString(), TokenDTO.class);
	}

	@Test
	public void testSaveWareHouseWithSuccess() throws Exception {

		uri = new URI("/api/v1/fresh-products/warehouse/save");

		assertNotNull(uri);

		String requestJson =  "{\"wareHouseName\": \"wareHouseName1\"}";

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post(uri)
						.content(requestJson)
						.header("Content-Type", "application/json")
						.header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
				.andExpect(status().isCreated()).andReturn();

		String responseJson = result.getResponse().getContentAsString();

		assertNotNull(responseJson);
	}

	@Test
	public void testlistWareHouse() throws Exception {

		uri = new URI("/api/v1/fresh-products/warehouse/list");

		assertNotNull(uri);

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get(uri).header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
				.andExpect(status().isOk()).andReturn();

		String jsonRetorno = result.getResponse().getContentAsString();

		assertNotNull(jsonRetorno);
	}

	@Test
	public void testUpdateWareHouseNoSuccess() throws Exception{

		uri = new URI("/api/v1/fresh-products/warehouse/update/1000");

		assertNotNull(uri);

		String requestJson =  "{\"idWareHouse\": 1,\"wareHouseName\": \"wareHouseName2\"}";

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.put(uri)
						.content(requestJson)
						.header("Content-Type", "application/json")
						.header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
				.andExpect(status().isNotFound()).andReturn();

		String responseJson = result.getResponse().getContentAsString();

		assertNotNull(responseJson);
	}
	
	@Test
	public void testUpdateWareHouseWithSuccess() throws Exception{

		uri = new URI("/api/v1/fresh-products/warehouse/update/1");

		assertNotNull(uri);

		String requestJson =  "{\"idWareHouse\": 1,\"wareHouseName\": \"wareHouseName2\"}";

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.put(uri)
						.content(requestJson)
						.header("Content-Type", "application/json")
						.header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
				.andExpect(status().isCreated()).andReturn();

		String responseJson = result.getResponse().getContentAsString();

		assertNotNull(responseJson);
	}
}
