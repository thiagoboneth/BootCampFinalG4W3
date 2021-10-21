package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.dto.SellerDTO;
import com.mercadolibre.demo.dto.response.SellerResponseDTO;
import com.mercadolibre.demo.model.Seller;
import com.mercadolibre.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-sellers/seller")
public class SellerController {

    private SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        super();
        this.sellerService = sellerService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<SellerResponseDTO> saveSeller(@Valid @RequestBody SellerDTO dto) {
        Seller seller = sellerService.save(dto.convertObjectSeller());
        return new ResponseEntity<>(SellerResponseDTO.convertSeller(seller), HttpStatus.CREATED);
    }
    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<List<Seller>> listSeller(){
        List<Seller> sellers = sellerService.list();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }
    @PutMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<Seller> updateSeller(@Valid @RequestBody Seller seller){
        Seller s = sellerService.update(seller);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> deleteSeller(@RequestParam Long idseller){
        sellerService.delete(idseller);
        return new ResponseEntity<>("Product successfully deleted", HttpStatus.OK);
    }
}
