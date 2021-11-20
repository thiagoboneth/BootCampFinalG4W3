package com.mercadolibre.demo.controller;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.List;

import javax.validation.Valid;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.response.DueDateDTO;
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
public class ProductController implements SecurityController {

	@Autowired
	private ProductService productService;

	@PostMapping(value = "/save")
	public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductDTO dto) {
		Product product = productService.save(dto);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<Product>> listProducts() {
		List<Product> products = productService.list();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}


	@GetMapping(value = "/duedatelist/{numberOfDay}/{idSection}")
	@ResponseBody
	public ResponseEntity<List<DueDateDTO>> dueDate(@PathVariable Long numberOfDay, @PathVariable Long idSection) {
		List<DueDateDTO> dueDate = productService.dueDate(numberOfDay,idSection);
		return new ResponseEntity<>(dueDate, HttpStatus.OK);
	}

	@GetMapping(value = "/duedatelist/list/{numberOfDay}/{CategoryName}/{typeOfList}")
	@ResponseBody
	public ResponseEntity<List<DueDateDTO>> dueDateCustom(@PathVariable Long numberOfDay, @PathVariable String CategoryName, @PathVariable String typeOfList) throws Exception {
		List<DueDateDTO> dueDateCustom = productService.dueDateCustom(numberOfDay,CategoryName, typeOfList);
		return new ResponseEntity<>(dueDateCustom, HttpStatus.OK);
	}
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody ProductDTO dto, @PathVariable Long id) throws Exception {
		Product product = productService.update(dto, id);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws Exception {
		productService.delete(id);
		return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
	}
}