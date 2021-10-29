package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.PurchaseOrderDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.ItemOfProductRepository;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private ItemOfProductRepository itemOfProductRepository;
    @Autowired
    private BuyerService buyerService;


    public PurchaseOrder save(PurchaseOrderDTO dto) throws Exception {
        PurchaseOrder purchaseOrder;
        purchaseOrder = convertPurchaseToDTO(dto);
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> list() {
        return purchaseOrderRepository.findAll();
    }

    //implementar item do produto!
    public PurchaseOrder convertPurchaseToDTO(PurchaseOrderDTO dto) throws Exception {
        OrderStatus orderStatus = OrderStatus.CARRINHO;
        Optional<Buyer> buyer = buyerService.findById(dto.getBuyer());
        List<ItemOfProduct> itemOfProducts = itemOfProductRepository.findAll();
        if (buyer.isPresent()){
            return new PurchaseOrder(dto.getDate(), orderStatus, buyer.get(),itemOfProducts);
        }
        throw new Exception("Erro no carrinho");
    }
}