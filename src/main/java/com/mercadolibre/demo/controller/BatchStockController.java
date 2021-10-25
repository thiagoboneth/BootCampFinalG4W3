package com.mercadolibre.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.demo.dto.BatchStockDTO;
import com.mercadolibre.demo.dto.response.BatchStockResponseDTO;
import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.service.BatchStockService;

@RestController
@RequestMapping("/api/v1/fresh-products/batchstock")
public class BatchStockController {
	
	@Autowired
	private BatchStockService batchStockService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<BatchStockResponseDTO> saveBatchStock(@RequestBody BatchStockDTO dto) {
		BatchStock batchStock = batchStockService.save(dto.convertObject());
		return new ResponseEntity<>(BatchStockResponseDTO.convertDTO(batchStock), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<BatchStock>> listBatchStock() {
		List<BatchStock> batchStocks = batchStockService.list();
		return new ResponseEntity<>(batchStocks, HttpStatus.OK);	
	}
	
	@PutMapping(value = "/update")
	@ResponseBody
	public ResponseEntity<BatchStock> updateBatchStock(@RequestBody BatchStock batchStock) {
		BatchStock b = batchStockService.update(batchStock);
		return new ResponseEntity<>(b, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity<String> deleteBatchStock(@RequestParam Long batchNumber) {
		batchStockService.delete(batchNumber);
		return new ResponseEntity<>("Product successfully deleted", HttpStatus.OK);
	}
}
