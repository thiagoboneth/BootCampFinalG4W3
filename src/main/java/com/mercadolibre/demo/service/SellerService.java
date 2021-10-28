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

    @Autowired
    SellerRepository sellerRepository;

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
        Seller seller;
        Optional<Seller> existSaller = findById(id);
        if (existSaller.isPresent()) {
            seller = convertSellerDTO(dto);
            seller.setIdseller(id);
            return sellerRepository.saveAndFlush(seller);
        } else {
            throw new Exception("Id nao casdastrado");
        }
    }

    public void delete(Long id) {
        sellerRepository.deleteById(id);
    }

    public Seller convertSellerDTO(SellerDTO dto) {
        return new Seller(dto.getName(), dto.getLastname());
    }

}

