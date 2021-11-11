package com.mercadolibre.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

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
public class SalesAdController {

    @Autowired
    private SalesAdService salesAdService;

    @PostMapping(value = "/save")
    public ResponseEntity<SalesAd> saveSalesAd(@Valid @RequestBody SalesAdDTO dto) throws Exception {
            SalesAd salesAd = salesAdService.save(dto);
            return new ResponseEntity<>(salesAd, HttpStatus.CREATED);
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
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}