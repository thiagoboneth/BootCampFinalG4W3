package com.mercadolibre.demo.service;


import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.dto.response.ProductInBatchStockDTO;
import com.mercadolibre.demo.dto.response.ProductInBathDTO;
import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.InboundOrder;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemOfProductService {

    private ItemOfProductRepository itemOfProductRepository;
    private SalesAdRepository salesAdRepository;
    private PurchaseOrderRepository purchaseOrderRepository;
    private BatchStockRepository batchStockRepository;
    private InboundOrderRepository inboundOrderRepository;
    private InboundOrderService inboundOrderService;

    @Autowired
    public ItemOfProductService(ItemOfProductRepository itemOfProductRepository, SalesAdRepository salesAdRepository, PurchaseOrderRepository purchaseOrderRepository, BatchStockRepository batchStockRepository, InboundOrderRepository inboundOrderRepository, InboundOrderService inboundOrderService) {
        this.itemOfProductRepository = itemOfProductRepository;
        this.salesAdRepository = salesAdRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.batchStockRepository = batchStockRepository;
        this.inboundOrderRepository = inboundOrderRepository;
        this.inboundOrderService = inboundOrderService;
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

    public void incrementQuantity(ItemOfProduct item, List<BatchStock> batchStockList) throws Exception {
        for (BatchStock batchStock : batchStockList) {
                batchStock.setCurrentQuantity(batchStock.getCurrentQuantity() + item.getQuantity());
                batchStockRepository.saveAndFlush(batchStock);
                return;
            }
    }

    public List<ItemOfProduct> delete(Long id) throws Exception {

        List<ItemOfProduct> lista = itemOfProductRepository.orderOfItem(id);

        for (ItemOfProduct item: lista){
            List<BatchStock> batchStockList = batchStockRepository.batchStockList(item.getSalesAd().getId());
            if(batchStockList != null){
                incrementQuantity(item,batchStockList);
                item.setQuantity(0L);
                itemOfProductRepository.saveAndFlush(item);
            }
        }
        return lista;
    }

    public List<ProductInBatchStockDTO> listProductOfBatchStock() {
        List<BatchStock> batchStockList = batchStockRepository.findAll();
        List<ProductInBatchStockDTO> productInBatchStockDTOList = new ArrayList<>();
        for (BatchStock batchStock:batchStockList) {
            ProductInBatchStockDTO productInBatchStockDTO = new ProductInBatchStockDTO();
            productInBatchStockDTO.setBatchStock(batchStock.getBatchNumber());
            productInBatchStockDTO.setNameProduct(batchStock.getIdSalesAd().getProduct().getName());
            productInBatchStockDTOList.add(productInBatchStockDTO);
        }
        return productInBatchStockDTOList;
    }
    
    public List<ProductInBathDTO> listOrderProduct(String name) {
        List<ProductInBathDTO> lista = new ArrayList<>();
        List<InboundOrder> inboundOrderList = inboundOrderRepository.findAll();

        for (InboundOrder inboundOrder1:inboundOrderList) {
            ProductInBathDTO productInBathDTO = new ProductInBathDTO();
            productInBathDTO.setIdbatch_number(inboundOrder1.getBatchStock().getBatchNumber());
            productInBathDTO.setName(inboundOrder1.getBatchStock().getIdSalesAd().getProduct().getName());
            productInBathDTO.setDue_date(inboundOrder1.getBatchStock().getDueDate());
            productInBathDTO.setCurrent_quantity(inboundOrder1.getBatchStock().getCurrentQuantity());
            productInBathDTO.setCategory(inboundOrder1.getSection().getCategory());
            productInBathDTO.setWare_house_name(inboundOrder1.getSection().getWareHouse().getWareHouseName());
            lista.add(productInBathDTO);
        }
        List<ProductInBathDTO> lista1 = new ArrayList<>();

        if(name.equals("L") ){
            lista1 = lista.stream()
                    .sorted(Comparator.comparingLong(ProductInBathDTO::getIdbatch_number))
                    .collect(Collectors.toList());
        }else
        if(name.equals("C") ){
           lista1 = lista.stream()
                    .sorted(Comparator.comparingLong(ProductInBathDTO::getCurrent_quantity))
                    .collect(Collectors.toList());
        }else
        if(name.equals("F")){

            lista1 = lista.stream()
                    .sorted(Comparator.comparing(ProductInBathDTO::getDue_date))
                    .collect(Collectors.toList());
        }
        return lista1;
    }


}
