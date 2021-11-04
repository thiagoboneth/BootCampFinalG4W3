package com.mercadolibre.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.mercadolibre.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.demo.dto.ProductDTO;
import com.mercadolibre.demo.model.Product;

@RestController
@RequestMapping("/api/v1/fresh-products/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
		
	@PostMapping(value = "/save")
	public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductDTO dto) {
		try {
			Product product = productService.save(dto);
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<Product>> listProducts() {
		List<Product> products = productService.list();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody ProductDTO dto, @PathVariable Long id) throws Exception {
		try{
			Product product = productService.update(dto, id);
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		try {
			productService.delete(id);
			return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
