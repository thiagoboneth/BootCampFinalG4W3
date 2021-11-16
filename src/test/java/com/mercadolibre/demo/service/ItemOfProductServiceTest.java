package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ItemOfProductServiceTest {

    ItemOfProductRepository mockItemOfProductRepository = Mockito.mock(ItemOfProductRepository.class);
    SalesAdRepository mockSalesAdRepository = Mockito.mock(SalesAdRepository.class);
    PurchaseOrderRepository mockPurchaseOrderRepository = Mockito.mock(PurchaseOrderRepository.class);
    BatchStockRepository mockBatchStockRepository = Mockito.mock(BatchStockRepository.class);
    InboundOrderRepository mockInboundOrderRepository = Mockito.mock(InboundOrderRepository.class);

    ItemOfProductService itemOfProductService = new ItemOfProductService(
            mockItemOfProductRepository, mockSalesAdRepository, mockPurchaseOrderRepository,
            mockBatchStockRepository, mockInboundOrderRepository);


    @Test
    void testSaveItenOfProduct() throws Exception {

        ItemOfProductDTO itemOfProductDTO = new ItemOfProductDTO();
        itemOfProductDTO.setIdSalesAd(1L);
        itemOfProductDTO.setNameProduct("Laranja");
        itemOfProductDTO.setQuantity(5000L);

        ItemOfProduct itemOfProduct = new ItemOfProduct();
        SalesAd salesAd = new SalesAd();
        salesAd.setId(1L);

        Mockito.when(mockSalesAdRepository.findById(1L)).thenReturn(Optional.of(salesAd));
        Mockito.when(mockItemOfProductRepository.save(Mockito.any(ItemOfProduct.class))).thenReturn(itemOfProduct);

        itemOfProduct = itemOfProductService.convertItemOfProduct(itemOfProductDTO);
        mockItemOfProductRepository.save(itemOfProductService.save(itemOfProductDTO));
        itemOfProduct.setId(1L);

        assertEquals(5000L, itemOfProduct.getQuantity());
        assertEquals(1L, itemOfProduct.getId());

        assertNotNull(itemOfProduct.getId());
        assertNotNull(itemOfProduct.getQuantity());
        assertNotNull(itemOfProduct.getSalesAd());
        assertNotNull(itemOfProduct.getSalesAd().getId());
    }

    @Test
    void testListItemOfProduct() {

        List<ItemOfProduct> itemOfProducts = new ArrayList<>();

        ItemOfProduct itemOfProduct = new ItemOfProduct();
        itemOfProduct.setId(1L);
        itemOfProducts.add(itemOfProduct);

        ItemOfProduct itemOfProduct2 = new ItemOfProduct();
        itemOfProduct2.setId(2L);
        itemOfProducts.add(itemOfProduct2);

        ItemOfProduct itemOfProduct3 = new ItemOfProduct();
        itemOfProduct3.setId(1L);
        itemOfProducts.add(itemOfProduct3);

        when(mockItemOfProductRepository.findAll()).thenReturn(itemOfProducts);
        Mockito.when(mockItemOfProductRepository.orderOfItem(1L)).thenReturn(itemOfProducts);
        List<ItemOfProduct> listaObtida = mockItemOfProductRepository.orderOfItem(1L);
        itemOfProductService.list(2L);

        assertNotNull(listaObtida);
        assertTrue(listaObtida.contains(itemOfProduct));
        assertTrue(listaObtida.contains(itemOfProduct3));

        assertEquals(1L, listaObtida.get(0).getId());
        assertEquals(1L, listaObtida.get(2).getId());
    }
    @Test
    void testConvertItemOfProductDTO(){
        Product product = new Product();
        product.setId(1L);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");

        Seller seller = new Seller();
        seller.setIdseller(1l);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(1L);

        SalesAd salesAd = new SalesAd();
        salesAd.setVolume(500.0F);
        salesAd.setMinimumTemperature(8F);
        salesAd.setMaximumTemperature(45.0F);
        salesAd.setPrice(1200.0D);
        salesAd.setId(1L);
        salesAd.setProduct(product);
        salesAd.setSeller(seller);

        SalesAd salesAd2 = new SalesAd();
        salesAd2.setVolume(500.0F);
        salesAd2.setMinimumTemperature(8F);
        salesAd2.setMaximumTemperature(45.0F);
        salesAd2.setPrice(1200.0D);
        salesAd2.setId(2L);
        salesAd2.setProduct(product);
        salesAd2.setSeller(seller);

        SalesAd salesAd3 = new SalesAd();
        salesAd3.setVolume(500.0F);
        salesAd3.setMinimumTemperature(8F);
        salesAd3.setMaximumTemperature(45.0F);
        salesAd3.setPrice(1200.0D);
        salesAd3.setId(3L);
        salesAd3.setProduct(product);
        salesAd3.setSeller(seller);

        List<ItemOfProduct> itemOfProducts = new ArrayList<>();

        ItemOfProduct itemOfProduct = new ItemOfProduct(3200L,salesAd,purchaseOrder);
        ItemOfProduct itemOfProduct2 = new ItemOfProduct(500L,salesAd2,purchaseOrder);
        ItemOfProduct itemOfProduct3 = new ItemOfProduct(1100L,salesAd3,purchaseOrder);

        itemOfProducts.add(itemOfProduct);
        itemOfProducts.add(itemOfProduct2);
        itemOfProducts.add(itemOfProduct3);

        List<ItemOfProductDTO> itemOfProductDTOList =
                itemOfProductService.convertItemOfProductDTO(itemOfProducts);

        assertNotNull(itemOfProductDTOList);
        assertEquals(1100L, itemOfProductDTOList.get(2).getQuantity());
        assertEquals(3L, itemOfProductDTOList.get(2).getIdSalesAd());

    }
    @Test
    void testIncrementQuantity() throws Exception {
        ItemOfProduct item = new ItemOfProduct();
        item.setQuantity(200L);

        BatchStock batchStock = new BatchStock();
        batchStock.setCurrentQuantity(5000L);

        List<BatchStock> batchStockList = new ArrayList<>();

        batchStockList.add(batchStock);

        itemOfProductService.incrementQuantity(item,batchStockList);

        assertEquals(5200L,batchStockList.get(0).getCurrentQuantity());

    }

}
