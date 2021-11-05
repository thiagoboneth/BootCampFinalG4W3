package com.mercadolibre.demo.controller;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
@AutoConfigureTestEntityManager
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
		
	private URI uri;
	private MockHttpServletRequestBuilder request;
	private ResultMatcher expectedResult;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	MockMvc mockMvc;
	
	//@Before
//	public void setUp() throws UnsupportedEncodingException, Exception {
//
//		String json = "{\"user\": \"Helena\", \"senha\": \"123\"}";
//		uri = new URI("/auth");
//		
//		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri)
//				.contentType(MediaType.APPLICATION_JSON).content(json);
//		
//		expectedResult = MockMvcResultMatchers.status().isOk();
//		
//		String response = mockMvc.perform(request)
//				.andExpect(expectedResult)
//				.andReturn().getResponse().getContentAsString();
//		
//		JSONObject data = new JSONObject(response);
//		String jwtToken = data.getString("token");
//		
//		request = MockMvcRequestBuilders.post(uri).content(json).header("Content-Type", "application/json")
//				.header("Authorization", "Bearer " + jwtToken);
//		
//		
//		expectedResult = MockMvcResultMatchers.status().isOk();
//
//		mockMvc.perform(request).andExpect(expectedResult);
//						
//	}
	
	@Test
	public void testListProductGetAll() throws Exception {
		
		uri = new URI("/api/v1/fresh-products/product/list");
		
		assertNotNull(uri);

		request = MockMvcRequestBuilders.get(uri);
		expectedResult = MockMvcResultMatchers.status().isOk();

		String response = mockMvc.perform(request).andExpect(expectedResult).andReturn().getResponse()
				.getContentAsString();

		assertNotNull(response);
		
	}
	
	@Test
	public void testSaveProductWithSuccess() throws Exception {
		
		uri = new URI("/api/v1/fresh-products/product/save");
		
		assertNotNull(uri);


		String json = "{\"name\": \"Abacate Breda\", \"description\": \"Abacate com casca verde vibrante e  sabor adocicado\"}";

		request = MockMvcRequestBuilders.post(uri).content(json).header("Content-Type", "application/json");
				
		expectedResult = MockMvcResultMatchers.status().isCreated();

		mockMvc.perform(request).andExpect(expectedResult);
		
	}
	
	@Test
	public void testSaveProductNoSuccesss() throws Exception {
		
		uri = new URI("/api/v1/fresh-products/product/save");
		
		assertNotNull(uri);


		String json = "{\"name\": null, \"description\": \"Abacate com casca verde vibrante e  sabor adocicado\"}";

		request = MockMvcRequestBuilders.post(uri).content(json).header("Content-Type", "application/json");
				
		expectedResult = MockMvcResultMatchers.status().isBadRequest();

		mockMvc.perform(request).andExpect(expectedResult);
	}
	
	@Test
	public void testUpdateProductWithSuccess() throws Exception {
		
		uri = new URI("/api/v1/fresh-products/product/update/1");
		
		assertNotNull(uri);


		String json = "{\"name\": \"Manga Carlotinha\", \"description\": \"Ótima para consumo in natura, ou na produção de sucos, geleias, doces e sorvetes\"}";

		request = MockMvcRequestBuilders.put(uri).content(json).header("Content-Type", "application/json");
				
		expectedResult = MockMvcResultMatchers.status().isCreated();

		mockMvc.perform(request).andExpect(expectedResult);
		
	}
	
	@Test
	public void testDeleteProduct() throws Exception {
		
		uri = new URI("/api/v1/fresh-products/product/delete/1");
		
		assertNotNull(uri);

		request = MockMvcRequestBuilders.delete(uri).header("Content-Type", "application/json");
				
		expectedResult = MockMvcResultMatchers.status().isOk();

		mockMvc.perform(request).andExpect(expectedResult);		
	}

}
