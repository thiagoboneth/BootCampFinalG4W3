package com.mercadolibre.demo.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.demo.dto.TokenDTO;

@Transactional
@AutoConfigureTestEntityManager
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

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
	public void testListProductGetAll() throws Exception {

		uri = new URI("/api/v1/fresh-products/product/list");

		assertNotNull(uri);

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get(uri).header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
				.andExpect(status().isOk()).andReturn();

		String jsonRetorno = result.getResponse().getContentAsString();

		assertNotNull(jsonRetorno);

	}

	  @Test
	  public void testSaveProductWithSuccess() throws Exception {
	  
	  uri = new URI("/api/v1/fresh-products/product/save");
	  
	  assertNotNull(uri);
	  
	  
	  String requestJson =  "{\"name\": \"Abacate Breda Teste Integracao Save\", \"description\": \"Abacate com casca verde vibrante e  sabor adocicado\"}";
	  
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
	  public void testUpdateProductWithSuccess() throws Exception {
		  
	  uri = new URI("/api/v1/fresh-products/product/update/1");
	  
	  assertNotNull(uri);
	  
	  
	  String requestJson =  "{\"name\": \"Abacate Breda Teste Integracao Update\", \"description\": \"Abacate com casca verde vibrante e  sabor adocicado\"}";
	  
	  MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.put(uri)
				.content(requestJson)
				.header("Content-Type", "application/json")
				.header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
				.andExpect(status().isCreated()).andReturn();

		String responseJson = result.getResponse().getContentAsString();

		assertNotNull(responseJson);
	  
	  }
	  
	  @Test
	  public void testSaveProductNoSuccesss() throws Exception {
		  
		  uri = new URI("/api/v1/fresh-products/product/save");
		  
		  assertNotNull(uri);
		  
		  String requestJson =  "{\"name\": \null \"description\": \"\"}";
		  
		  MvcResult result = mockMvc.perform(
					MockMvcRequestBuilders.post(uri)
					.content(requestJson)
					.header("Content-Type", "application/json")
					.header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
					.andExpect(status().isBadRequest()).andReturn();

			String responseJson = result.getResponse().getContentAsString();

			assertNotNull(responseJson);
	  }
	  
	  @Test
	  public void testDeleteProduct() throws Exception {
	  
		  uri = new URI("/api/v1/fresh-products/product/delete/1");
		  
		  assertNotNull(uri);

		  MvcResult result = mockMvc.perform(
					MockMvcRequestBuilders.delete(uri)
					.header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
					.andExpect(status().isOk()).andReturn();

			String responseJson = result.getResponse().getContentAsString();

			assertNotNull(responseJson);
	  }

	@Test
	public void testdueDate() throws Exception {

		uri = new URI("/api/v1/fresh-products/product/duedatelist/100/1");

		assertNotNull(uri);

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get(uri)
						.header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
				.andExpect(status().isOk()).andReturn();

		String responseJson = result.getResponse().getContentAsString();

		assertNotNull(responseJson);
	}

	@Test
	public void testdueDateCustom() throws Exception {

		uri = new URI("/api/v1/fresh-products/product/duedatelist/list/100/Refrigerado/asc");

		assertNotNull(uri);

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get(uri)
						.header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
				.andExpect(status().isOk()).andReturn();

		String responseJson = result.getResponse().getContentAsString();

		assertNotNull(responseJson);
	}
}
