package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ItemOfProduct2DTO;
import com.mercadolibre.demo.dto.PurchaseOrderDTO;
import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.model.OrderStatus;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.model.PurchaseOrder;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.repository.BatchStockRepository;
import com.mercadolibre.demo.repository.BuyerRepository;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import com.mercadolibre.demo.repository.SalesAdRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PurchaseOrderServiceTest {

	PurchaseOrderRepository mockPurchaseOrderRepository = Mockito.mock(PurchaseOrderRepository.class);
	BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
	SalesAdRepository mockSalesAdRepository= Mockito.mock(SalesAdRepository.class);
	BatchStockRepository mockBatchStockRepository = Mockito.mock(BatchStockRepository.class);

	PurchaseOrderService purchaseOrderService = new PurchaseOrderService(
			mockPurchaseOrderRepository, mockBuyerRepository, mockSalesAdRepository, mockBatchStockRepository);

	@Test
	void testSavePurchaseOrderWithSuccess() throws Exception {

		Product product = new Product();
		product.setId(1L);
		product.setName("Abacaxi Havaino");
		product.setDescription("Abacaxi improtado do Hawai, ótimo para sucos e doces");

		Buyer buyer = new Buyer();
		buyer.setIdBuyer(1L);
		buyer.setName("Dona");
		buyer.setLastName("Florinda");

		SalesAd salesAd = new SalesAd();
		salesAd.setId(1L);
		salesAd.setMaximumTemperature(20F);
		salesAd.setMinimumTemperature(0F);
		salesAd.setPrice(45D);
		salesAd.setProduct(product);

		BatchStock batchStock = new BatchStock();
		batchStock.setIdBatchNumber(1L);
		batchStock.setCurrentQuantity(1000L);
		batchStock.setCurrentTemperature(5F);
		batchStock.setDueDate(LocalDate.now());
		batchStock.setInitialQuantity(1000L);
		batchStock.setManufacturingDate(LocalDate.now());
		batchStock.setManufacturingTime(LocalDateTime.now());
		batchStock.setIdSalesAd(salesAd);

		List<ItemOfProduct2DTO> itemProductList = new ArrayList<>();

		PurchaseOrderDTO dto = new PurchaseOrderDTO();
		dto.setIdBuyer(buyer.getIdBuyer());
		dto.setItemOfProduct(itemProductList);

		PurchaseOrder purchaseOrder = new PurchaseOrder();

		Mockito.when(mockBuyerRepository.findById(1L)).thenReturn(Optional.of(buyer));
		Mockito.when(mockSalesAdRepository.findById(1L)).thenReturn(Optional.of(salesAd));
		Mockito.when(mockBatchStockRepository.findById(1L)).thenReturn(Optional.of(batchStock));
		Mockito.when(mockPurchaseOrderRepository.save(Mockito.any(PurchaseOrder.class))).thenReturn(purchaseOrder);

		purchaseOrder = purchaseOrderService.convertPurchaseToDTO(dto);
		purchaseOrder.setId(1L);
		purchaseOrder.setOrderStatus(OrderStatus.CARRINHO);
		purchaseOrderService.save(dto);

		assertNotNull(purchaseOrder.getId());
		assertNotNull(purchaseOrder.getIdBuyer().getIdBuyer());
		assertNotNull(purchaseOrder.getOrderStatus());
		assertNotNull(purchaseOrder.getItemOfProduct());
		
		assertEquals(1L, purchaseOrder.getId());
		assertEquals(1L, purchaseOrder.getIdBuyer().getIdBuyer());
		assertEquals(OrderStatus.CARRINHO, purchaseOrder.getOrderStatus());
		assertEquals(OrderStatus.CARRINHO, purchaseOrder.getOrderStatus());
		assertEquals(OrderStatus.CARRINHO, purchaseOrder.getOrderStatus());
		assertEquals(itemProductList, purchaseOrder.getItemOfProduct());
	}
}