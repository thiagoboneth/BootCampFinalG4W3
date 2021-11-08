package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.dto.PurchaseOrderDTO;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.model.PurchaseOrder;
import com.mercadolibre.demo.repository.BatchStockRepository;
import com.mercadolibre.demo.repository.BuyerRepository;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import com.mercadolibre.demo.repository.SalesAdRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PurchaseOrderServiceTest {
    PurchaseOrderRepository mockPurchaseOrderRepository = Mockito.mock(PurchaseOrderRepository.class);
    BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
    SalesAdRepository mockSalesAdRepository = Mockito.mock(SalesAdRepository.class);
    BatchStockRepository mockBatchStockRepository = Mockito.mock(BatchStockRepository.class);
    PurchaseOrderService purchaseOrderService = new PurchaseOrderService(mockPurchaseOrderRepository,mockBuyerRepository,mockSalesAdRepository,mockBatchStockRepository);

    @Test
    void testSavePurchaseOrderWithSuccess() throws Exception {

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        List<ItemOfProductDTO> itemProductList = new ArrayList<>();

        Buyer buyer = new Buyer();
        buyer.setIdBuyer(1L);

        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setIdBuyer(1L);
        purchaseOrderDTO.setItemOfProduct(itemProductList);

        Mockito.when(mockBuyerRepository.findById(1L)).thenReturn(Optional.of(buyer));
        Mockito.when(mockPurchaseOrderRepository.save(Mockito.any(PurchaseOrder.class))).thenReturn(purchaseOrder);

        purchaseOrder = purchaseOrderService.convertPurchaseToDTO(purchaseOrderDTO);
        purchaseOrder.setId(1L);
        purchaseOrderService.save(purchaseOrderDTO);

        assertNotNull(purchaseOrder.getId());
        assertNotNull(purchaseOrder.getBuyer().getIdBuyer());

    }
}
