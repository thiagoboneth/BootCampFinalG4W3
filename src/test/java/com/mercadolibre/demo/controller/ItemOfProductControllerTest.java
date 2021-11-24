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
public class ItemOfProductControllerTest {

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
    public void testlistItemOfProduct() throws Exception {

        uri = new URI("/api/v1/fresh-products/itemOfProduct/list");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(uri).header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isOk()).andReturn();

        String jsonRetorno = result.getResponse().getContentAsString();

        assertNotNull(jsonRetorno);

    }

    @Test
    public void testlistProductOfBatchStock() throws Exception {

        uri = new URI("/api/v1/fresh-products/itemOfProduct/listProductOfBatchStock");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(uri).header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isOk()).andReturn();

        String jsonRetorno = result.getResponse().getContentAsString();

        assertNotNull(jsonRetorno);

    }

    @Test
    public void testlistOrderProduct() throws Exception {

        uri = new URI("/api/v1/fresh-products/itemOfProduct/listOrderProduct/F");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(uri).header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isOk()).andReturn();

        String jsonRetorno = result.getResponse().getContentAsString();

        assertNotNull(jsonRetorno);

    }

    @Test
    public void testlistProductForCar() throws Exception {

        uri = new URI("/api/v1/fresh-products/itemOfProduct/lisProductForCar/1");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(uri).header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isOk()).andReturn();

        String jsonRetorno = result.getResponse().getContentAsString();

        assertNotNull(jsonRetorno);

    }

    @Test
    public void testDeletePurchaseOrderNoSuccess() throws Exception{

        uri = new URI("/api/v1/fresh-products/itemOfProduct/delete/80");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete(uri)
                        .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isNotFound()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }

    @Test
    public void testDeletePurchaseOrderWithSuccess() throws Exception{

        uri = new URI("/api/v1/fresh-products/itemOfProduct/delete/1");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete(uri)
                        .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isOk()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }
}
