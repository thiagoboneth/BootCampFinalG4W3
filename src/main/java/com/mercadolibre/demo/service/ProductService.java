package com.mercadolibre.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	// CREATE
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	// READ
	public List<Product> list() {
		return productRepository.findAll();
	}

	// UPDATE
	public Product update(Product product) {
		return productRepository.saveAndFlush(product);
	}
	
	 // DELETE
//	public Product delete(Product product) {
//		return productRepository.deleteAllById(null);;
//	}
}
