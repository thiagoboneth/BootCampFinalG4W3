package com.mercadolibre.demo.service;

import com.mercadolibre.demo.model.Seller;
import com.mercadolibre.demo.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }
    public List<Seller> list() {
        return sellerRepository.findAll();
    }
    public Seller update(Seller seller) {
        return sellerRepository.saveAndFlush(seller);
    }
    public void delete(Long id) {
        sellerRepository.deleteById(id);
    }
}
