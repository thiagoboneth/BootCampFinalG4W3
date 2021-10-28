package com.mercadolibre.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mercadolibre.demo.dto.BatchStockDTO;
import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.service.BatchStockService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/batchstock")
public class BatchStockController {

    @Autowired
    private BatchStockService batchStockService;

    @PostMapping(value = "/save")
    public ResponseEntity<BatchStock> saveBatchStock(@Valid @RequestBody BatchStockDTO dto) throws Exception {
        try {
            BatchStock batchStock = batchStockService.save(dto);
            return new ResponseEntity<>(batchStock, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<List<BatchStock>> listBatchStock() {
        List<BatchStock> batchStocks = batchStockService.list();
        return new ResponseEntity<>(batchStocks, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public ResponseEntity<BatchStock> updateSalesAd(@Valid @RequestBody BatchStockDTO dto, @PathVariable Long id) throws Exception {
        try {
            BatchStock batchStock = batchStockService.update(dto, id);
            return new ResponseEntity<>(batchStock, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteBatchStock(@PathVariable Long id) {
        try {
            batchStockService.delete(id);
            return new ResponseEntity<>("BatchStock successfully deleted", HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
