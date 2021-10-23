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
	
	public SalesAd save(SalesAd salesAd) {
		return salesAdRepository.save(salesAd);
	}
	public List<SalesAd> list() {
		return salesAdRepository.findAll();
	}
	public SalesAd update(SalesAd salesAd) {
		return salesAdRepository.saveAndFlush(salesAd);
	}
	public void delete(Long batchNumber) {
		salesAdRepository.deleteById(batchNumber);
	}
}
