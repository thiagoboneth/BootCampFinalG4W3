package com.mercadolibre.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.BuyerDataSaveDTO;
import com.mercadolibre.demo.dto.ViaCepDTO;
import com.mercadolibre.demo.model.BuyerData;
import com.mercadolibre.demo.service.BuyerDataService;

@RestController
@RequestMapping("/api/v1/fresh-products/buyerdata")
public class BuyerDataController implements SecurityController  {
	
	@Autowired
	private BuyerDataService buyerDataService;
	
	@GetMapping(value = "/list/{cep}")
	public ResponseEntity<ViaCepDTO> listCep(@PathVariable String cep) throws Exception {
		try {
			ViaCepDTO viaCep = buyerDataService.listCep(cep);
			return new ResponseEntity<>(viaCep, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
		
	@PostMapping(value = "/save")
	public ResponseEntity<BuyerData> saveAddress(@Valid @RequestBody BuyerDataSaveDTO dto) throws Exception {
		try {
			BuyerData buyerData = buyerDataService.save(dto);
			return new ResponseEntity<>(buyerData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
