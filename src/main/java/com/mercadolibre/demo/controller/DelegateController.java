package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.DelegateDTO;
import com.mercadolibre.demo.model.Delegate;
import com.mercadolibre.demo.service.DelegateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/fresh-products/delegate")
public class DelegateController implements SecurityController {

	@Autowired
    private DelegateService delegateService;

    @PostMapping(value = "/save")
    private ResponseEntity<Delegate> saveDelegate(@Valid @RequestBody DelegateDTO dto) throws Exception {
    	try{
    		Delegate delegate = delegateService.save(dto);
    		return new ResponseEntity<>(delegate, HttpStatus.CREATED);
    	} catch (NoSuchElementException e) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<List<Delegate>> listDelegate(){
        List<Delegate> delegate = delegateService.list();
        return new ResponseEntity<>(delegate, HttpStatus.OK);
    }
    
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Delegate> updateDelegate(@Valid @RequestBody DelegateDTO dto, @PathVariable Long id) throws Exception{
        try {
        	Delegate delegate = delegateService.update(dto, id);
            return new ResponseEntity<>(delegate, HttpStatus.CREATED);
        } catch(NoSuchElementException e) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
