package com.mercadolibre.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.repository.SalesAdRepository;

@Service
public class SalesAdService {

	public final SalesAdRepository salesAdRepository;

	@Autowired
	public SalesAdService(SalesAdRepository salesAdRepository) {
		this.salesAdRepository = salesAdRepository;
	}
	
	// CREATE
	public SalesAd save(SalesAd salesAd) {
		return salesAdRepository.save(salesAd);
	}
	
	// READ
	public List<SalesAd> list() {
		return salesAdRepository.findAll();
	}

	// UPDATE
	public SalesAd update(SalesAd salesAd) {
		return salesAdRepository.saveAndFlush(salesAd);
	}
	
	 // DELETE
	public void delete(Long batchNumber) {
		salesAdRepository.deleteById(batchNumber);
	}
}
