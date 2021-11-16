package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ItemOfProduct2DTO;
import com.mercadolibre.demo.dto.PurchaseOrderDTO;
import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.model.ItemOfProduct;
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
		assertEquals(itemProductList, purchaseOrder.getItemOfProduct());
	}

	@Test
	void testGetSalesAdWithSuccess() {
		
		ItemOfProduct2DTO itemOfProduct2DTO = new ItemOfProduct2DTO();
		itemOfProduct2DTO.setIdSalesAd(1L);
		itemOfProduct2DTO.setQuantity(1000L);
						
		Product product = new Product();
		product.setId(1L);
		product.setName("Sorvete de Cocô");
		product.setDescription("Sorvete produzido com Cocô do interior");

		List<Buyer> buyerList = new ArrayList<>();
		Buyer buyer = new Buyer();
		buyer.setIdBuyer(1L);
		buyer.setName("Dona");
		buyer.setLastName("Florinda");
		buyerList.add(buyer);
		
		List<SalesAd> salesAdList = new ArrayList<>();
		SalesAd salesAd = new SalesAd();
		salesAd.setId(1L);
		salesAd.setMaximumTemperature(20F);
		salesAd.setMinimumTemperature(0F);
		salesAd.setPrice(45D);
		salesAd.setProduct(product);
		salesAdList.add(salesAd);
		
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(1L);
		purchaseOrder.setOrderStatus(OrderStatus.CARRINHO);
		purchaseOrder.setDate(LocalDate.now());;
		purchaseOrder.setIdBuyer(buyer);
		
		Mockito.when(mockSalesAdRepository.findById(1L)).thenReturn(Optional.of(salesAdList.stream().findAny().get()));
		SalesAd getSalesAd = purchaseOrderService.getSalesAd(itemOfProduct2DTO).get();
		
		assertEquals(1L, getSalesAd.getId());
		assertEquals(20F, getSalesAd.getMaximumTemperature());
		assertEquals(0F, getSalesAd.getMinimumTemperature());
		assertEquals(45D, getSalesAd.getPrice());
		assertEquals(product, getSalesAd.getProduct());
		
		assertNotNull(getSalesAd.getId());
		assertNotNull(getSalesAd.getMaximumTemperature());
		assertNotNull(getSalesAd.getMinimumTemperature());
		assertNotNull(getSalesAd.getPrice());
		assertNotNull(getSalesAd.getProduct());
	}
	
	@Test
	void testGetBuyerWithSuccess() {
		
		List<ItemOfProduct2DTO> itemProductList = new ArrayList<>();
		
		PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
		purchaseOrderDTO.setIdBuyer(1L);
		purchaseOrderDTO.setItemOfProduct(itemProductList);

		List<Buyer> buyerList = new ArrayList<>();
		Buyer buyer = new Buyer();
		buyer.setIdBuyer(1L);
		buyer.setName("Ayrton");
		buyer.setLastName("Senna");
		buyerList.add(buyer);
		
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(1L);
		purchaseOrder.setOrderStatus(OrderStatus.CARRINHO);
		purchaseOrder.setDate(LocalDate.now());;
		purchaseOrder.setIdBuyer(buyer);
		
		Mockito.when(mockBuyerRepository.findById(1L)).thenReturn(Optional.of(buyerList.stream().findAny().get()));
		Buyer getBuyer = purchaseOrderService.getBuyer(purchaseOrderDTO).get();
		
		assertEquals(1L, getBuyer.getIdBuyer());
		assertEquals("Ayrton", getBuyer.getName());
		assertEquals("Senna", getBuyer.getLastName());
		
		assertNotNull(getBuyer.getIdBuyer());
		assertNotNull(getBuyer.getName());
		assertNotNull(getBuyer.getLastName());
	}
	
	@Test
	void testListPurchaseOrder() {
		
		List<Buyer> buyerList = new ArrayList<>();
		Buyer buyer = new Buyer();
		buyer.setIdBuyer(1L);
		buyer.setName("Rubens");
		buyer.setLastName("Barichello");
		buyerList.add(buyer);
		
		List<ItemOfProduct> itemProductList = new ArrayList<>();
		
		List<PurchaseOrder> purchaseList = new ArrayList<>();
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(1L);
		purchaseOrder.setDate(LocalDate.now());
		purchaseOrder.setOrderStatus(OrderStatus.CARRINHO);
		purchaseOrder.setIdBuyer(buyer);
		purchaseOrder.setItemOfProduct(itemProductList);
		purchaseList.add(purchaseOrder);
		
		Mockito.when(mockPurchaseOrderRepository.findAll()).thenReturn(purchaseList);
		purchaseOrderService.list();
		
		assertEquals(1L, purchaseOrder.getId());
		assertEquals(OrderStatus.CARRINHO, purchaseOrder.getOrderStatus());
		assertEquals(itemProductList, purchaseOrder.getItemOfProduct());
		
		assertNotNull(purchaseOrder.getId());
		assertNotNull(purchaseOrder.getDate());
		assertNotNull(purchaseOrder.getOrderStatus());
		assertNotNull(purchaseOrder.getIdBuyer());
		assertNotNull(purchaseOrder.getItemOfProduct());
	}
	
	@Test
	void testUpdatePurchaseOrderWithSuccess() throws Exception {
		
		List<Buyer> buyerList = new ArrayList<>();
		Buyer buyer = new Buyer();
		buyer.setIdBuyer(1L);
		buyer.setName("Fernanda");
		buyer.setLastName("Pereira");
		buyerList.add(buyer);
		
		List<ItemOfProduct> itemProductList = new ArrayList<>();
		List<ItemOfProduct2DTO> itemProductListDTO = new ArrayList<>();
		
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(1L);
		purchaseOrder.setDate(LocalDate.now());
		purchaseOrder.setOrderStatus(OrderStatus.CARRINHO);
		purchaseOrder.setIdBuyer(buyer);
		purchaseOrder.setItemOfProduct(itemProductList);
		
		PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
		purchaseOrderDTO.setIdBuyer(buyer.getIdBuyer());
		purchaseOrderDTO.setItemOfProduct(itemProductListDTO);
		
		Mockito.when(mockBuyerRepository.findById(1L)).thenReturn(Optional.of(buyer));
		Mockito.when(mockPurchaseOrderRepository.findById(1L)).thenReturn(Optional.of(purchaseOrder));
		Mockito.when(mockPurchaseOrderRepository.saveAndFlush(Mockito.any(PurchaseOrder.class))).thenReturn(purchaseOrder);
		
		purchaseOrderService.update(purchaseOrderDTO, purchaseOrder.getId());
		purchaseOrder.setId(1L);
		
		assertEquals(1L, purchaseOrder.getId());
		assertEquals(OrderStatus.CARRINHO, purchaseOrder.getOrderStatus());
		assertEquals(itemProductList, purchaseOrder.getItemOfProduct());
		
		assertNotNull(purchaseOrder.getId());
		assertNotNull(purchaseOrder.getDate());
		assertNotNull(purchaseOrder.getOrderStatus());
		assertNotNull(purchaseOrder.getIdBuyer());
		assertNotNull(purchaseOrder.getItemOfProduct());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}