package com.mercadolibre.demo.dto.response;

import com.mercadolibre.demo.model.Product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ProductResponseDTO {
	
	private String name;
	private String description;
	
	public static ProductResponseDTO convertDTO(Product product) {
		return new ProductResponseDTO(product.getName(), product.getDescription());
	}
	
}
