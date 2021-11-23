package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViaCepDTO {
	
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
}
