package com.mercadolibre.demo.service;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.mercadolibre.demo.dto.SalesAdDTO;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.model.Seller;
import com.mercadolibre.demo.repository.ProductRepository;
import com.mercadolibre.demo.repository.SalesAdRepository;
import com.mercadolibre.demo.repository.SellerRepository;

public class SalesAdServiceTest {

    SalesAdRepository mockSalesAdRepository = Mockito.mock(SalesAdRepository.class);
    SellerRepository mockSellerRepository = Mockito.mock(SellerRepository.class);
    ProductRepository mockProductRepository = Mockito.mock(ProductRepository.class);
    SalesAdService salesAdService = new SalesAdService(mockSalesAdRepository,mockSellerRepository,mockProductRepository);

    @Test
    void testSaveSalesAdWithSuccess() throws Exception {

        SalesAdDTO salesAdDTO = new SalesAdDTO();
        salesAdDTO.setVolume(30.0F);
        salesAdDTO.setMinimumTemperature(0F);
        salesAdDTO.setMaximumTemperature(22.0F);
        salesAdDTO.setPrice(1200.0D);
        salesAdDTO.setIdSeller(1L);
        salesAdDTO.setIdProduct(1L);

        Product product = new Product();
        product.setId(1L);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");

        Seller seller = new Seller();
        seller.setIdseller(1L);
        seller.setName("Naruto");
        seller.setLastname("Uzumaki");
        
        SalesAd salesAd = new SalesAd();

        Mockito.when(mockSalesAdRepository.save(Mockito.any(SalesAd.class))).thenReturn(salesAd);
        Mockito.when(mockSellerRepository.findById(1L)).thenReturn(Optional.of(seller));
        Mockito.when(mockProductRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(mockSalesAdRepository.findById(1L)).thenReturn(Optional.of(salesAd));

        salesAd = salesAdService.convertSalesAdDTO(salesAdDTO);

        mockSalesAdRepository.save(salesAdService.save(salesAdDTO));
        salesAd.setId(1L);

        assertNotNull(salesAd.getPrice());
        assertNotNull(salesAd.getVolume());
        assertNotNull(salesAd.getSeller());
        assertNotNull(salesAd.getMaximumTemperature());

        assertEquals(30.0F, salesAd.getVolume());
        assertEquals(0F, salesAd.getMinimumTemperature());
        assertEquals(22.0F, salesAd.getMaximumTemperature());
        assertEquals(1200.0D, salesAd.getPrice());
        assertEquals(1L, salesAd.getId());
    }

    @Test
    void testGetSellerSuccessful() throws Exception {

        SalesAdDTO salesAdDTO = new SalesAdDTO();
        salesAdDTO.setVolume(30.0F);
        salesAdDTO.setMinimumTemperature(0F);
        salesAdDTO.setMaximumTemperature(22.0F);
        salesAdDTO.setPrice(1200.0D);
        salesAdDTO.setIdSeller(1L);
        salesAdDTO.setIdProduct(1L);

        List<Seller> sellerList = new ArrayList<Seller>();
        Seller seller = new Seller();
        seller.setIdseller(1L);
        seller.setName("Naruto");
        seller.setLastname("Uzumaki");
        sellerList.add(seller);

        Mockito.when(mockSellerRepository.findById(1L)).thenReturn(Optional.of(sellerList.stream().findAny().get()));
        salesAdService.getSeller(salesAdDTO);

        assertNotNull( salesAdService.getSeller(salesAdDTO));

        assertEquals("Naruto", seller.getName());
        assertEquals("Uzumaki", seller.getLastname());
    }
    
    @Test
    void testGetProductSuccessful() {

        SalesAdDTO salesAdDTO = new SalesAdDTO();
        salesAdDTO.setVolume(30.0F);
        salesAdDTO.setMinimumTemperature(0F);
        salesAdDTO.setMaximumTemperature(22.0F);
        salesAdDTO.setPrice(1200.0D);
        salesAdDTO.setIdSeller(1L);
        salesAdDTO.setIdProduct(1L);

        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");
        productList.add(product);


        Mockito.when(mockProductRepository.findById(1L)).thenReturn(Optional.of(productList.stream().findAny().get()));
        salesAdService.getSeller(salesAdDTO);

        assertNotNull( salesAdService.getProduct(salesAdDTO));

        assertEquals("Laranja Lima", product.getName());
        assertEquals("Laranja azedinha rica em vitamina c", product.getDescription());
    }
    @Test
    void testGetListSalesAd() {

        Product product = new Product();
        SalesAd salesAd = new SalesAd();
        Seller seller = new Seller();
        Product product2 = new Product();
        Seller seller2 = new Seller();

        List<SalesAd> salesAdList = new ArrayList<>();
        salesAd.setVolume(500.0F);
        salesAd.setMinimumTemperature(8F);
        salesAd.setMaximumTemperature(45.0F);
        salesAd.setPrice(1200.0D);
        salesAd.setId(1L);
        salesAd.setProduct(product);
        salesAd.setSeller(seller);
        salesAdList.add(salesAd);

        SalesAd salesAd2 = new SalesAd();
        salesAd2.setVolume(30.0F);
        salesAd2.setMinimumTemperature(0F);
        salesAd2.setMaximumTemperature(22.0F);
        salesAd2.setPrice(1200.0D);
        salesAd2.setId(1L);
        salesAd2.setProduct(product2);
        salesAd2.setSeller(seller2);
        salesAdList.add(salesAd2);

        when(mockSalesAdRepository.findAll()).thenReturn(salesAdList);
        List <SalesAd> listaObtida = mockSalesAdRepository.findAll();
		salesAdService.list();


        assertNotNull(listaObtida);
        assertTrue(listaObtida.contains(salesAd));
        assertTrue(listaObtida.contains(salesAd2));

        assertEquals(1200.0D, listaObtida.get(1).getPrice());
        assertEquals(500.0F, listaObtida.get(0).getVolume());
    }

    @Test
    void testUpdateSalesAdWithSuccess() throws Exception {

        Product product = new Product();
        product.setId(1L);
        SalesAd salesAd = new SalesAd();

        Seller seller = new Seller();
        seller.setIdseller(1l);

        List<SalesAd> salesAdList = new ArrayList<>();
        salesAd.setVolume(500.0F);
        salesAd.setMinimumTemperature(8F);
        salesAd.setMaximumTemperature(45.0F);
        salesAd.setPrice(1200.0D);
        salesAd.setId(1L);
        salesAd.setProduct(product);
        salesAd.setSeller(seller);
        salesAdList.add(salesAd);

        SalesAdDTO salesAdDTO = new SalesAdDTO();
        salesAdDTO.setVolume(30.0F);
        salesAdDTO.setMinimumTemperature(0F);
        salesAdDTO.setMaximumTemperature(22.0F);
        salesAdDTO.setPrice(1800.0D);
        salesAdDTO.setIdSeller(1L);
        salesAdDTO.setIdProduct(1L);

        Mockito.when(mockSellerRepository.findById(1L)).thenReturn(Optional.of(seller));
        Mockito.when(mockProductRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(mockSalesAdRepository.findById(1L)).thenReturn(Optional.of(salesAd));
        Mockito.when(mockSalesAdRepository.saveAndFlush(salesAd)).thenReturn(salesAd);

        salesAd = salesAdService.convertSalesAdDTO(salesAdDTO);
        salesAd.setId(1L);
        salesAdService.update(salesAdDTO,mockSalesAdRepository.findById(1L).get().getId());

        assertEquals(1800.00, salesAd.getPrice());
        assertEquals(22.0F, salesAd.getMaximumTemperature());

        assertNotNull(salesAd.getSeller().getIdseller());
        assertNotNull(salesAd.getProduct().getId());
    }
    
    @Test
    void testUpdateSalesAdtNoSuccess() throws Exception {

        Product product = new Product();
        product.setId(1L);

        Seller seller = new Seller();
        seller.setIdseller(1l);

        SalesAd salesAd = new SalesAd();
        salesAd.setVolume(500.0F);
        salesAd.setMinimumTemperature(8F);
        salesAd.setMaximumTemperature(45.0F);
        salesAd.setPrice(1200.0D);
        salesAd.setId(1L);
        salesAd.setProduct(product);
        salesAd.setSeller(seller);

        SalesAdDTO salesAdDTO = new SalesAdDTO();
        salesAdDTO.setVolume(30.0F);
        salesAdDTO.setMinimumTemperature(0F);
        salesAdDTO.setMaximumTemperature(22.0F);
        salesAdDTO.setPrice(1800.0D);
        salesAdDTO.setIdSeller(1L);
        salesAdDTO.setIdProduct(1L);

        Mockito.when(mockSellerRepository.findById(1L)).thenReturn(Optional.of(seller));
        Mockito.when(mockProductRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(mockSalesAdRepository.findById(1L)).thenReturn(Optional.of(salesAd));
        Mockito.when(mockSalesAdRepository.saveAndFlush(salesAd)).thenReturn(salesAd);

        salesAd = salesAdService.convertSalesAdDTO(salesAdDTO);
        salesAd.setId(1L);
        salesAdService.update(salesAdDTO,mockSalesAdRepository.findById(1L).get().getId());

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            salesAdService.update(salesAdDTO, 2L);
        });

        assertThat(exceptionThatWasThrown.getMessage(), equalTo("Id não cadastrado"));
    }
    
  }
