package com.mercadolibre.demo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mercadolibre.demo.dto.BatchStockDTO;
import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.model.Seller;
import com.mercadolibre.demo.repository.BatchStockRepository;
import com.mercadolibre.demo.repository.SalesAdRepository;

public class BatchStockServiceTest {


	BatchStockRepository mockBatchStockRepository = Mockito.mock(BatchStockRepository.class);
	SalesAdRepository mockSalesAdRepository = Mockito.mock(SalesAdRepository.class);

	BatchStockService batchStockService = new BatchStockService(mockBatchStockRepository, mockSalesAdRepository);

	@Test
	void testSaveBatchStockWithSuccess() throws Exception {

		BatchStockDTO batchStockDTO = new BatchStockDTO();
		batchStockDTO.setCurrentTemperature(0F);
		batchStockDTO.setMinimumTemperature(-5F);
		batchStockDTO.setInitialQuantity(1000L);
		batchStockDTO.setCurrentQuantity(800L);
		batchStockDTO.setManufacturingDate(LocalDate.now());
		batchStockDTO.setManufacturingTime(LocalDateTime.now());
		batchStockDTO.setDueDate(LocalDate.now());
		batchStockDTO.setIdSalesAd(1L);

		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setId(1L);
		product.setName("Abacate Breda");
		product.setDescription("Abacate com casca verde vibrante e  sabor adocicado");
		productList.add(product);


		List<Seller> sellerList = new ArrayList<Seller>();
		Seller seller = new Seller();
		seller.setIdseller(1L);
		seller.setName("Monkey");
		seller.setLastname("D. Luffy");
		sellerList.add(seller);

		List<SalesAd> salesAdList = new ArrayList<>();
		SalesAd salesAd = new SalesAd();
		salesAd.setVolume(500.0F);
		salesAd.setMinimumTemperature(8F);
		salesAd.setMaximumTemperature(45.0F);
		salesAd.setPrice(1200.0D);
		salesAd.setId(1L);
		salesAd.setProduct(product);
		salesAd.setSeller(seller);
		salesAdList.add(salesAd);

		BatchStock batchStock = new BatchStock();

		Mockito.when(mockBatchStockRepository.save(Mockito.any(BatchStock.class))).thenReturn(batchStock);
		Mockito.when(mockSalesAdRepository.findById(1L)).thenReturn(Optional.of(salesAd));
		Mockito.when(mockBatchStockRepository.findById(1L)).thenReturn(Optional.of(batchStock));

		batchStock = batchStockService.convertBatchStockToObject(batchStockDTO);

		mockBatchStockRepository.save(batchStockService.save(batchStockDTO));
		batchStock.setBatchNumber(1L);

		assertEquals(1L, batchStock.getBatchNumber());
		assertEquals(0F, batchStock.getCurrentTemperature());
		assertEquals(-5F, batchStock.getMinimumTemperature());
		assertEquals(1000L, batchStock.getInitialQuantity());
		assertEquals(800L, batchStock.getCurrentQuantity());
		assertEquals(salesAd, batchStock.getSalesAd());

		assertNotNull(batchStock.getBatchNumber());
		assertNotNull(batchStock.getCurrentTemperature());
		assertNotNull(batchStock.getMinimumTemperature());
		assertNotNull(batchStock.getInitialQuantity());
		assertNotNull(batchStock.getCurrentQuantity());
		assertNotNull(batchStock.getManufacturingDate());
		assertNotNull(batchStock.getManufacturingTime());
		assertNotNull(batchStock.getDueDate());
		assertNotNull(batchStock.getSalesAd());
	}

