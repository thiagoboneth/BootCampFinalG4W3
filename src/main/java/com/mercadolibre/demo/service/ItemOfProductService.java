package com.mercadolibre.demo.service;


import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.repository.ItemOfProductRepository;
import com.mercadolibre.demo.repository.SalesAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemOfProductService {

    @Autowired
    private ItemOfProductRepository itemOfProductRepository;
    @Autowired
    private SalesAdRepository salesAdRepository;
    @Autowired
    private SalesAdService salesAdService;

    public ItemOfProduct save(ItemOfProductDTO dto) throws Exception {
        ItemOfProduct itemOfProduct = convertItemToDTO(dto);
        return itemOfProductRepository.save(itemOfProduct);
    }

    private ItemOfProduct convertItemToDTO(ItemOfProductDTO dto) throws Exception {
        Optional<SalesAd> salesAd = salesAdService.findById(dto.getSalesAd().getId());
        if (salesAd.isPresent()){
            return new ItemOfProduct(dto.getQuantity(),salesAd.get());
        }else {
            throw new Exception("Anuncio inoperante");
        }
    }
}
