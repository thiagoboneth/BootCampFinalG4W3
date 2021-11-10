package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.dto.PurchaseOrderDTO;
import com.mercadolibre.demo.dto.response.PriceDTO;
import com.mercadolibre.demo.model.PurchaseOrder;
import com.mercadolibre.demo.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/fresh-products/purchaseorder")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping(value = "/add")
    private ResponseEntity<PriceDTO> addItem(@Valid @RequestBody PurchaseOrderDTO dto) throws Exception {
           PurchaseOrder purchaseOrder = purchaseOrderService.save(dto);
            return new ResponseEntity<>(purchaseOrderService.PriceLista(purchaseOrder.getItemOfProduct()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<List<PurchaseOrder>> listOfItem(){
        List<PurchaseOrder> purchaseOrders = purchaseOrderService.list();
        return new ResponseEntity<>(purchaseOrders, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<PurchaseOrder> updateItem(@Valid @RequestBody PurchaseOrderDTO dto, @PathVariable Long id) throws Exception{
            PurchaseOrder purchaseOrder = purchaseOrderService.update(dto, id);
            return new ResponseEntity<>(purchaseOrder, HttpStatus.CREATED);
    }
}
