package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.dto.SellerDTO;
import com.mercadolibre.demo.model.Seller;
import com.mercadolibre.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/seller")
public class SellerController {

	@Autowired
	private SellerService sellerService;

	@PostMapping(value = "/save")
	public ResponseEntity<Seller> saveSeller(@Valid @RequestBody SellerDTO dto) {
		Seller seller = sellerService.save(dto);
		return new ResponseEntity<>(seller, HttpStatus.CREATED);
	}

	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<Seller>> listSeller() {
		List<Seller> sellers = sellerService.list();
		return new ResponseEntity<>(sellers, HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Seller> updateSeller(@Valid @RequestBody SellerDTO sellerDTO, @PathVariable Long id) throws Exception {
		Seller seller = sellerService.update(sellerDTO, id);
		return new ResponseEntity<>(seller, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteSeller(@PathVariable Long id) throws Exception {
		sellerService.delete(id);
		return new ResponseEntity<>("Seller successfully deleted", HttpStatus.OK);
	}
}
