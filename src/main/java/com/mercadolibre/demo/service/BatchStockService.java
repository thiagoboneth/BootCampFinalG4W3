package com.mercadolibre.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.repository.BatchStockRepository;

@Service
public class BatchStockService {
	
	public final BatchStockRepository batchStockRepository;

	@Autowired
	public BatchStockService(BatchStockRepository batchStockRepositor) {
		this.batchStockRepository = batchStockRepositor;
	}
	
	public BatchStock save(BatchStock batchStock) {
		return batchStockRepository.save(batchStock);
	}
	public List<BatchStock> list() {
		return batchStockRepository.findAll();
	}
	public BatchStock update(BatchStock batchStock) {
		return batchStockRepository.saveAndFlush(batchStock);
	}
	public void delete(Long batchNumber) {
		batchStockRepository.deleteById(batchNumber);
	}
}
