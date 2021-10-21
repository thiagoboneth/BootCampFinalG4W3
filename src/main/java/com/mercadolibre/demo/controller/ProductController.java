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
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@PostMapping(value = "/save")
	public ResponseEntity<ProductResponseDTO> saveProduct(@Valid @RequestBody ProductDTO dto) {
		Product product = productService.save(dto.convertObject()); // Salva a entity 
		return new ResponseEntity<>(ProductResponseDTO.convertDTO(product), HttpStatus.CREATED); // Retorna status code 201(CREATED)
	}
	
	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<Product>> listProducts() {
		List<Product> products = productService.list();
		return new ResponseEntity<>(products, HttpStatus.OK); // Retorna status code 200(OK) e a lista em formato JSON
	}
	
	
	@PutMapping(value = "/update")
	@ResponseBody
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
		Product p = productService.update(product); // Salva a entity e libera o update instantâneamente na DB
		return new ResponseEntity<>(p, HttpStatus.CREATED); //Retorna status code 201(CREATED)
	}
	
	
	// O endpoint delete deve ficar oculto na url da api
	@DeleteMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity<String> deleteProduct(@RequestParam Long idproduct) {
		//productRepository.deleteById(idproduct);
		return new ResponseEntity<>("Product successfully deleted", HttpStatus.OK); // Retorna status code 200(OK) e mensagem
	}

}
