package com.mercadolibre.demo.service;


import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.dto.PriceDTO;
import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.repository.BatchStockRepository;
import com.mercadolibre.demo.repository.ItemOfProductRepository;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import com.mercadolibre.demo.repository.SalesAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemOfProductService {

    private ItemOfProductRepository itemOfProductRepository;
    private SalesAdRepository salesAdRepository;
    private PurchaseOrderRepository purchaseOrderRepository;
    private BatchStockRepository batchStockRepository;

    @Autowired
    public ItemOfProductService(ItemOfProductRepository itemOfProductRepository, SalesAdRepository salesAdRepository, PurchaseOrderRepository purchaseOrderRepository, BatchStockRepository batchStockRepository) {
        this.itemOfProductRepository = itemOfProductRepository;
        this.salesAdRepository = salesAdRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.batchStockRepository = batchStockRepository;
    }

    public ItemOfProduct save(ItemOfProductDTO dto) throws Exception {
        ItemOfProduct itemOfProduct = convertItemOfProduct(dto);
        return itemOfProductRepository.save(itemOfProduct);
    }

    public ItemOfProduct convertItemOfProduct(ItemOfProductDTO dto) throws Exception {
        Optional<SalesAd> salesAd = salesAdRepository.findById(dto.getSalesAd());
        if (salesAd.isPresent()){
             return new ItemOfProduct(dto.getQuantity(),salesAd.get());
        }
        throw new Exception("Erro no carrinho");
    }

    public List<ItemOfProductDTO> list(Long name) {
        List<ItemOfProductDTO> itemOfProductDTOS;
        return convertItemOfProductDTO(itemOfProductRepository.orderOfItem(name));
    }

    public List<ItemOfProductDTO> convertItemOfProductDTO(List<ItemOfProduct> list){
        List<ItemOfProductDTO> itemOfProductDTOS = new ArrayList<>();
        ItemOfProductDTO itemOfProductDTO = new ItemOfProductDTO();
        for (ItemOfProduct item: list) {
            itemOfProductDTO.setQuantity(item.getQuantity());
            itemOfProductDTO.setSalesAd(item.getSalesAd().getId());
            itemOfProductDTO.setNameProduct(item.getSalesAd().getProduct().getName());
            itemOfProductDTOS.add(itemOfProductDTO);
        }
        return itemOfProductDTOS;
    }

//    public void delete(Long id) {
//        itemOfProductRepository.deleteById(id);
//    }

    public void incrementQuantity(ItemOfProduct item, List<BatchStock> batchStockList) throws Exception {
        for (BatchStock batchStock : batchStockList) {
                batchStock.setCurrentQuantity(batchStock.getCurrentQuantity() + item.getQuantity());
                itemOfProductRepository.saveAndFlush(batchStock);
                return;
            }
    }

    public List<ItemOfProduct> delete(List<ItemOfProduct> lista) throws Exception {
        for (ItemOfProduct item: lista){
            List<BatchStock> batchStockList = batchStockRepository.batchStockList(item.getSalesAd().getId());
            if(batchStockList != null){
                incrementQuantity(item,batchStockList);
                itemOfProductRepository.deleteById(item.getId());
            }
        }
        return lista;
    }

}
