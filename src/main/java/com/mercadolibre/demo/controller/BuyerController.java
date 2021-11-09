package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.dto.BuyerDTO;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.service.BuyerService;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/buyer")
public class BuyerController {

	@Autowired
	private BuyerService buyerService;

	@PostMapping(value = "/save")
	public ResponseEntity<Buyer> saveBuyer(@Valid @RequestBody BuyerDTO dto) {
		Buyer buyer = buyerService.save(dto);
		return new ResponseEntity<>(buyer, HttpStatus.CREATED);
	}

	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<Buyer>> listBuyer() {
		List<Buyer> buyers = buyerService.list();
		return new ResponseEntity<>(buyers, HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Buyer> updateBuyer(@Valid @RequestBody BuyerDTO buyerDTO, @PathVariable Long id) throws Exception {
		Buyer buyer = buyerService.update(buyerDTO, id);
		return new ResponseEntity<>(buyer, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteBuyer(@PathVariable Long id) throws Exception {
		buyerService.delete(id);
		return new ResponseEntity<>("Comprador com Id " + id + " deletado com sucesso", HttpStatus.OK);
	}
}