package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.dto.BuyerDTO;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/fresh-products/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @RequestMapping(value = "/save")
    public ResponseEntity<Buyer> saveBuyer(@Valid @RequestBody BuyerDTO dto) {
        try {
            Buyer buyer = buyerService.save(dto);
            return new ResponseEntity<>(buyer, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<List<Buyer>> listBuyer() {
        List<Buyer> buyers = buyerService.list();
        return new ResponseEntity<>(buyers, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Buyer> updateBuyer(@Valid @RequestBody BuyerDTO buyerDTO, @PathVariable Long id) throws Exception {
        try {
            Buyer buyer = buyerService.update(buyerDTO, id);
            return new ResponseEntity<>(buyer, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteBuyer(@PathVariable Long idsBuyer) {
        try {
            buyerService.delete(idsBuyer);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Buyer successfully deleted", HttpStatus.OK);
    }
}