package com.mercadolibre.demo.service;

import java.util.List;
import java.util.Optional;
import com.mercadolibre.demo.dto.InboundOrderDTO;
import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.Section;
import com.mercadolibre.demo.repository.BatchStockRepository;
import com.mercadolibre.demo.repository.SectionRepositotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mercadolibre.demo.model.InboundOrder;
import com.mercadolibre.demo.repository.InboundOrderRepository;

@Service
public class InboundOrderService {

	@Autowired
	private InboundOrderRepository inboundOrderRepository;
	@Autowired
	private BatchStockRepository batchStockRepository;
	@Autowired
	private SectionRepositotory sectionRepositotory;

	
	public InboundOrder save(InboundOrderDTO dto) throws Exception {
		InboundOrder inboundOrder;
		inboundOrder = convertInboundOrderToDTO(dto);
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

	public InboundOrder convertInboundOrderToDTO(InboundOrderDTO dto) throws Exception {
		Optional<BatchStock> batchStock = batchStockRepository.findById(dto.getIdBatchStock());
		Optional<Section> section = sectionRepositotory.findById(dto.getIdSection());
		if (batchStock.isPresent() && section.isPresent()){
			return new InboundOrder(dto.getOrderDate(), batchStock.get(), section.get());
		}
		throw new Exception("ID não cadastrado");
	}
}