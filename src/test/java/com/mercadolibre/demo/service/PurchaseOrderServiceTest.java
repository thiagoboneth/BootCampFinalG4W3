package com.mercadolibre.demo.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mercadolibre.demo.dto.ItemOfProduct2DTO;
import com.mercadolibre.demo.dto.ItemOfProductDTO;
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

		Mockito.when(mockBuyerRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(buyer));
		Mockito.when(mockSalesAdRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(salesAd));
		Mockito.when(mockBatchStockRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(batchStock));
		Mockito.when(mockPurchaseOrderRepository.save(Mockito.any(PurchaseOrder.class))).thenReturn(purchaseOrder);

		purchaseOrder = purchaseOrderService.convertPurchaseToDTO(dto);
		purchaseOrder.setId(1L);
		purchaseOrder.setOrderStatus(OrderStatus.CARRINHO);
		purchaseOrderService.convertItemOfProduct(itemProductList, purchaseOrder);

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
	void testFindByIdWithSuccess() {
		
		Product product = new Product();
		product.setId(1L);
		product.setName("Fíle de Merluza");
		product.setDescription("Excelente peixe para obter uma alimentacao saudável");
		
		List<SalesAd> salesAdList = new ArrayList<>();
		SalesAd salesAd = new SalesAd();
		salesAd.setId(1L);
		salesAd.setMaximumTemperature(-5F);
		salesAd.setMinimumTemperature(0F);
		salesAd.setPrice(28D);
		salesAd.setProduct(product);
		salesAdList.add(salesAd);
		
		List<ItemOfProduct> itemProductList = new ArrayList<>();
		
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(1L);
		purchaseOrder.setOrderStatus(OrderStatus.CARRINHO);
		purchaseOrder.setDate(LocalDate.now());;
		purchaseOrder.setIdBuyer(null);
		purchaseOrder.setItemOfProduct(itemProductList);
		
		ItemOfProduct itemOfProduct = new ItemOfProduct();
		itemOfProduct.setId(1L);
		itemOfProduct.setQuantity(1000L);
		itemOfProduct.setPurchaseOrder(purchaseOrder);
		itemOfProduct.setSalesAd(salesAd);
		itemProductList.add(itemOfProduct);
		
		Mockito.when(mockPurchaseOrderRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(purchaseOrder));
		purchaseOrderService.findById(purchaseOrder.getId());
		
		assertNotNull(purchaseOrder.getId());
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

		Mockito.when(mockSalesAdRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(salesAdList.stream().findAny().get()));
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
	void testConvertItemOfProductNoSuccess() {

		Buyer buyer = new Buyer();
		buyer.setIdBuyer(1L);
		buyer.setName("Ayrton");
		buyer.setLastName("Senna");

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(1L);
		purchaseOrder.setOrderStatus(OrderStatus.CARRINHO);
		purchaseOrder.setDate(LocalDate.now());;
		purchaseOrder.setIdBuyer(buyer);


		List<ItemOfProduct2DTO> list = new ArrayList<>();
		ItemOfProduct2DTO itemOfProduct2DTO = new ItemOfProduct2DTO();
		itemOfProduct2DTO.setIdSalesAd(null);
		itemOfProduct2DTO.setQuantity(null);
		list.add(itemOfProduct2DTO);

		Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
			purchaseOrderService.convertItemOfProduct(list, purchaseOrder);

		});

		assertTrue(exceptionThatWasThrown.getMessage(), true);
	}
	
	@Test
	void testConvertItemOfProductWithSuccess() throws Exception {

		Buyer buyer = new Buyer();
		buyer.setIdBuyer(1L);
		buyer.setName("Ayrton");
		buyer.setLastName("Senna");

		Product product = new Product();
		product.setId(1L);
		product.setName("Contra filé");
		product.setDescription("Carne mácia");

		List<SalesAd> salesAdList = new ArrayList<>();
		SalesAd salesAd = new SalesAd();
		salesAd.setId(1L);
		salesAd.setMaximumTemperature(20F);
		salesAd.setMinimumTemperature(0F);
		salesAd.setPrice(45D);
		salesAd.setProduct(product);
		salesAdList.add(salesAd);

		List<ItemOfProduct> itemProductList = new ArrayList<>();

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(1L);
		purchaseOrder.setOrderStatus(OrderStatus.CARRINHO);
		purchaseOrder.setDate(LocalDate.now());;
		purchaseOrder.setIdBuyer(buyer);
		purchaseOrder.setItemOfProduct(itemProductList);

		ItemOfProduct itemOfProduct = new ItemOfProduct();
		itemOfProduct.setId(1L);
		itemOfProduct.setQuantity(3000L);
		itemOfProduct.setPurchaseOrder(purchaseOrder);
		itemOfProduct.setSalesAd(salesAd);
		itemProductList.add(itemOfProduct);

		List<ItemOfProduct2DTO> listDto = new ArrayList<>();
		ItemOfProduct2DTO itemOfProduct2DTO = new ItemOfProduct2DTO();
		itemOfProduct2DTO.setIdSalesAd(salesAd.getId());
		itemOfProduct2DTO.setQuantity(3000L);
		listDto.add(itemOfProduct2DTO);

		Mockito.when(mockSalesAdRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(salesAd));
		Mockito.when(mockPurchaseOrderRepository.save(Mockito.any(PurchaseOrder.class))).thenReturn(purchaseOrder);
		purchaseOrderService.convertItemOfProduct(listDto, purchaseOrder);

		assertNotNull(itemOfProduct2DTO);
	}

	@Test
	void testConvertPurchaseToDTONoSuccess() {

		Product product = new Product();
		product.setId(1L);
		product.setName("Melancia Híbrida Combat");
		product.setDescription("Melancia redonda, de boa textura, muito firme e ótima para producao de sucos");

		Buyer buyer = new Buyer();
		buyer.setIdBuyer(1L);
		buyer.setName("Ayrton");
		buyer.setLastName("Senna");

		SalesAd salesAd = new SalesAd();
		salesAd.setId(1L);
		salesAd.setMaximumTemperature(20F);
		salesAd.setMinimumTemperature(0F);
		salesAd.setPrice(45D);
		salesAd.setProduct(product);

		List<ItemOfProduct> itemProductList = new ArrayList<>();

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(1L);
		purchaseOrder.setOrderStatus(OrderStatus.CARRINHO);
		purchaseOrder.setDate(LocalDate.now());;
		purchaseOrder.setIdBuyer(null);
		purchaseOrder.setItemOfProduct(itemProductList);

		ItemOfProduct itemOfProduct = new ItemOfProduct();
		itemOfProduct.setId(1L);
		itemOfProduct.setQuantity(1000L);
		itemOfProduct.setPurchaseOrder(purchaseOrder);
		itemOfProduct.setSalesAd(salesAd);
		itemProductList.add(itemOfProduct);

		List<ItemOfProduct2DTO> itemOfProductDTOList = new ArrayList<>();
		ItemOfProduct2DTO itemOfProduct2DTO = new ItemOfProduct2DTO();
		itemOfProduct2DTO.setIdSalesAd(salesAd.getId());
		itemOfProduct2DTO.setQuantity(1000L);
		itemOfProductDTOList.add(itemOfProduct2DTO);

		PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
		purchaseOrderDTO.setIdBuyer(null);
		purchaseOrderDTO.setItemOfProduct(itemOfProductDTOList);


		ItemOfProductDTO itemOfProductDTO = new ItemOfProductDTO();
		itemOfProductDTO.setIdSalesAd(salesAd.getId());
		itemOfProductDTO.setQuantity(1000L);

		Mockito.when(mockBuyerRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(buyer));
		

		Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
			purchaseOrderService.convertPurchaseToDTO(purchaseOrderDTO);
		});
		
		assertEquals("Erro no carrinho", exceptionThatWasThrown.getMessage());
		assertTrue(exceptionThatWasThrown.getMessage(), true);
	}
	
	@Test
	void testPriceList() throws Exception {

		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setId(1L);
		product.setName("Sorvete de Cocô");
		product.setDescription("Sorvete produzido com Cocô do interior");
		productList.add(product);

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

		List<ItemOfProduct> itemProductList = new ArrayList<>();

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(1L);
		purchaseOrder.setDate(LocalDate.now());
		purchaseOrder.setOrderStatus(OrderStatus.CARRINHO);
		purchaseOrder.setIdBuyer(buyer);
		purchaseOrder.setItemOfProduct(itemProductList);

		ItemOfProduct itemOfProduct = new ItemOfProduct();
		itemOfProduct.setId(1L);
		itemOfProduct.setQuantity(100L);
		itemOfProduct.setSalesAd(salesAd);
		itemOfProduct.setPurchaseOrder(purchaseOrder);
		itemProductList.add(itemOfProduct);

		List<BatchStock> batchStockList = new ArrayList<>();
		BatchStock batchStock = new BatchStock();
		batchStock.setIdBatchNumber(1L);
		batchStock.setCurrentQuantity(1000L);
		batchStock.setCurrentTemperature(5F);
		batchStock.setDueDate(LocalDate.now());
		batchStock.setInitialQuantity(1000L);
		batchStock.setManufacturingDate(LocalDate.now());
		batchStock.setManufacturingTime(LocalDateTime.now());
		batchStock.setIdSalesAd(salesAd);
		batchStockList.add(batchStock);

		Mockito.when(mockBatchStockRepository.batchStockList(salesAd.getId())).thenReturn(batchStockList);
		purchaseOrderService.priceList(itemProductList);

	}

	@Test
	void testDecrementQuantity() throws Exception {

		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setId(1L);
		product.setName("Sorvete de Cocô");
		product.setDescription("Sorvete produzido com Cocô do interior");
		productList.add(product);

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

		List<ItemOfProduct> itemProductList = new ArrayList<>();

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(1L);
		purchaseOrder.setDate(LocalDate.now());
		purchaseOrder.setOrderStatus(OrderStatus.CARRINHO);
		purchaseOrder.setIdBuyer(buyer);
		purchaseOrder.setItemOfProduct(itemProductList);

		ItemOfProduct itemOfProduct = new ItemOfProduct();
		itemOfProduct.setId(1L);
		itemOfProduct.setQuantity(100L);
		itemOfProduct.setSalesAd(salesAd);
		itemOfProduct.setPurchaseOrder(purchaseOrder);
		itemProductList.add(itemOfProduct);
		
		ItemOfProduct itemOfProduct2 = new ItemOfProduct();
		itemOfProduct2.setId(1L);
		itemOfProduct2.setQuantity(100L);
		itemOfProduct2.setSalesAd(salesAd);
		itemOfProduct2.setPurchaseOrder(purchaseOrder);
		itemProductList.add(itemOfProduct2);

		List<BatchStock> batchStockList = new ArrayList<>();
		BatchStock batchStock = new BatchStock();
		batchStock.setIdBatchNumber(1L);
		batchStock.setCurrentQuantity(1000L);
		batchStock.setCurrentTemperature(5F);
		batchStock.setDueDate(LocalDate.now());
		batchStock.setInitialQuantity(1000L);
		batchStock.setManufacturingDate(LocalDate.now());
		batchStock.setManufacturingTime(LocalDateTime.now());
		batchStock.setIdSalesAd(salesAd);
		batchStockList.add(batchStock);

		Mockito.when(mockBatchStockRepository.saveAndFlush(Mockito.any(BatchStock.class))).thenReturn(batchStock);
		purchaseOrderService.DecrementQuantity(itemOfProduct, batchStockList);

		assertEquals(900, batchStock.getCurrentQuantity());
	}
}
