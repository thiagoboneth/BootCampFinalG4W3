package com.mercadolibre.demo.controller;


import com.mercadolibre.demo.dto.WareHouseDTO;
import com.mercadolibre.demo.dto.response.WareHouseResponseDTO;
import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/warehouse")
public class WareHouseController {

    private WareHouseService wareHouseService;

    @Autowired
    public WareHouseController(WareHouseService wareHouseService) {
        super();
        this.wareHouseService = wareHouseService;
    }

	@PostMapping(value = "/save")
    private ResponseEntity<WareHouseResponseDTO> saveWareHouse(@Valid @RequestBody WareHouseDTO dto){
        WareHouse wareHouse = wareHouseService.create(dto.convertObject());
        return new ResponseEntity<>(WareHouseResponseDTO.convertDTO(wareHouse),HttpStatus.CREATED);
    }
}
