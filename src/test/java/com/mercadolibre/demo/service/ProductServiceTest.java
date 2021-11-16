package com.mercadolibre.demo.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import com.mercadolibre.demo.repository.BatchStockRepository;
import com.mercadolibre.demo.repository.InboundOrderRepository;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import com.mercadolibre.demo.dto.ProductDTO;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.repository.ProductRepository;


class ProductServiceTest {

	ProductRepository mock = Mockito.mock(ProductRepository.class);
	InboundOrderRepository mockInboundOrder = Mockito.mock(InboundOrderRepository.class);
	ProductService productService = new ProductService(mock, mockInboundOrder);

	@Test
	void testSaveProductWithSuccess() {

		ProductDTO dto = new ProductDTO();
		dto.setName("Abacate Breda");
		dto.setDescription("Abacate com casca verde vibrante e  sabor adocicado");

		Product product = productService.convertProductToDTO(dto);
		Mockito.when(mock.save(Mockito.any(Product.class))).thenReturn(productService.convertProductToDTO(dto));
		mock.save(productService.save(dto));

		assertEquals("Abacate Breda", product.getName());
		assertEquals("Abacate com casca verde vibrante e  sabor adocicado", product.getDescription());

		assertNotNull(product.getName());
		assertNotNull(product.getDescription());
	}

	@Test
	void testListProductWithSuccess() {

		List<Product> list = new ArrayList<>();
		Product product1 = new Product();
		product1.setId(1L);
		product1.setName("Laranja Lima");
		product1.setDescription("Laranja azedinha rica em vitamina c");
		list.add(product1);

		Product product2 = new Product();
		product2.setId(2L);
		product2.setName("Abacate Breda");
		product2.setDescription("Abacate com casca verde vibrante e  sabor adocicado");
		list.add(product2);

		Mockito.when(mock.findAll()).thenReturn(list);
		List <Product> listAll = mock.findAll();
		productService.list();

		assertNotNull(list);

		assertTrue(listAll.contains(product1));
		assertTrue(listAll.contains(product2));
	}
	
	@Test
	void testFindById() {
		List<Product> list = new ArrayList<>();
		Product product = new Product();
		product.setId(1L);
		product.setName("Laranja Lima");
		product.setDescription("Laranja azedinha rica em vitamina c");

		list.add(product);
		mock.findAll();
		Mockito.when(mock.findById(1L)).thenReturn(Optional.of(list.stream().findAny().get()));
		mock.findById(1L);

		assertEquals("Laranja Lima", product.getName());
		assertEquals("Laranja azedinha rica em vitamina c", product.getDescription());

		assertNotNull(product.getName());
		assertNotNull(product.getDescription());
	}

	@Test
	void testUpdateProductWithSuccess() throws Exception {
		List<Product> list = new ArrayList<>();
		Product product = new Product();
		product.setId(1L);
		product.setName("Laranja Lima");
		product.setDescription("Laranja azedinha rica em vitamina c");
		list.add(product);

		ProductDTO dto = new ProductDTO();
		dto.setName("Manga Tommy");
		dto.setDescription("Manga resistente e com maior durabilidade");

		Mockito.when(mock.findById(1L)).thenReturn(Optional.of(list.stream().findAny().get()));
		Mockito.when(mock.saveAndFlush(product)).thenReturn(product);

		product = productService.convertProductToDTO(dto);
		product.setId(1L);
		productService.update(dto,mock.findById(1L).get().getId());

		assertEquals("Manga Tommy", product.getName());
		assertEquals("Manga resistente e com maior durabilidade", product.getDescription());

		assertNotNull(product.getName());
		assertNotNull(product.getDescription());
	}

	@Test
	void testUpdateProductNoSuccess() {
		List<Product> list = new ArrayList<>();
		Product product = new Product();
		product.setId(1L);
		product.setName("Laranja Lima");
		product.setDescription("Laranja azedinha rica em vitamina c");
		list.add(product);

		ProductDTO dto = new ProductDTO();
		dto.setName("Manga Tommy");
		dto.setDescription("Manga resistente e com maior durabilidade");

		Mockito.when(mock.findById(1L)).thenReturn(Optional.of(list.stream().findAny().get()));
		Mockito.when(mock.saveAndFlush(product)).thenReturn(product);

		product = productService.convertProductToDTO(dto);
		product.setId(1L);

		Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
			productService.update(dto, 2L);
		});

		assertThat(exceptionThatWasThrown.getMessage(), equalTo("Id não cadastrado"));
	}

	@Test
	void deleteProductWithSuccess() throws Exception {
		List<Product> list = new ArrayList<>();

		Product product = new Product();
		product.setId(1L);
		product.setName("Laranja Lima");
		product.setDescription("Laranja azedinha rica em vitamina c");
		list.add(product);
		productService.delete(1L);

		Mockito.verify(mock).deleteById(1L);
	}
}
