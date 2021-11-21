package com.mercadolibre.demo.controller;


import com.mercadolibre.demo.config.TokenService;
import com.mercadolibre.demo.dto.TokenDTO;
import com.mercadolibre.demo.model.Usuario;
import com.mercadolibre.demo.request.LoginRequest;
import com.mercadolibre.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<TokenDTO> realizaAutenticacao(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.converter();
        Authentication authentication = manager.authenticate(dadosLogin);
        String token = tokenService.geraToken(authentication);
        return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

    }

    @PostMapping(value = "/save")
    public ResponseEntity<Usuario> saveUser(@Valid @RequestBody Usuario dto){
        try {
            Usuario usuario = userService.save(dto);
            return new ResponseEntity<>(usuario,HttpStatus.CREATED);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