	@Test
	void testGetSalesAdWithSuccessful() {

		BatchStockDTO batchStockDTO = new BatchStockDTO();
		batchStockDTO.setCurrentTemperature(10F);
		batchStockDTO.setMinimumTemperature(0F);
		batchStockDTO.setInitialQuantity(40L);
		batchStockDTO.setCurrentQuantity(40L);
		batchStockDTO.setManufacturingDate(LocalDate.now());
		batchStockDTO.setManufacturingTime(LocalDateTime.now());
		batchStockDTO.setDueDate(LocalDate.now());
		batchStockDTO.setIdSalesAd(1L);

		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setId(1L);
		product.setName("Laranja Lima");
		product.setDescription("Laranja azedinha rica em vitamina c");
		productList.add(product);

		List<Seller> sellerList = new ArrayList<Seller>();
		Seller seller = new Seller();
		seller.setIdseller(1L);
		seller.setName("Monkey");
		seller.setLastname("D. Luffy");
		sellerList.add(seller);

		List<SalesAd> salesAdList = new ArrayList<>();
		SalesAd salesAd = new SalesAd();
		salesAd.setId(1L);
		salesAd.setVolume(200.0F);
		salesAd.setMinimumTemperature(0F);
		salesAd.setMaximumTemperature(30.0F);
		salesAd.setPrice(1200.0D);
		salesAd.setProduct(product);
		salesAd.setSeller(seller);
		salesAdList.add(salesAd);

		Mockito.when(mockSalesAdRepository.findById(1L)).thenReturn(Optional.of(salesAdList.stream().findAny().get()));
		SalesAd getSalesAd = batchStockService.getSalesAd(batchStockDTO).get();

		assertEquals(1L,getSalesAd.getId());
		assertEquals(200.0F, getSalesAd.getVolume());
		assertEquals(0F, getSalesAd.getMinimumTemperature());
		assertEquals(30.0F, getSalesAd.getMaximumTemperature());
		assertEquals(1200.0D, getSalesAd.getPrice());
		assertEquals(product, getSalesAd.getProduct());
		assertEquals(seller, getSalesAd.getSeller());

		assertNotNull(getSalesAd.getId());
		assertNotNull(getSalesAd.getVolume());
		assertNotNull(getSalesAd.getMinimumTemperature());
		assertNotNull(getSalesAd.getMaximumTemperature());
		assertNotNull(getSalesAd.getPrice());
		assertNotNull(getSalesAd.getProduct());
		assertNotNull(getSalesAd.getSeller());
	}

	@Test
	void testListBatchStock() {

		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setId(1L);
		product.setName("Laranja Lima");
		product.setDescription("Laranja azedinha rica em vitamina c");
		productList.add(product);

		List<Seller> sellerList = new ArrayList<Seller>();
		Seller seller = new Seller();
		seller.setIdseller(1L);
		seller.setName("Monkey");
		seller.setLastname("D. Luffy");
		sellerList.add(seller);

		List<SalesAd> salesAdList = new ArrayList<>();
		SalesAd salesAd = new SalesAd();
		salesAd.setId(1L);
		salesAd.setVolume(200.0F);
		salesAd.setMinimumTemperature(0F);
		salesAd.setMaximumTemperature(30.0F);
		salesAd.setPrice(1200.0D);
		salesAd.setProduct(product);
		salesAd.setSeller(seller);
		salesAdList.add(salesAd);

		List<BatchStock> batchStockList = new ArrayList<>();
		BatchStock batchStock = new BatchStock();
		batchStock.setBatchNumber(1L);
		batchStock.setCurrentTemperature(10F);
		batchStock.setMinimumTemperature(0F);
		batchStock.setInitialQuantity(40L);
		batchStock.setCurrentQuantity(40L);
		batchStock.setManufacturingDate(LocalDate.now());
		batchStock.setManufacturingTime(LocalDateTime.now());
		batchStock.setDueDate(LocalDate.now());
		batchStock.setSalesAd(salesAd);
		batchStockList.add(batchStock);

		Mockito.when(mockBatchStockRepository.findAll()).thenReturn(batchStockList);
		batchStockList = mockBatchStockRepository.findAll();
		batchStockService.list();

		assertEquals(1L, batchStock.getBatchNumber());
		assertEquals(10F, batchStock.getCurrentTemperature());
		assertEquals(0F, batchStock.getMinimumTemperature());
		assertEquals(40L, batchStock.getInitialQuantity());
		assertEquals(40L, batchStock.getCurrentQuantity());
		assertEquals(salesAd, batchStock.getSalesAd());

		assertNotNull(batchStock.getBatchNumber());
		assertNotNull(batchStock.getCurrentTemperature());
		assertNotNull(batchStock.getMinimumTemperature());
		assertNotNull(batchStock.getInitialQuantity());
		assertNotNull(batchStock.getCurrentQuantity());
		assertNotNull(batchStock.getManufacturingDate());
		assertNotNull(batchStock.getManufacturingTime());
		assertNotNull(batchStock.getDueDate());
		assertNotNull(batchStock.getSalesAd());
	}

