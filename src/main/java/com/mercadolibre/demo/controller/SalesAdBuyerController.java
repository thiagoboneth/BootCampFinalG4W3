package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.dto.SalesAdBuyerDTO;
import com.mercadolibre.demo.dto.response.SalesAdBuyerResponseDTO;
import com.mercadolibre.demo.model.SalesAdBuyer;
import com.mercadolibre.demo.service.SalesAdBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/salesadbuyer")
public class SalesAdBuyerController {

	private SalesAdBuyerService salesAdBuyerService;

	@Autowired
	public SalesAdBuyerController(SalesAdBuyerService salesAdBuyerService) {
		this.salesAdBuyerService = salesAdBuyerService;
	}


	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	private ResponseEntity<SalesAdBuyerResponseDTO> createBuyer(@RequestBody SalesAdBuyerDTO dto){
		SalesAdBuyer salesAdBuyer = salesAdBuyerService.save(dto.convertObject());
		return new ResponseEntity(SalesAdBuyerResponseDTO.convertDTO(salesAdBuyer),HttpStatus.OK);
	}
	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<SalesAdBuyer>> listBuyer(){
		List<SalesAdBuyer> salesAdBuyers = salesAdBuyerService.list();
		return new ResponseEntity<>(salesAdBuyers, HttpStatus.OK);
	}
	@PutMapping(value = "/update")
	@ResponseBody
	public ResponseEntity<SalesAdBuyer> updateBuyer(@Valid @RequestBody SalesAdBuyer salesAdBuyer){
		SalesAdBuyer s = salesAdBuyerService.update(salesAdBuyer);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}
	@DeleteMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity<String> deleteBuyer(@RequestParam Long idSalesBuyer){
		salesAdBuyerService.delete(idSalesBuyer);
		return new ResponseEntity<>("Buyer successfully deleted", HttpStatus.OK);
	}
}