package com.mercadolibre.demo.controller;

import java.util.List;

import com.mercadolibre.demo.config.SecurityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mercadolibre.demo.dto.SalesAdDTO;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.service.SalesAdService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/salesad")
public class SalesAdController implements SecurityController {

    @Autowired
    private SalesAdService salesAdService;

    @PostMapping(value = "/save")
    public ResponseEntity<SalesAd> saveSalesAd(@Valid @RequestBody SalesAdDTO dto) throws Exception {
    	try {
            SalesAd salesAd = salesAdService.save(dto);
            return new ResponseEntity<>(salesAd, HttpStatus.CREATED);
    	} catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<List<SalesAd>> listSalesAd() {
        List<SalesAd> salesAds = salesAdService.list();
        return new ResponseEntity<>(salesAds, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public ResponseEntity<SalesAd> updateSalesAd(@Valid @RequestBody SalesAdDTO dto, @PathVariable Long id) throws Exception {
        try {
            SalesAd salesAd = salesAdService.update(dto, id);
            return new ResponseEntity<>(salesAd, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage() ,HttpStatus.NOT_FOUND);
        }
    }
}