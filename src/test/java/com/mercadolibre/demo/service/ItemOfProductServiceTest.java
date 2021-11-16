package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        ItemOfProduct itemOfProduct =  new ItemOfProduct();
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
}
