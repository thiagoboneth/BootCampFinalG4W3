package com.mercadolibre.demo.service;

import java.util.List;
import java.util.Optional;

import com.mercadolibre.demo.dto.InboundOrderDTO;
import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.Section;
import com.mercadolibre.demo.repository.BatchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mercadolibre.demo.model.InboundOrder;
import com.mercadolibre.demo.repository.InboundOrderRepository;
import com.mercadolibre.demo.repository.SectionRepository;

@Service
public class InboundOrderService {

	private InboundOrderRepository inboundOrderRepository;
	private BatchStockRepository batchStockRepository;
	private SectionRepository sectionRepository;	

	@Autowired
	public InboundOrderService(InboundOrderRepository inboundOrderRepository, BatchStockRepository batchStockRepository,
			SectionRepository sectionRepository) {
		this.inboundOrderRepository = inboundOrderRepository;
		this.batchStockRepository = batchStockRepository;
		this.sectionRepository = sectionRepository;
	}


	public InboundOrder save(InboundOrderDTO dto) throws Exception {
		InboundOrder inboundOrder = convertInboundOrderToDTO(dto);
		return inboundOrderRepository.save(inboundOrder);
	}

	public List<InboundOrder> list() {
		return inboundOrderRepository.findAll();
	}

	public Optional<InboundOrder> findById(Long id) {
		return inboundOrderRepository.findById(id);
	}

	public InboundOrder update(InboundOrderDTO dto, Long id) throws Exception {
		Optional<InboundOrder> existInbounOrder = findById(id);
		if (existInbounOrder.isPresent()) {
			InboundOrder inboundOrder = convertInboundOrderToDTO(dto);
			inboundOrder.setId(id);
			return inboundOrderRepository.saveAndFlush(inboundOrder);
		} else {
			throw new Exception("Id nao casdastrado");
		}
	}

	public void delete(Long id) {
		inboundOrderRepository.deleteById(id);
	}

	public Optional<BatchStock> obtemBatchStock(InboundOrderDTO dto) throws Exception {
		Optional<BatchStock> batchStock = batchStockRepository.findById(dto.getIdBatchStock());
		if(batchStock.isPresent()) {
			return batchStock;
		} else {
			throw new Exception("Id não cadastrado");
		}
	}

	public Optional<Section> obtemSection(InboundOrderDTO dto) throws Exception {
		Optional<Section> section = sectionRepository.findById(dto.getIdSection());
		if(section.isPresent()) {
			return section;
		} else {
			throw new Exception("Id não cadastrado");
		}
	}

	public InboundOrder convertInboundOrderToDTO(InboundOrderDTO dto) throws Exception {
		if (obtemBatchStock(dto).isPresent() && obtemSection(dto).isPresent() ) {
			return new InboundOrder(dto.getOrderDate(), obtemBatchStock(dto), obtemSection(dto));
		}
		throw new Exception("ID não cadastrado");
	}
}