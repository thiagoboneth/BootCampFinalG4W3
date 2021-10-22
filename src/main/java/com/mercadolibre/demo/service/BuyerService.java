package com.mercadolibre.demo.service;

import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BuyerService {

    private BuyerRepository buyerRepository;

    @Autowired
    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public Buyer create(Buyer buyer) {
        return buyerRepository.save(buyer);
    }
    public Buyer save(Buyer buyer) {
        return buyerRepository.save(buyer);
    }
    public List<Buyer> list() {
        return buyerRepository.findAll();
    }
    public Buyer update(Buyer buyer) {
        return buyerRepository.saveAndFlush(buyer);
    }
    public void delete(Long id) {buyerRepository.deleteById(id);}
}
