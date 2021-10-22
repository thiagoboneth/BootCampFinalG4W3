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

import com.mercadolibre.demo.dto.SalesAdDTO;
import com.mercadolibre.demo.dto.response.SalesAdResponseDTO;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.service.SalesAdService;

@RestController
@RequestMapping("/api/v1/fresh-products/salesad")
public class SalesAdController {
	
	private SalesAdService salesAdService;

	@Autowired
	public SalesAdController(SalesAdService salesAdService) {
		this.salesAdService = salesAdService;
	}
	
	
	@PostMapping(value = "/save")
	public ResponseEntity<SalesAdResponseDTO> saveBatchStock(@Valid @RequestBody SalesAdDTO dto) {
		SalesAd salesAd = salesAdService.save(dto.convertObject());
		return new ResponseEntity<>(SalesAdResponseDTO.convertDTO(salesAd), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<SalesAd>> listBatchStock() {
		List<SalesAd> salesAds = salesAdService.list();
		return new ResponseEntity<>(salesAds, HttpStatus.OK);	
	}
	
	@PutMapping(value = "/update")
	@ResponseBody
	public ResponseEntity<SalesAd> updateBatchStock(@Valid @RequestBody SalesAd salesAd) {
		SalesAd s = salesAdService.update(salesAd);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity<String> deleteBatchStock(@RequestParam Long id) {
		salesAdService.delete(id);
		return new ResponseEntity<>("Product successfully deleted", HttpStatus.OK);
	}
}