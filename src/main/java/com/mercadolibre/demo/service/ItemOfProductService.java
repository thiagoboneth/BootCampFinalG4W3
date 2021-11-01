package com.mercadolibre.demo.service;


import com.mercadolibre.demo.dto.ItemOfProduct2DTO;
import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.PurchaseOrder;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.repository.ItemOfProductRepository;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
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
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public ItemOfProduct save(ItemOfProductDTO dto) throws Exception {
        ItemOfProduct itemOfProduct = convertItemOfProduct(dto);
        return itemOfProductRepository.save(itemOfProduct);
    }

/*    public ItemOfProduct convertItemOfProduct(Long id,ItemOfProductDTO dto) throws Exception {
        Optional<SalesAd> salesAd = salesAdRepository.findById(dto.getSalesAd());
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(dto.getPurchaseOrder());
        if (salesAd.isPresent()){
            // return new ItemOfProduct(dto.getQuantity(),salesAd, purchaseOrder);
            return null;

        }
        throw new Exception("Erro no carrinho");
    }*/
    /*    public ItemOfProduct convertItemOfProduct(ItemOfProduct2DTO dto) throws Exception {
        Optional<SalesAd> salesAd = salesAdRepository.findById(dto.getSalesAd());
        if (salesAd.isPresent()){
            return new ItemOfProduct(dto.getQuantity(),salesAd.get());

        }
        throw new Exception("Erro no carrinho");
    }*/
    public ItemOfProduct convertItemOfProduct(ItemOfProductDTO dto) throws Exception {
        Optional<SalesAd> salesAd = salesAdRepository.findById(dto.getSalesAd());
       // Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(dto.getPurchaseOrder());
        if (salesAd.isPresent()){
             return new ItemOfProduct(dto.getQuantity(),salesAd.get());
        }
        throw new Exception("Erro no carrinho");
    }
}
