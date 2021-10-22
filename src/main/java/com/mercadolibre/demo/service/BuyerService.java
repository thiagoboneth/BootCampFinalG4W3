package com.mercadolibre.demo.service;

import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BuyerService {

    private BuyerRepository buyerRepository;

    @Autowired
    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    // CREATE
    public Buyer create(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

//    // READ
//    public List<Delegate> list() {
//        return wareHouseRepository.findAll();
//    }
//
//    // UPDATE
//    public WareHouse update(WareHouse wareHouse) {
//        return wareHouseRepository.saveAndFlush(wareHouse);
//    }
//
//     //DELETE
//	public void delete(WareHouse wareHouse) {
//        wareHouseRepository.deleteById(wareHouse.getId_ware_house());
//	}
}
