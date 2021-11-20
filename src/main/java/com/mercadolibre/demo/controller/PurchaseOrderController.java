package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.PurchaseOrderDTO;
import com.mercadolibre.demo.dto.response.PriceDTO;
import com.mercadolibre.demo.model.PurchaseOrder;
import com.mercadolibre.demo.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/purchaseorder")
public class PurchaseOrderController implements SecurityController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping(value = "/add")
    private ResponseEntity<PriceDTO> addItem(@Valid @RequestBody PurchaseOrderDTO dto) throws Exception {
           PurchaseOrder purchaseOrder = purchaseOrderService.save(dto);
            return new ResponseEntity<>(purchaseOrderService.priceList(purchaseOrder.getItemOfProduct()), HttpStatus.CREATED);
    }
}
