package com.mercadolibre.demo.service;

import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.repository.SalesAdBuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesAdBuyerService {

    @Autowired
    private SalesAdBuyerRepository salesAdBuyerRepository;


    public ItemOfProduct save(ItemOfProduct itemOfProduct) {
        return salesAdBuyerRepository.save(itemOfProduct);
    }

    public List<ItemOfProduct> list() {
        return salesAdBuyerRepository.findAll();
    }

    public ItemOfProduct update(ItemOfProduct itemOfProduct) {
        return salesAdBuyerRepository.saveAndFlush(itemOfProduct);
    }

    public void delete(Long idproduct) {
        salesAdBuyerRepository.deleteById(idproduct);
    }
}
