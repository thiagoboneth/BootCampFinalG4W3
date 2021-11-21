package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.UserDTO;
import com.mercadolibre.demo.dto.WareHouseDTO;
import com.mercadolibre.demo.model.Usuario;
import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.repository.UserRepository;
import com.mercadolibre.demo.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
	@Autowired
	private UserRepository repository;


	public Usuario save(Usuario dto) {
		Usuario usuario = dto;
		Integer quantity = repository.findAll().size();
		if(usuario.getUser().equals("Meli") && quantity == 0) {
			usuario.isAccountNonExpired();
			usuario.setAtivo(true);
			return repository.save(usuario);
		}
		return null;
	}
}

