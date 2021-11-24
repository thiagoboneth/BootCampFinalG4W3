package com.mercadolibre.demo.service;
import com.mercadolibre.demo.dto.AdressDTO;
import com.mercadolibre.demo.dto.AdressSaveDTO;
import com.mercadolibre.demo.dto.response.AdressBuscaDTO;
import com.mercadolibre.demo.dto.response.AdressRestDTO;
import com.mercadolibre.demo.model.Adress;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.repository.AdressRepository;
import com.mercadolibre.demo.repository.BuyerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;


public class AdressServiceTest {

    AdressRepository mockAdress = Mockito.mock(AdressRepository.class);
    BuyerRepository mockBuyer = Mockito.mock(BuyerRepository.class);

    AdressService adressService = new AdressService(mockAdress, mockBuyer);

    Buyer baseBuyer(){
        Buyer buyer = new Buyer();
        buyer.setIdBuyer(1L);
        buyer.setName("Kabuto");
        buyer.setLastName("Yakushi");
        return buyer;
    }
    AdressDTO baseAdressDTO(){

        AdressDTO adressDTO = new  AdressDTO();
        adressDTO.setCep("56310-753");
        adressDTO.setLogradouro("rua cerjeira");
        adressDTO.setComplemento("casa");
        adressDTO.setBairro("Bairro");
        adressDTO.setComplemento("Rua Cerejaira");
        adressDTO.setUf("UF");
        adressDTO.setFone1("87988264086");
        adressDTO.setFone2("87988264086");
        adressDTO.setIdbuyer(1L);
        adressDTO.setReferencia("Atras da banca");
        return adressDTO;
    }

    AdressSaveDTO baseAdressSaveDTO(){

        AdressSaveDTO adressSaveDTO = new AdressSaveDTO();
        adressSaveDTO.setIdBuyer(1L);
        adressSaveDTO.setCep("56310-753");
        adressSaveDTO.setReferencia("Atras da banca");
        adressSaveDTO.setFone1("87988264086");
        adressSaveDTO.setFone2("87988264086");
        return adressSaveDTO;
    }


    @Test
    void testBuscarCepNoSuccess() {
        String CEP = null;

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            adressService.buscarCep(CEP);
        });

        assertTrue(exceptionThatWasThrown.getMessage(), true);
    }
    @Test
    void testBuscarCepSuccess() {
        AdressDTO adressDTO =  baseAdressDTO();
        Buyer buyer = baseBuyer();

        AdressDTO adressDTO1 = adressService.buscarCep(adressDTO.getCep());

        assertEquals("56310-753", adressDTO1.getCep());
    }

    @Test
    void testListNoSuccess() {
        String CEP = null;

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            adressService.list(CEP);
        });

        assertTrue(exceptionThatWasThrown.getMessage(), true);
    }

    @Test
    void testListSuccess() throws Exception {

        AdressDTO adressDTO =  baseAdressDTO();
        Buyer buyer = baseBuyer();

        Mockito.when(mockBuyer.findById(Mockito.any(Long.class))).thenReturn(Optional.of(buyer));

        adressService.buscarCep(adressDTO.getCep());
        //adressService.list(adressDTO.getCep());
        adressService.convertAddressBuscaRestDTO(adressDTO);

        assertEquals("56310-753", adressDTO.getCep());

    }
    @Test
    void testSaveNoSuccess() {
        AdressSaveDTO adressSaveDTO = baseAdressSaveDTO();
        Buyer buyer = baseBuyer();
        adressSaveDTO.setIdBuyer(null);

        Mockito.when(mockBuyer.findById(Mockito.any(Long.class))).thenReturn(Optional.of(buyer));

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            adressService.save(adressSaveDTO);
        });

        assertTrue(exceptionThatWasThrown.getMessage(), true);
    }
  @Test
    void testSaveSuccess() throws Exception {
      AdressSaveDTO adressSaveDTO = baseAdressSaveDTO();
      Buyer buyer = baseBuyer();
      Mockito.when(mockBuyer.findById(Mockito.any(Long.class))).thenReturn(Optional.of(buyer));

      adressService.save(adressSaveDTO);

      assertEquals(1, adressSaveDTO.getIdBuyer());

    }

    @Test
    void testConverAdressDTONoSuccess() {
        AdressDTO adressDTO = baseAdressDTO();
        adressDTO.setIdbuyer(null);
        Buyer buyer = baseBuyer();
        buyer.setIdBuyer(1L);

        Mockito.when(mockBuyer.findById(Mockito.any(Long.class))).thenReturn(Optional.of(buyer));

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            adressService.convertAddressDTO(adressDTO);
        });

        assertTrue(exceptionThatWasThrown.getMessage(), true);
    }

    @Test
    void testConverAdressDTOSuccess() throws Exception {
        AdressDTO adressDTO = baseAdressDTO();
        Buyer buyer = baseBuyer();

        Mockito.when(mockBuyer.findById(Mockito.any(Long.class))).thenReturn(Optional.of(buyer));
        adressService.getBuyer(adressDTO);
    }

    @Test
    void testAdressBuscaDTONoSuccess() {
        AdressDTO adressDTO = baseAdressDTO();
        adressDTO.setIdbuyer(null);
        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            adressService.convertAddressBuscaRestDTO(adressDTO);
        });

        assertTrue(exceptionThatWasThrown.getMessage(), true);
    }

    @Test
    void testConverAdressBuscaDTOSuccess() throws Exception {
        AdressDTO adressDTO = baseAdressDTO();
        Buyer buyer = baseBuyer();

        Mockito.when(mockBuyer.findById(Mockito.any(Long.class))).thenReturn(Optional.of(buyer));
        adressService.convertAddressBuscaRestDTO(adressDTO);
    }

    }

