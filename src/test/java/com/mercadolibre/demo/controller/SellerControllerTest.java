package com.mercadolibre.demo.controller;

import static org.junit.Assert.assertNotNull;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureTestEntityManager
@SpringBootTest
@AutoConfigureMockMvc
public class SellerControllerTest {
	
	private URI uri;
	private MockHttpServletRequestBuilder request;
	private ResultMatcher expectedResult;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testListBuyerGetAll() throws Exception {
		
		uri = new URI("/api/v1/fresh-products/seller/list");
		
		assertNotNull(uri);

		request = MockMvcRequestBuilders.get(uri);
		expectedResult = MockMvcResultMatchers.status().isOk();

		String response = mockMvc.perform(request).andExpect(expectedResult).andReturn().getResponse()
				.getContentAsString();

		assertNotNull(response);
	}
	
	@Test
	public void testSaveBuyerWithSuccess() throws Exception {
		
		uri = new URI("/api/v1/fresh-products/seller/save");
		
		assertNotNull(uri);

		String json = "{\"name\": \"Ana Maria\", \"lastname\": \"Braga\"}";

		request = MockMvcRequestBuilders.post(uri).content(json).header("Content-Type", "application/json");
				
		expectedResult = MockMvcResultMatchers.status().isCreated();

		mockMvc.perform(request).andExpect(expectedResult);
		
	}
	
	@Test
	public void testUpdateBuyerWithSuccess() throws Exception {
		
		uri = new URI("/api/v1/fresh-products/seller/update/1");
		
		assertNotNull(uri);
		
		String json = "{\"name\": \"Mariana\", \"lastname\": \"Rios\"}";

		request = MockMvcRequestBuilders.put(uri).content(json).header("Content-Type", "application/json");
				
		expectedResult = MockMvcResultMatchers.status().isCreated();

		mockMvc.perform(request).andExpect(expectedResult);
		
	}
	
	@Test
	public void testSaveBuyerNoSuccesss() throws Exception {
		
		uri = new URI("/api/v1/fresh-products/seller/save");
		
		assertNotNull(uri);

		String json = "{\"name\": null, \"lastname\": \"\"}";

		request = MockMvcRequestBuilders.post(uri).content(json).header("Content-Type", "application/json");
				
		expectedResult = MockMvcResultMatchers.status().isBadRequest();

		mockMvc.perform(request).andExpect(expectedResult);
	}
	
	
	@Test
	public void testDeleteBuyer() throws Exception {
		
		uri = new URI("/api/v1/fresh-products/seller/delete/8");
		
		assertNotNull(uri);

		request = MockMvcRequestBuilders.delete(uri).header("Content-Type", "application/json");
				
		expectedResult = MockMvcResultMatchers.status().isOk();

		mockMvc.perform(request).andExpect(expectedResult);
	}

}
