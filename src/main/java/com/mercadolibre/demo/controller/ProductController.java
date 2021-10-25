package com.mercadolibre.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.demo.dto.ProductDTO;
import com.mercadolibre.demo.dto.response.ProductResponseDTO;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.service.ProductService;

@RestController
@RequestMapping("/api/v1/fresh-products/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping(value = "/save")
	public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody ProductDTO dto) {
		Product product = productService.save(dto.convertObject());
		return new ResponseEntity<>(ProductResponseDTO.convertDTO(product), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<Product>> listProducts() {
		List<Product> products = productService.list();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update")
	@ResponseBody
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
		Product p = productService.update(product);
		return new ResponseEntity<>(p, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity<String> deleteProduct(@RequestParam Long idproduct) {
		productService.delete(idproduct);
		return new ResponseEntity<>("Product successfully deleted", HttpStatus.OK);
	}

}
