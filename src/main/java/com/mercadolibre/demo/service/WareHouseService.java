package com.mercadolibre.demo.service;

import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WareHouseService {

    private WareHouseRepository wareHouseRepository;

    @Autowired
    public WareHouseService(WareHouseRepository wareHouseRepository) {
        this.wareHouseRepository = wareHouseRepository;
    }

    // CREATE
    public WareHouse create(WareHouse wareHouse) {
        return wareHouseRepository.save(wareHouse);
    }

//    // READ
//    public List<WareHouse> list() {
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
