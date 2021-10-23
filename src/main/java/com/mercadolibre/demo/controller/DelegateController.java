package com.mercadolibre.demo.controller;


import com.mercadolibre.demo.dto.DelegateDTO;
import com.mercadolibre.demo.dto.response.DelegateResponseDTO;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.model.Delegate;
import com.mercadolibre.demo.service.DelegateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/delegate")
public class DelegateController {

    private DelegateService delegateService;

    @Autowired
    public DelegateController(DelegateService delegateService) {this.delegateService = delegateService;}

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<DelegateDTO> createDelegate(@RequestBody DelegateDTO dto){
        Delegate delegate = delegateService.save(dto.convertObjectDelegate());
        return new ResponseEntity(DelegateResponseDTO.convertDTO(delegate),HttpStatus.OK);
    }
    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<List<Delegate>> listDelegate(){
        List<Delegate> delegate = delegateService.list();
        return new ResponseEntity<>(delegate, HttpStatus.OK);
    }
    @PutMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<Delegate> updateDelegate(@Valid @RequestBody Delegate delegate){
        Delegate s = delegateService.update(delegate);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> deleteDelegate(@RequestParam Long idsDelegate){
        delegateService.delete(idsDelegate);
        return new ResponseEntity<>("Delegate successfully deleted", HttpStatus.OK);
    }
}
