package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.UserDTO;
import com.mercadolibre.demo.dto.WareHouseDTO;
import com.mercadolibre.demo.model.Usuario;
import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.repository.UserRepository;
import com.mercadolibre.demo.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	public Usuario save(Usuario dto) {
		Usuario usuario = dto;
		return userRepository.save(usuario);
	}
}

