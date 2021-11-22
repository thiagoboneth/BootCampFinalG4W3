package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.dto.response.ProductInBatchStockDTO;
import com.mercadolibre.demo.dto.response.ProductInBathDTO;
import com.mercadolibre.demo.dto.response.ProductItenForCarsDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@Transactional
@AutoConfigureTestEntityManager
@SpringBootTest
@AutoConfigureMockMvc
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
	void testConvertItemOfProductNoSuccess() throws Exception {

		ItemOfProductDTO itemOfProductDTO = new ItemOfProductDTO();
		itemOfProductDTO.setIdSalesAd(1L);
		itemOfProductDTO.setNameProduct("Laranja");
		itemOfProductDTO.setQuantity(5000L);

		Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
			itemOfProductService.convertItemOfProduct(itemOfProductDTO);
		});

		assertEquals("Erro ao Converter ItemOfProductDTO",exceptionThatWasThrown.getMessage());
	}

	@Test
	void testConvertItemOfProducWithSuccess() throws Exception {

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

		ItemOfProductDTO itemOfProductDTO = new ItemOfProductDTO();
		itemOfProductDTO.setIdSalesAd(1L);
		itemOfProductDTO.setNameProduct("Laranja");
		itemOfProductDTO.setQuantity(5000L);

		Mockito.when(mockSalesAdRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(salesAd));
		Mockito.when(mockItemOfProductRepository.save(Mockito.any(ItemOfProduct.class))).thenReturn(itemOfProduct);

		itemOfProductService.convertItemOfProduct(itemOfProductDTO);

		assertNotNull(itemOfProductDTO);

	}

	@Test
	void testConvertItemOfProductDTOWithSuccess(){
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

		Mockito.when(mockSalesAdRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(salesAd));
		Mockito.when(mockItemOfProductRepository.save(Mockito.any(ItemOfProduct.class))).thenReturn(itemOfProduct);

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
	@Test
	void resetCart() throws Exception {

		List<ItemOfProduct> itemOfProducts = new ArrayList<>();

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(1L);

		SalesAd salesAd = new SalesAd();
		salesAd.setVolume(500.0F);

		ItemOfProduct itemOfProduct = new ItemOfProduct(3200L,salesAd,purchaseOrder);
		ItemOfProduct itemOfProduct2 = new ItemOfProduct(500L,salesAd,purchaseOrder);
		ItemOfProduct itemOfProduct3 = new ItemOfProduct(1100L,salesAd,purchaseOrder);

		itemOfProducts.add(itemOfProduct);
		itemOfProducts.add(itemOfProduct2);
		itemOfProducts.add(itemOfProduct3);

		Mockito.when(mockItemOfProductRepository.orderOfItem(Mockito.any(Long.class))).thenReturn(itemOfProducts);
		Mockito.when(mockPurchaseOrderRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(purchaseOrder));
		itemOfProductService.resetCart(1L);

		assertEquals(0L,itemOfProducts.get(0).getQuantity());
		assertEquals(0L,itemOfProducts.get(1).getQuantity());
	}

	@Test
	void testListProductOfBatchStock(){

		List<BatchStock> batchStockList = new ArrayList<>();

		Product product = new Product();
		product.setId(1L);
		product.setName("Laranja Lima");

		SalesAd salesAd = new SalesAd();
		salesAd.setProduct(product);

		BatchStock batchStock = new BatchStock();
		batchStock.setIdBatchNumber(1L);
		batchStock.setIdSalesAd(salesAd);
		batchStockList.add(batchStock);

		BatchStock batchStock2 = new BatchStock();
		batchStock2.setIdBatchNumber(1L);
		batchStock2.setIdSalesAd(salesAd);
		batchStockList.add(batchStock2);

		BatchStock batchStock3 = new BatchStock();
		batchStock3.setIdBatchNumber(1L);
		batchStock3.setIdSalesAd(salesAd);
		batchStockList.add(batchStock3);

		Mockito.when(mockBatchStockRepository.findAll()).thenReturn(batchStockList);

		List<ProductInBatchStockDTO> productInBatchStockDTOList = itemOfProductService.listProductOfBatchStock();

		assertNotNull(productInBatchStockDTOList);
		assertNotNull(productInBatchStockDTOList.get(2).getBatchStock());

		assertEquals("Laranja Lima",productInBatchStockDTOList.get(0).getNameProduct());
	}
	@Test
	void testListOrderProduct(){

		List<InboundOrder> inboundOrderList = new ArrayList<>();

		Product product = new Product();
		product.setId(1L);
		product.setName("Laranja Lima");

		Seller seller = new Seller();
		seller.setIdseller(1l);

		SalesAd salesAd = new SalesAd();
		salesAd.setVolume(500.0F);
		salesAd.setProduct(product);
		salesAd.setSeller(seller);

		WareHouse wareHouse = new WareHouse();
		wareHouse.setIdWareHouse(1L);
		wareHouse.setWareHouseName("Whare1");

		Section section = new Section();
		section.setWareHouse(wareHouse);
		section.setCategory("Frios");

		BatchStock batchStock = new BatchStock();
		batchStock.setIdBatchNumber(1L);
		batchStock.setCurrentQuantity(500L);
		batchStock.setIdSalesAd(salesAd);
		batchStock.setDueDate(LocalDate.now());

		InboundOrder inboundOrder = new InboundOrder();
		inboundOrder.setId(1L);
		inboundOrder.setBatchStock(batchStock);
		inboundOrder.setSection(section);
		inboundOrder.setOrderDate(LocalDate.now());
		inboundOrderList.add(inboundOrder);

		InboundOrder inboundOrder2 = new InboundOrder();
		inboundOrder2.setId(1L);
		inboundOrder2.setBatchStock(batchStock);
		inboundOrder2.setSection(section);
		inboundOrder2.setOrderDate(LocalDate.now());
		inboundOrderList.add(inboundOrder2);

		Mockito.when(mockInboundOrderRepository.findAll()).thenReturn(inboundOrderList);

		itemOfProductService.listOrderProduct("C");
		itemOfProductService.listOrderProduct("F");

		List<ProductInBathDTO> listaObtida = itemOfProductService.listOrderProduct("L");

		assertNotNull(listaObtida);
		assertEquals("Frios", listaObtida.get(0).getCategory());
		assertEquals("Laranja Lima", listaObtida.get(0).getName());
	}

	@Test
	void testCarItems(){

		List<ItemOfProduct> itemOfProducts = new ArrayList<>();

		Product product = new Product();
		product.setId(1L);
		product.setName("Laranja Lima");

		Seller seller = new Seller();
		seller.setIdseller(1l);

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(1L);

		SalesAd salesAd = new SalesAd();
		salesAd.setVolume(500.0F);
		salesAd.setProduct(product);

		ItemOfProduct itemOfProduct = new ItemOfProduct(3200L,salesAd,purchaseOrder);
		ItemOfProduct itemOfProduct2 = new ItemOfProduct(500L,salesAd,purchaseOrder);
		ItemOfProduct itemOfProduct3 = new ItemOfProduct(1100L,salesAd,purchaseOrder);

		itemOfProducts.add(itemOfProduct);
		itemOfProducts.add(itemOfProduct2);
		itemOfProducts.add(itemOfProduct3);

		Mockito.when(mockItemOfProductRepository.findAll()).thenReturn(itemOfProducts);

		ProductItenForCarsDTO productItenForCarsDTO = itemOfProductService.cartItems(1L);

		assertNotNull(productItenForCarsDTO);

		assertEquals(3200,itemOfProduct.getQuantity());

	}

}
