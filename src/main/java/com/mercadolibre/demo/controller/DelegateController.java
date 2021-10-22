package com.mercadolibre.demo.controller;


import com.mercadolibre.demo.dto.DelegateDTO;
import com.mercadolibre.demo.dto.response.DelegateResponseDTO;
import com.mercadolibre.demo.model.Delegate;
import com.mercadolibre.demo.service.DelegateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/delegate")
public class DelegateController {

    private DelegateService delegateService;

    @Autowired
    public DelegateController(DelegateService delegateService) {
        super();
        this.delegateService = delegateService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<DelegateDTO> createDelegate(@Valid @RequestBody DelegateDTO dto){
        Delegate delegate = delegateService.create(dto.convertObjectDelegate());
        return new ResponseEntity(DelegateResponseDTO.convertDTO(delegate),HttpStatus.OK);
    }
}
