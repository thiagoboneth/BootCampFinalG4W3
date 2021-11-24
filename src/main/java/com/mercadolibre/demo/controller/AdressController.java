
package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.AdressSaveDTO;
import com.mercadolibre.demo.dto.response.AdressBuscaDTO;
import com.mercadolibre.demo.dto.response.AdressRestDTO;
import com.mercadolibre.demo.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/adress")
public class AdressController implements SecurityController {

	@Autowired
	private AdressService adressService;


	@GetMapping(value = "/buscar/{cep}")
	public ResponseEntity<AdressBuscaDTO> lisAdress(@Valid @PathVariable String cep) throws Exception {
		try {
			AdressBuscaDTO adress = adressService.list(cep);
			return new ResponseEntity<>(adress, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(value = "/save")
	public ResponseEntity<AdressRestDTO> saveAddress(@RequestBody AdressSaveDTO dto) throws Exception {
		try {
			AdressRestDTO adressRestDTO = adressService.save(dto);
			return new ResponseEntity<>(adressRestDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}




}
