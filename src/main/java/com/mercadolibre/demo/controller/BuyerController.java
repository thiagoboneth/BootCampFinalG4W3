package com.mercadolibre.demo.controller;


import com.mercadolibre.demo.dto.BuyerDTO;
import com.mercadolibre.demo.dto.response.BuyerResponseDTO;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/buyer")
public class BuyerController {

	@Autowired
	private BuyerService buyerService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	private ResponseEntity<BuyerDTO> createBuyer(@Valid @RequestBody BuyerDTO dto){
		Buyer buyer = buyerService.save(dto.convertObjectBuyer());
		return new ResponseEntity(BuyerResponseDTO.convertDTO(buyer),HttpStatus.OK);
	}
	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<Buyer>> listBuyer(){
		List<Buyer> buyers = buyerService.list();
		return new ResponseEntity<>(buyers, HttpStatus.OK);
	}
	@PutMapping(value = "/update")
	@ResponseBody
	public ResponseEntity<Buyer> updateBuyer(@Valid @RequestBody Buyer buyer){
		Buyer s = buyerService.update(buyer);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}
	@DeleteMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity<String> deleteBuyer(@RequestParam Long idsBuyer){
		buyerService.delete(idsBuyer);
		return new ResponseEntity<>("Buyer successfully deleted", HttpStatus.OK);
	}
}