package com.mercadolibre.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mercadolibre.demo.model.Product;

import lombok.Getter;

@Getter
public class ProductDTO {
	
	@NotNull(message = "O nome não pode ser nulo")
	@NotEmpty (message = "O nome não pode estar vázio")
	private String name;
	@NotNull(message = "A descricao não pode ser nulo")
	@NotEmpty (message = "A descricao não pode estar vázio")
	private String description;
	
	
	public Product convertObject() {
		return new Product(name, description);
	}
}
