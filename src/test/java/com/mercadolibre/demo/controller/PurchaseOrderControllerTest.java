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
public class PurchaseOrderControllerTest {

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
    public void testaddItem() throws Exception {

        uri = new URI("/api/v1/fresh-products/purchaseorder/add");

        assertNotNull(uri);

        String requestJson =  "{\"idBuyer\": 1,\"itemOfProduct\": [{\"idSalesAd\": 2,\"quantity\": 10},{\"idSalesAd\": 1,\"quantity\": 10}]}";

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(requestJson)
                        .header("Content-Type", "application/json")
                        .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isCreated()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }
}
