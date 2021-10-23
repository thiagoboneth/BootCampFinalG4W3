package com.mercadolibre.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.demo.model.InboundOrder;
import com.mercadolibre.demo.repository.InboundOrderRepository;

@Service
public class InboundOrderService {
	
	private final InboundOrderRepository inboundOrderRepository;

	@Autowired
	public InboundOrderService(InboundOrderRepository inboundOrderRepository) {this.inboundOrderRepository = inboundOrderRepository;}
	
	public InboundOrder save(InboundOrder inboundOrder) {
		return inboundOrderRepository.save(inboundOrder);
	}
	public List<InboundOrder> list() {
		return inboundOrderRepository.findAll();
	}
	public InboundOrder update(InboundOrder inboundOrder) {
		return inboundOrderRepository.saveAndFlush(inboundOrder);
	}
	public void delete(Long id) {
		inboundOrderRepository.deleteById(id);
	}
}
