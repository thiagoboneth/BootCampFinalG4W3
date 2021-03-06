package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.BuyerDTO;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class BuyerService {

	private BuyerRepository buyerRepository;

	@Autowired
	public BuyerService(BuyerRepository buyerRepository) {
		this.buyerRepository = buyerRepository;
	}

	public Buyer save(BuyerDTO dto) {
		Buyer buyer = convertBuyerToDTO(dto);
		return buyerRepository.save(buyer);
	}

	public List<Buyer> list() {
		return buyerRepository.findAll();
	}

	public Optional<Buyer> findById(Long id) {
		return buyerRepository.findById(id);
	}

	public Buyer update(BuyerDTO dto, Long id) throws Exception {
		Optional<Buyer> existBuyer = findById(id);
		if (existBuyer.isPresent()) {
			Buyer buyer = convertBuyerToDTO(dto);
			buyer.setIdBuyer(id);
			return buyerRepository.saveAndFlush(buyer);
		} else {
			throw new Exception("Id não cadastrado");
		}
	}

	public void delete(Long id) throws Exception {
		Optional<Buyer> existBuyer = findById(id);
		if (existBuyer.isPresent()) {
			buyerRepository.deleteById(id);
		} else {
			throw new Exception("Id não cadastrado");
		}
	}
	
	public Buyer convertBuyerToDTO(BuyerDTO dto) {
		return new Buyer(dto.getName(), dto.getLastname());
	}
}
