package com.mercadolibre.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}
	public List<Product> list() {
		return productRepository.findAll();
	}
	public Product update(Product product) {
		return productRepository.saveAndFlush(product);
	}
	public void delete(Long idproduct) {
		productRepository.deleteById(idproduct);
	}
}
