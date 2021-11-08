package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.SellerDTO;
import com.mercadolibre.demo.model.Seller;
import com.mercadolibre.demo.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

	private SellerRepository sellerRepository;

	@Autowired
	public SellerService(SellerRepository sellerRepository) {
		this.sellerRepository = sellerRepository;
	}


	public Seller save(SellerDTO dto) {
		Seller seller = convertSellerDTO(dto);
		return sellerRepository.save(seller);
	}

	public List<Seller> list() {
		return sellerRepository.findAll();
	}

	public Optional<Seller> findById(Long id) {
		return sellerRepository.findById(id);
	}

	public Seller update(SellerDTO dto, Long id) throws Exception {
		Optional<Seller> existSaller = findById(id);
		if (existSaller.isPresent()) {
			Seller  seller = convertSellerDTO(dto);
			seller.setIdseller(id);
			return sellerRepository.saveAndFlush(seller);
		} else {
			throw new Exception("Id não cadastrado");
		}
	}

	public void delete(Long id) throws Exception {
		Optional<Seller> existSaller = findById(id);
		if (existSaller.isPresent()) {
			sellerRepository.deleteById(id);
		} else {
			throw new Exception("Id não cadastrado");
		}
	}

	public Seller convertSellerDTO(SellerDTO dto) {
		return new Seller(dto.getName(), dto.getLastname());
	}

}

