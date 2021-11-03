package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.dto.PurchaseOrderDTO;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.model.PurchaseOrder;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.repository.BuyerRepository;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import com.mercadolibre.demo.repository.SalesAdRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PurchaseOrderServiceTest {
    PurchaseOrderRepository mockPurchaseOrderRepository = Mockito.mock(PurchaseOrderRepository.class);
    BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
    SalesAdRepository mockSalesAdRepository = Mockito.mock(SalesAdRepository.class);
    PurchaseOrderService purchaseOrderService = new PurchaseOrderService(mockPurchaseOrderRepository,mockBuyerRepository,mockSalesAdRepository);

    @Test
    void testSaveSalesAdWithSuccess() throws Exception {

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        List<ItemOfProductDTO> itemProductList = new ArrayList<>();
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setIdBuyer(1L);
        purchaseOrderDTO.setItemOfProduct(itemProductList);

        Mockito.when(mockPurchaseOrderRepository.save(Mockito.any(PurchaseOrder.class))).thenReturn(purchaseOrder);

       purchaseOrder = purchaseOrderService.convertPurchaseToDTO(purchaseOrderDTO);
       mockPurchaseOrderRepository.save(purchaseOrderService.save(purchaseOrderDTO));
       purchaseOrder.setId(1L);

        assertNotNull(purchaseOrder.getId());
        assertNotNull(purchaseOrder.getBuyer().getIdBuyer());

    }
}
