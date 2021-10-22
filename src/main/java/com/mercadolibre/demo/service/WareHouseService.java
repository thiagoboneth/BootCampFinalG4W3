package com.mercadolibre.demo.service;

import com.mercadolibre.demo.model.Seller;
import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public WareHouse save(WareHouse wareHouse) {
        return wareHouseRepository.save(wareHouse);
    }
    public List<WareHouse> list() {
        return wareHouseRepository.findAll();
    }
    public WareHouse update(WareHouse wareHouse) {
        return wareHouseRepository.saveAndFlush(wareHouse);
    }
    public void delete(Long id) {
        wareHouseRepository.deleteById(id);
    }
}
