package com.mercadolibre.demo.controller;


import com.mercadolibre.demo.dto.BuyerDTO;
import com.mercadolibre.demo.dto.response.BuyerResponseDTO;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/buyer")
public class BuyerController {

	private BuyerService buyerService;

	@Autowired
	public BuyerController(BuyerService buyerService) {
		super();
		this.buyerService = buyerService;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	private ResponseEntity<BuyerDTO> createBuyer(@Valid @RequestBody BuyerDTO dto){
		Buyer buyer = buyerService.create(dto.convertObjectBuyer());
		return new ResponseEntity(BuyerResponseDTO.convertDTO(buyer),HttpStatus.OK);
	}
}