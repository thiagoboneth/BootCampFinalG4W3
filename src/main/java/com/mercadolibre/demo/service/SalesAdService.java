package com.mercadolibre.demo.service;

import java.util.List;
import java.util.Optional;

import com.mercadolibre.demo.dto.SalesAdDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.ProductRepository;
import com.mercadolibre.demo.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.demo.repository.SalesAdRepository;

@Service
public class SalesAdService {
    @Autowired
    private SalesAdRepository salesAdRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;

    public SalesAdService(SalesAdRepository salesAdRepository, SellerRepository sellerRepository, ProductRepository productRepository) {
        this.salesAdRepository = salesAdRepository;
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    public SalesAd save(SalesAdDTO dto) throws Exception {
        SalesAd salesAd = convertSalesAdDTO(dto);
        return salesAdRepository.save(salesAd);
    }

    public List<SalesAd> list() {
        return salesAdRepository.findAll();
    }

    public Optional<SalesAd> findById(Long id) {
        return salesAdRepository.findById(id);
    }

    public SalesAd update(SalesAdDTO dto, Long id) throws Exception {
        Optional<SalesAd> existSalesAd = findById(id);
        if (existSalesAd.isPresent()) {
            SalesAd salesAd = convertSalesAdDTO(dto);
            salesAd.setId(id);
            return salesAdRepository.saveAndFlush(salesAd);
        } else {
            throw new Exception("Id nao casdastrado");
        }
    }

    public void delete(Long batchNumber) {
        salesAdRepository.deleteById(batchNumber);
    }

    public SalesAd convertSalesAdDTO(SalesAdDTO dto) throws Exception {
            return new SalesAd(dto.getVolume(), dto.getMinimumTemperature(), dto.getMaximumTemperature(),
                    dto.getPrice(), obtemSeller(dto),obtemProduct(dto));
    }
    public Optional<Seller> obtemSeller(SalesAdDTO dto) throws Exception {
        Optional<Seller> seller = sellerRepository.findById(dto.getIdSeller());
        if (seller.isPresent()){
            return seller;
        }else {
            throw new Exception("Id nao casdastrado");
        }
    }
    public Optional<Product> obtemProduct(SalesAdDTO dto) throws Exception {
        Optional<Product> product = productRepository.findById(dto.getIdProduct());
        if (product.isPresent()){
            return product;
        }else {
            throw new Exception("Id nao casdastrado");
        }
    }

}
