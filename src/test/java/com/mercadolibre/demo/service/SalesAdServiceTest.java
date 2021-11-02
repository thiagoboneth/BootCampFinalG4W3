package com.mercadolibre.demo.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    void deveSalvarDTO() throws Exception {

        SalesAdDTO salesAdDTO = new SalesAdDTO();
        salesAdDTO.setVolume(30.0F);
        salesAdDTO.setMinimumTemperature(0F);
        salesAdDTO.setMaximumTemperature(22.0F);
        salesAdDTO.setPrice(1200.0D);
        salesAdDTO.setIdSeller(1L);
        salesAdDTO.setIdProduct(1L);

        List<Product> productList = new ArrayList<Product>();
        Product product = new Product();
        product.setId(1L);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");
        productList.add(product);

        List<Seller> sellerList = new ArrayList<Seller>();
        Seller seller = new Seller();
        seller.setIdseller(1L);
        seller.setName("Naruto");
        seller.setLastname("Uzumaki");
        sellerList.add(seller);
        SalesAd salesAd = new SalesAd();

        Mockito.when(mockSalesAdRepository.save(Mockito.any(SalesAd.class))).thenReturn(salesAd);
        Mockito.when(mockSellerRepository.findById(1L)).thenReturn(Optional.of(seller));
        Mockito.when(mockProductRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(mockSalesAdRepository.findById(1L)).thenReturn(Optional.of(salesAd));

        SalesAd salesAd1 = salesAdService.convertSalesAdDTO(salesAdDTO);

        mockSalesAdRepository.save(salesAdService.save(salesAdDTO));
        salesAd1.setId(1L);

        assertNotNull(salesAd1.getPrice());
        assertNotNull(salesAd1.getVolume());
        assertNotNull(salesAd1.getSeller());
        assertNotNull(salesAd1.getMaximumTemperature());

        assertEquals(30.0F, salesAd1.getVolume());
        assertEquals(0F, salesAd1.getMinimumTemperature());
        assertEquals(22.0F, salesAd1.getMaximumTemperature());
        assertEquals(1200.0D, salesAd1.getPrice());
        assertEquals(1L, salesAd1.getId());


    }
    @Test
    void deveObterSelller() throws Exception {

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

        Seller getSeller = salesAdService.obtemSeller(salesAdDTO).get();

        assertEquals("Naruto", getSeller.getName());
        assertEquals("Uzumaki", getSeller.getLastname());


    }
    @Test
    void deveObterProduct() throws Exception {

        SalesAdDTO salesAdDTO = new SalesAdDTO();
        salesAdDTO.setVolume(30.0F);
        salesAdDTO.setMinimumTemperature(0F);
        salesAdDTO.setMaximumTemperature(22.0F);
        salesAdDTO.setPrice(1200.0D);
        salesAdDTO.setIdSeller(1L);
        salesAdDTO.setIdProduct(1L);

        List<Product> productList = new ArrayList<Product>();
        Product product = new Product();
        product.setId(1L);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");
        productList.add(product);

        Mockito.when(mockProductRepository.findById(1L)).thenReturn(Optional.of(productList.stream().findAny().get()));

        Product getProduct = salesAdService.obtemProduct(salesAdDTO).get();

        assertEquals("Laranja Lima", getProduct.getName());
        assertEquals("Laranja azedinha rica em vitamina c", getProduct.getDescription());


    }
  }
