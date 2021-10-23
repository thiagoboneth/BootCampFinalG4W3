package com.mercadolibre.demo.service;

import com.mercadolibre.demo.model.Delegate;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.model.SalesAdBuyer;
import com.mercadolibre.demo.repository.SalesAdBuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesAdBuyerService {

	@Autowired
	private final SalesAdBuyerRepository salesAdBuyerRepository;

	public SalesAdBuyerService(SalesAdBuyerRepository salesAdBuyerRepository) {this.salesAdBuyerRepository = salesAdBuyerRepository;}

	public SalesAdBuyer save(SalesAdBuyer salesAdBuyer) {
		return salesAdBuyerRepository.save(salesAdBuyer);
	}
	public List<SalesAdBuyer> list() {
		return salesAdBuyerRepository.findAll();
	}
	public SalesAdBuyer update(SalesAdBuyer salesAdBuyer) {
		return salesAdBuyerRepository.saveAndFlush(salesAdBuyer);
	}
	public void delete(Long idproduct) {
		salesAdBuyerRepository.deleteById(idproduct);
	}
}
