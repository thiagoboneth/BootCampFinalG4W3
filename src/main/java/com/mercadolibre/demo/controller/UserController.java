package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.model.Usuario;
import com.mercadolibre.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/user")
public class UserController implements SecurityController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/save")
	public ResponseEntity<Usuario> saveUser(@Valid @RequestBody Usuario dto){
		Usuario usuario = userService.save(dto);
		return new ResponseEntity<>(usuario,HttpStatus.CREATED);
	}
}
