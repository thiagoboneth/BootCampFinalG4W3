package com.mercadolibre.demo.dto;

import javax.validation.constraints.NotBlank;

import com.mercadolibre.demo.model.Product;

import lombok.Getter;

@Getter
public class ProductDTO {
	
	@NotBlank(message = "{name.not.blank}")
	private String name;
	
	@NotBlank(message = "{description.not.blank}")
	private String description;
	
	
	public Product convertObject() {
		return new Product(name, description);
	}
}
