package com.mercadolibre.demo.service;

import com.mercadolibre.demo.model.Delegate;
import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.repository.DelegateRepository;
import com.mercadolibre.demo.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DelegateService {

    private DelegateRepository delegateRepository;

    @Autowired
    public DelegateService(DelegateRepository delegateRepository) {
        this.delegateRepository = delegateRepository;
    }

    // CREATE
    public Delegate create(Delegate delegate) {
        return delegateRepository.save(delegate);
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
