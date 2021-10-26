package com.mercadolibre.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.demo.dto.ProductDTO;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product save(ProductDTO dto)  throws Exception{
		Product product = new Product();
		if(dto.getName() != null && dto.getDescription() != null && !dto.getName().isEmpty() && !dto.getDescription().isEmpty()) {
			product = convertProductToDTO(dto);
			return productRepository.save(product);
		} else {
			throw new Exception("Os parametros nào devem ser nulos ou vázios!");
		}
	}
	
	public List<Product> list() {
		return productRepository.findAll();
	}
	
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}
	
	
	public Product update(Product product) {
		
		if(product.getName() != null && product.getDescription() != null && !product.getName().isEmpty() && !product.getDescription().isEmpty()) {
			Optional<Product> existProduct =findById(product.getId());
			if(existProduct.isPresent()) {
				product.setId(product.getId());
			return productRepository.saveAndFlush(product);
			}
			
		}
		return product;
	}
	public void delete(Long id) {
			productRepository.deleteById(id);
	}
	
	public Product convertProductToDTO(ProductDTO dto) {
		return new Product(dto.getName(), dto.getDescription());
	}
}
