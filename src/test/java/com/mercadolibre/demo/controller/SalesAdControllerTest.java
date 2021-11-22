package com.mercadolibre.demo.controller;


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
import java.net.URI;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureTestEntityManager
@SpringBootTest
@AutoConfigureMockMvc
public class SalesAdControllerTest {

    private URI uri;

    @Autowired
    MockMvc mockMvc;

    private TokenDTO tokenDTO;

    @BeforeEach
    public void testandoAutenticacao() throws Exception {
        String json = "{\"user\": \"filipe\", \"senha\": \"123\"}";
        uri = new URI("/auth");

        MvcResult resultContendoToken = mockMvc
                .perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andReturn();
        tokenDTO = new ObjectMapper().readValue(resultContendoToken.getResponse().getContentAsString(), TokenDTO.class);
    }

    @Test
    public void testsaveSalesAdNoSuccess() throws Exception {

        uri = new URI("/api/v1/fresh-products/salesad/save");

        assertNotNull(uri);

        String requestJson =  "{\"volume\": 50,\n" +
                "    \"minimumTemperature\": 1,\n" +
                "    \"maximumTemperature\": 10,\n" +
                "    \"price\": 80,\n" +
                "    \"idSeller\":1,\n" +
                "    \"idProduct\":1000}";

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(requestJson)
                        .header("Content-Type", "application/json")
                        .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isNotFound()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }
    
    @Test
    public void testsaveSalesAdWithSuccess() throws Exception {

        uri = new URI("/api/v1/fresh-products/salesad/save");

        assertNotNull(uri);

        String requestJson =  "{\"volume\": 50,\n" +
                "    \"minimumTemperature\": 1,\n" +
                "    \"maximumTemperature\": 10,\n" +
                "    \"price\": 80,\n" +
                "    \"idSeller\":1,\n" +
                "    \"idProduct\":1}";

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
    public void testlistSalesAd() throws Exception {

        uri = new URI("/api/v1/fresh-products/salesad/list");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(uri).header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isOk()).andReturn();

        String jsonRetorno = result.getResponse().getContentAsString();

        assertNotNull(jsonRetorno);

    }
    
    @Test
    public void testUpdateSalesAdNoSuccess() throws Exception{

        uri = new URI("/api/v1/fresh-products/salesad/update/1000");

        assertNotNull(uri);

        String requestJson =  "{\"volume\": 50,\n" +
                "    \"minimumTemperature\": 1,\n" +
                "    \"maximumTemperature\": 10,\n" +
                "    \"price\": 80,\n" +
                "    \"idSeller\":1,\n" +
                "    \"idProduct\":1}";

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
    public void testUpdateSalesAdWithSuccess() throws Exception{

        uri = new URI("/api/v1/fresh-products/salesad/update/1");

        assertNotNull(uri);

        String requestJson =  "{\"volume\": 50,\n" +
                "    \"minimumTemperature\": 1,\n" +
                "    \"maximumTemperature\": 10,\n" +
                "    \"price\": 80,\n" +
                "    \"idSeller\":1,\n" +
                "    \"idProduct\":1}";

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
