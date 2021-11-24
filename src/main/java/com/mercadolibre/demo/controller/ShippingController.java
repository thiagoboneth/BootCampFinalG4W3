package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.AdressSaveDTO;
import com.mercadolibre.demo.dto.response.AdressBuscaDTO;
import com.mercadolibre.demo.dto.response.AdressRestDTO;
import com.mercadolibre.demo.service.AdressService;
import com.mercadolibre.demo.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/shipping")
public class ShippingController implements SecurityController {

	@Autowired
	private ShippingService shippingService;


/*	@GetMapping(value = "/tracking/{code}")
	public ResponseEntity<AdressBuscaDTO> litShipping(@Valid @PathVariable String code) throws Exception {
		try {
			AdressBuscaDTO adress = shippingService.buscar(code);
			return new ResponseEntity<>(adress, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(value = "/save")
	public ResponseEntity<AdressRestDTO> saveShipping(@RequestBody AdressSaveDTO dto) throws Exception {
		try {
			AdressRestDTO adressRestDTO = shippingService.save(dto);
			return new ResponseEntity<>(adressRestDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}*/




}
