package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.DiscontBlackFridayDTO;
import com.mercadolibre.demo.dto.response.StockByWareHouseDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.DiscontBlackFridayRepository;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.mercadolibre.demo.model.CuponsBlackFriday.*;

@Service
public class DiscontBlackFridayService {

    private PurchaseOrderRepository purchaseOrderRepository;
    private DiscontBlackFridayRepository discontBlackFridayRepoitory;

    @Autowired
    public DiscontBlackFridayService(PurchaseOrderRepository purchaseOrderRepository, DiscontBlackFridayRepository discontBlackFridayRepoitory) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.discontBlackFridayRepoitory = discontBlackFridayRepoitory;
    }

    public DiscontBlackFriday registerDiscontBlackFriday(CuponsBlackFriday cupomBlackFriday ,Long idCart) throws Exception {
        Optional<PurchaseOrder> existCart = findByIDcart(idCart);
        List<StockByWareHouseDTO> stockByWareHouseDTOS = categoryForDiscount(idCart);
        if (existCart.isPresent()) {
            switch (cupomBlackFriday) {
                case FRIOSBLACK:
                    DiscontBlackFridayDTO discontBlackFridayFrios = new DiscontBlackFridayDTO(FRIOSBLACK,
                            sumDB(idCart), "15%", porcentage(idCart, 15F));
                    return discontBlackFridayRepoitory.save(convertDiscontBlackFriday(discontBlackFridayFrios));

                case CONGELADOSBLACK:
                    DiscontBlackFridayDTO discontBlackFridayCongelados = new DiscontBlackFridayDTO(SEMCUPOM,
                            sumDB(idCart), "10%", porcentage(idCart, 20F));
                    return discontBlackFridayRepoitory.save(convertDiscontBlackFriday(discontBlackFridayCongelados));

                case FRESCOSBLACK:
                    DiscontBlackFridayDTO discontBlackFridayFrescos = new DiscontBlackFridayDTO(FRESCOSBLACK,
                            sumDB(idCart), "25%", porcentage(idCart, 25F));
                    return discontBlackFridayRepoitory.save(convertDiscontBlackFriday(discontBlackFridayFrescos));

                case SEMCUPOM:
                    DiscontBlackFridayDTO discontBlackFridaySemCupom = new DiscontBlackFridayDTO(SEMCUPOM,
                            sumDB(idCart), "0%", porcentage(idCart, 0F));
                    return discontBlackFridayRepoitory.save(convertDiscontBlackFriday(discontBlackFridaySemCupom));
                default:
                    throw new Exception("Cupom Inválido");
            }
        } else {
            throw new Exception("Carrinho inexistente");

        }
    }

    public List<DiscontBlackFriday> listValueProductsWithdiscont() {
        return discontBlackFridayRepoitory.findAll();
    }

    public DiscontBlackFriday updateDiscontBlackFriday(DiscontBlackFridayDTO dto, Long id) throws Exception {
        Optional<DiscontBlackFriday> existDiscontBlack = findById(id);
        if (existDiscontBlack.isPresent()) {
            DiscontBlackFriday discontBlackFriday = convertDiscontBlackFriday(dto);
            discontBlackFriday.setIdDiscontBlackFriday(id);
            return discontBlackFridayRepoitory.saveAndFlush(discontBlackFriday);
        } else {
            throw new Exception("Id não cadastrado");
        }
    }

    public DiscontBlackFriday deleteDiscontBlackFriday(Long id) throws Exception {
        Optional<DiscontBlackFriday> existDiscontBlack = findById(id);
        if (existDiscontBlack.isPresent()) {
        }
        return deleteDiscontBlackFriday(id);
    }

            public Optional<DiscontBlackFriday> findById(Long id) {
        return discontBlackFridayRepoitory.findById(id);
    }

    public Optional<PurchaseOrder> findByIDcart(Long idCart) {
        return purchaseOrderRepository.findById(idCart);
    }
    public List<StockByWareHouseDTO> categoryForDiscount(Long idCart){
        return discontBlackFridayRepoitory.categoryForDiscount(idCart);
    }

    private Double sumDB(Long idCart) {
        Double price = discontBlackFridayRepoitory.price(idCart);
        Long quantity = discontBlackFridayRepoitory.quantity(idCart);

        return quantity * price;
    }

    private Double porcentage(Long idCart, Float porcentage) {
        return (sumDB(idCart) * 100) / porcentage;
    }

    public DiscontBlackFridayDTO convertDiscontBlackFridayDTO(DiscontBlackFriday discontBlackFriday) {
        return new DiscontBlackFridayDTO(discontBlackFriday.getCuponsBlackFriday(), discontBlackFriday.getValueOfCart(),
                discontBlackFriday.getCupomValue(), discontBlackFriday.getValueWithDescont());
    }

    public DiscontBlackFriday convertDiscontBlackFriday(DiscontBlackFridayDTO dto) {
        return new DiscontBlackFriday(dto.getCuponsBlackFriday(), dto.getValueOfCart(), dto.getCupomValue(), dto.getValueWithDescont());
    }
}
