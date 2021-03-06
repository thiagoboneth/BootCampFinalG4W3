package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.WareHouseDTO;
import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.service.WareHouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/warehouse")
public class WareHouseController implements SecurityController {

	@Autowired
	private WareHouseService wareHouseService;

	@PostMapping(value = "/save")
	public ResponseEntity<WareHouse> saveWareHouse(@Valid @RequestBody WareHouseDTO dto){
		WareHouse wareHouse = wareHouseService.save(dto);
		return new ResponseEntity<>(wareHouse,HttpStatus.CREATED);
	}

	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<WareHouse>> listWareHouse(){
		List<WareHouse> wareHouses = wareHouseService.list();
		return new ResponseEntity<>(wareHouses, HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<WareHouse> updateWareHouse(@Valid @RequestBody WareHouseDTO dto, @PathVariable Long id) throws Exception {
		try {
			WareHouse wareHouse = wareHouseService.update(dto, id);
			return new ResponseEntity<>(wareHouse, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
