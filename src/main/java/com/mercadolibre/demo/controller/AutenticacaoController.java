package com.mercadolibre.demo.controller;


import com.mercadolibre.demo.config.TokenService;
import com.mercadolibre.demo.dto.TokenDTO;
import com.mercadolibre.demo.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> realizaAutenticacao(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.converter();
        Authentication authentication = manager.authenticate(dadosLogin);
        String token = tokenService.geraToken(authentication);
        return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

    }
}
