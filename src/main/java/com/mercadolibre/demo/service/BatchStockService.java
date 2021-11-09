package com.mercadolibre.demo.service;

import java.util.List;
import java.util.Optional;

import com.mercadolibre.demo.dto.BatchStockDTO;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.repository.SalesAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.repository.BatchStockRepository;

@Service
public class BatchStockService {

	public BatchStockRepository batchStockRepository;
	public SalesAdRepository salesAdRepository;

	@Autowired
	public BatchStockService(BatchStockRepository batchStockRepository, SalesAdRepository salesAdRepository) {
		this.batchStockRepository = batchStockRepository;
		this.salesAdRepository = salesAdRepository;
	}

	public BatchStock save(BatchStockDTO dto) throws Exception {
		BatchStock batchStock = convertBatchStockToObject(dto);
		return batchStockRepository.save(batchStock);
	}

	public List<BatchStock> list() {
		return batchStockRepository.findAll();
	}

	public Optional<BatchStock> findById(Long id) {
		return batchStockRepository.findById(id);
	}

	public BatchStock update(BatchStockDTO dto, Long id) throws Exception {
		Optional<BatchStock> existsBatchStok = findById(id);
		if (existsBatchStok.isPresent()) {
			BatchStock batchStock = convertBatchStockToObject(dto);
			batchStock.setIdBatchNumber(id);
			return batchStockRepository.saveAndFlush(batchStock);
		}
		throw new Exception("Id não cadastrado");
	}

	public void delete(Long batchNumber) {
		batchStockRepository.deleteById(batchNumber);
	}

	public Optional<SalesAd> getSalesAd(BatchStockDTO dto) {
		Optional<SalesAd> salesAd = salesAdRepository.findById(dto.getIdSalesAd());
		return salesAd;
	}

	public BatchStock convertBatchStockToObject(BatchStockDTO dto) {
		if (getSalesAd(dto).isPresent()) {
			BatchStock batchStock = new BatchStock(dto.getCurrentTemperature(), dto.getMinimumTemperature(),
					dto.getInitialQuantity(), dto.getCurrentQuantity(), dto.getManufacturingDate(),
					dto.getManufacturingTime(), dto.getDueDate(), getSalesAd(dto));
			
			return batchStock;
		}	
		return null;
	}   
}
