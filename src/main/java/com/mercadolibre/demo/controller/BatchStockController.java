package com.mercadolibre.demo.controller;

import java.util.List;

import com.mercadolibre.demo.config.SecurityController;
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
public class BatchStockController implements SecurityController {

    @Autowired
    private BatchStockService batchStockService;

    @PostMapping(value = "/save")
    public ResponseEntity<BatchStock> saveBatchStock(@Valid @RequestBody BatchStockDTO dto) throws Exception {
    	try {
            BatchStock batchStock = batchStockService.save(dto);
            return new ResponseEntity<>(batchStock, HttpStatus.CREATED);
    	} catch(Exception e) {
    		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
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
    	} catch (Exception e) {
    		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
    }
}
