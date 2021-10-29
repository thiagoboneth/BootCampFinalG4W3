package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.repository.ItemOfProductRepository;
import com.mercadolibre.demo.repository.SalesAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemOfProductService {

    @Autowired
    private ItemOfProductRepository itemOfProductRepository;

    @Autowired
    private SalesAdRepository salesAdRepository;

    public ItemOfProduct save(ItemOfProductDTO dto) throws Exception {
        ItemOfProduct itemOfProduct = convertParaObject(dto);
        return itemOfProductRepository.save(itemOfProduct);
    }

    public List<ItemOfProduct> list() {
        return itemOfProductRepository.findAll();
    }

    public Optional<ItemOfProduct> findById(Long id) {
        return itemOfProductRepository.findById(id);
    }

    public ItemOfProduct update(ItemOfProductDTO dto, Long id) throws Exception {

        Optional<ItemOfProduct> existsBatchStok = findById(id);
        if (existsBatchStok.isPresent()) {
            ItemOfProduct itemOfProduct = convertParaObject(dto);
            itemOfProduct.setId(id);
            return itemOfProductRepository.saveAndFlush(itemOfProduct);
        }
        throw new Exception("Id nao casdastrado");
    }

    public void delete(Long idproduct) {
        itemOfProductRepository.deleteById(idproduct);
    }
    public ItemOfProduct convertParaObject(ItemOfProductDTO dto) throws Exception {
        Optional<SalesAd> salesAd = salesAdRepository.findById(dto.getIdsalesAd());
        if (salesAd.isPresent()) {
            return new ItemOfProduct(dto.getQuantity(), salesAd.get());
        } else {
            throw new Exception("Id nao casdastrado");

        }
}
}
