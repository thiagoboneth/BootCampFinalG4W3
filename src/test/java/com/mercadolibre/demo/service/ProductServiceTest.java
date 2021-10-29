package com.mercadolibre.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mercadolibre.demo.dto.ProductDTO;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.repository.ProductRepository;

class ProductServiceTest {

	ProductRepository mock = Mockito.mock(ProductRepository.class);
	ProductService productService = new ProductService(mock);

	@Test
	void testSaveProductWithSuccess() {
	
		ProductDTO dto = new ProductDTO();
		dto.setName("Abacate Breda");
		dto.setDescription("Abacate com casca verde vibrante e  sabor adocicado");
		
		Product product = productService.convertProductToDTO(dto);
		Mockito.when(mock.save(Mockito.any(Product.class))).thenReturn(product);
		mock.save(product);
		productService.save(dto);

		assertEquals("Abacate Breda", product.getName());
		assertEquals("Abacate com casca verde vibrante e  sabor adocicado", product.getDescription());

		assertNotNull(product.getName());
		assertNotNull(product.getDescription());
	}
	
	@Test
	void testListProductWithSuccess() {
		
		List<Product> list = new ArrayList<Product>();
		Product product = new Product();
		product.setId(1L);
		product.setName("Laranja Lima");
		product.setDescription("Laranja azedinha rica em vitamina c");
		
		list.add(product);
		
		Mockito.when(mock.findAll()).thenReturn(list);
		mock.findAll();
		productService.list();
	}

	@Test
	void testUpdateProductWithSuccess() throws Exception {
		
		Product product = new Product();
		product.setId(1L);
		product.setName("Laranja Lima");
		product.setDescription("Laranja azedinha rica em vitamina c");
		
		//Optional<Product> existProduct = productService.findById(product.getId());
		
		ProductDTO dto = new ProductDTO();
		dto.setName("Manga Tommy");
		dto.setDescription("Manga resistente e com maior durabilidade");
		
		Product updateProduct = productService.convertProductToDTO(dto);
		Mockito.when(mock.saveAndFlush(Mockito.any(Product.class))).thenReturn(updateProduct);
		mock.saveAndFlush(updateProduct);
		productService.update(dto, 1L);

		assertEquals("Manga Tommy", product.getName());
		assertEquals("Manga resistente e com maior durabilidade", updateProduct.getDescription());

		assertNotNull(updateProduct.getName());
		assertNotNull(updateProduct.getDescription());
	}

	@Test
	void deleteProductWithSuccess() {
		
		List<Product> list = new ArrayList<Product>();
		Product product = new Product();
		product.setId(1L);
		product.setName("Laranja Lima");
		product.setDescription("Laranja azedinha rica em vitamina c");
		
		list.add(product);
		
		productService.delete(1L);
		
	}
}