	@Test
	void testUpdateBatchStockWithSuccessful() throws Exception {

		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setId(1L);
		product.setName("Abacate Breda");
		product.setDescription("Abacate com casca verde vibrante e  sabor adocicado");
		productList.add(product);


		List<Seller> sellerList = new ArrayList<Seller>();
		Seller seller = new Seller();
		seller.setIdseller(1L);
		seller.setName("Monkey");
		seller.setLastname("D. Luffy");
		sellerList.add(seller);

		List<SalesAd> salesAdList = new ArrayList<>();
		SalesAd salesAd = new SalesAd();
		salesAd.setVolume(500.0F);
		salesAd.setMinimumTemperature(8F);
		salesAd.setMaximumTemperature(45.0F);
		salesAd.setPrice(1200.0D);
		salesAd.setId(1L);
		salesAd.setProduct(product);
		salesAd.setSeller(seller);
		salesAdList.add(salesAd);

		BatchStock batchStock = new BatchStock();
		batchStock.setBatchNumber(1L);
		batchStock.setCurrentTemperature(10F);
		batchStock.setMinimumTemperature(0F);
		batchStock.setInitialQuantity(40L);
		batchStock.setCurrentQuantity(40L);
		batchStock.setManufacturingDate(LocalDate.now());
		batchStock.setManufacturingTime(LocalDateTime.now());
		batchStock.setDueDate(LocalDate.now());
		batchStock.setSalesAd(salesAd);

		BatchStockDTO batchStockDTO = new BatchStockDTO();
		batchStockDTO.setCurrentTemperature(0F);
		batchStockDTO.setMinimumTemperature(-5F);
		batchStockDTO.setInitialQuantity(1000L);
		batchStockDTO.setCurrentQuantity(800L);
		batchStockDTO.setDueDate(LocalDate.now());
		batchStockDTO.setIdSalesAd(1L);

		Mockito.when(mockSalesAdRepository.findById(1L)).thenReturn(Optional.of(salesAd));
		Mockito.when(mockBatchStockRepository.findById(1L)).thenReturn(Optional.of(batchStock));
		Mockito.when(mockBatchStockRepository.saveAndFlush(Mockito.any(BatchStock.class))).thenReturn(batchStock);

		batchStock = batchStockService.convertBatchStockToObject(batchStockDTO);
		
		batchStockService.update(batchStockDTO, mockBatchStockRepository.findById(1L).get().getBatchNumber());
		batchStock.setBatchNumber(1L);
		
		assertEquals(1L, batchStock.getBatchNumber());
		assertEquals(0F, batchStock.getCurrentTemperature());
		assertEquals(-5F, batchStock.getMinimumTemperature());
		assertEquals(1000L, batchStock.getInitialQuantity());
		assertEquals(800L, batchStock.getCurrentQuantity());
		assertEquals(salesAd, batchStock.getSalesAd());

		assertNotNull(batchStock.getBatchNumber());
		assertNotNull(batchStock.getCurrentTemperature());
		assertNotNull(batchStock.getMinimumTemperature());
		assertNotNull(batchStock.getInitialQuantity());
		assertNotNull(batchStock.getCurrentQuantity());
		assertNotNull(batchStock.getManufacturingDate());
		assertNotNull(batchStock.getManufacturingTime());
		assertNotNull(batchStock.getDueDate());
		assertNotNull(batchStock.getSalesAd());
	}

