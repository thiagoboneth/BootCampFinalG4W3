package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.dto.WareHouseDTO;
import com.mercadolibre.demo.dto.response.WareHouseResponseDTO;
import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/warehouse")
public class WareHouseController {

    private WareHouseService wareHouseService;

    @Autowired
    public WareHouseController(WareHouseService wareHouseService) {this.wareHouseService = wareHouseService;}

	@PostMapping(value = "/save")
    private ResponseEntity<WareHouseResponseDTO> saveWareHouse( @RequestBody WareHouseDTO dto){
        WareHouse wareHouse = wareHouseService.create(dto.convertObject());
        return new ResponseEntity<>(WareHouseResponseDTO.convertDTO(wareHouse),HttpStatus.CREATED);
    }
    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<List<WareHouse>> listWareHouse(){
        List<WareHouse> wareHouses = wareHouseService.list();
        return new ResponseEntity<>(wareHouses, HttpStatus.OK);
    }
    @PutMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<WareHouse> updateWareHouse( @RequestBody WareHouse wareHouse){
        WareHouse s = wareHouseService.update(wareHouse);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }
/*    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> deleteWareHouse(@RequestParam Long idsWareHouse){
        wareHouseService.delete(idsWareHouse);
        return new ResponseEntity<>("WareHouse successfully deleted", HttpStatus.OK);
    }*/
}