	@Test
	void testUpdateBatchStockNoSuccess() throws Exception {
		
		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setId(1L);
		product.setName("Abacate Breda");
		product.setDescription("Abacate com casca verde vibrante e  sabor adocicado");
		productList.add(product);


		List<Seller> sellerList = new ArrayList<Seller>();
		Seller seller = new Seller();
		seller.setIdseller(1L);
		seller.setName("Monkey");
		seller.setLastname("D. Luffy");
		sellerList.add(seller);

		List<SalesAd> salesAdList = new ArrayList<>();
		SalesAd salesAd = new SalesAd();
		salesAd.setVolume(500.0F);
		salesAd.setMinimumTemperature(8F);
		salesAd.setMaximumTemperature(45.0F);
		salesAd.setPrice(1200.0D);
		salesAd.setId(1L);
		salesAd.setProduct(product);
		salesAd.setSeller(seller);
		salesAdList.add(salesAd);

		BatchStock batchStock = new BatchStock();
		batchStock.setBatchNumber(1L);
		batchStock.setCurrentTemperature(10F);
		batchStock.setMinimumTemperature(0F);
		batchStock.setInitialQuantity(40L);
		batchStock.setCurrentQuantity(40L);
		batchStock.setManufacturingDate(LocalDate.now());
		batchStock.setManufacturingTime(LocalDateTime.now());
		batchStock.setDueDate(LocalDate.now());
		batchStock.setSalesAd(salesAd);

		BatchStockDTO batchStockDTO = new BatchStockDTO();
		batchStockDTO.setCurrentTemperature(0F);
		batchStockDTO.setMinimumTemperature(-5F);
		batchStockDTO.setInitialQuantity(1000L);
		batchStockDTO.setCurrentQuantity(800L);
		batchStockDTO.setDueDate(LocalDate.now());
		batchStockDTO.setIdSalesAd(1L);

		Mockito.when(mockSalesAdRepository.findById(1L)).thenReturn(Optional.of(salesAd));
		Mockito.when(mockBatchStockRepository.findById(1L)).thenReturn(Optional.of(batchStock));
		Mockito.when(mockBatchStockRepository.saveAndFlush(Mockito.any(BatchStock.class))).thenReturn(batchStock);
		
		batchStock = batchStockService.convertBatchStockToObject(batchStockDTO);
		
		batchStockService.update(batchStockDTO, mockBatchStockRepository.findById(1L).get().getBatchNumber());
		batchStock.setBatchNumber(1L);
		
        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
        	batchStockService.update(batchStockDTO, 2L);
        });

        assertThat(exceptionThatWasThrown.getMessage(), equalTo("Id não cadastrado"));
	}
	
	@Test
	void deleteBatchStockWithSuccess() {
		
		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setId(1L);
		product.setName("Abacate Breda");
		product.setDescription("Abacate com casca verde vibrante e  sabor adocicado");
		productList.add(product);


		List<Seller> sellerList = new ArrayList<Seller>();
		Seller seller = new Seller();
		seller.setIdseller(1L);
		seller.setName("Monkey");
		seller.setLastname("D. Luffy");
		sellerList.add(seller);

		List<SalesAd> salesAdList = new ArrayList<>();
		SalesAd salesAd = new SalesAd();
		salesAd.setVolume(500.0F);
		salesAd.setMinimumTemperature(8F);
		salesAd.setMaximumTemperature(45.0F);
		salesAd.setPrice(1200.0D);
		salesAd.setId(1L);
		salesAd.setProduct(product);
		salesAd.setSeller(seller);
		salesAdList.add(salesAd);

		BatchStock batchStock = new BatchStock();
		batchStock.setBatchNumber(1L);
		batchStock.setCurrentTemperature(10F);
		batchStock.setMinimumTemperature(0F);
		batchStock.setInitialQuantity(40L);
		batchStock.setCurrentQuantity(40L);
		batchStock.setManufacturingDate(LocalDate.now());
		batchStock.setManufacturingTime(LocalDateTime.now());
		batchStock.setDueDate(LocalDate.now());
		batchStock.setSalesAd(salesAd);

		BatchStockDTO batchStockDTO = new BatchStockDTO();
		batchStockDTO.setCurrentTemperature(0F);
		batchStockDTO.setMinimumTemperature(-5F);
		batchStockDTO.setInitialQuantity(1000L);
		batchStockDTO.setCurrentQuantity(800L);
		batchStockDTO.setDueDate(LocalDate.now());
		batchStockDTO.setIdSalesAd(1L);
		
		batchStockService.delete(1L);
		Mockito.verify(mockBatchStockRepository).deleteById(1L);
		
	}
}
