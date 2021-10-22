package com.mercadolibre.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.model.Seller;

import lombok.Getter;

@Getter
public class SalesAdDTO {
	
	@NotNull(message = "O volume não pode ser nulo")
	@NotEmpty (message = "O volume não pode estar vázio")
    private Float volume;
    
	@NotNull(message = "A temperatura mínima não pode ser nula")
	@NotEmpty (message = "A temperatura mínima não pode estar vázia")
    private Float minimumTemperature;
    
	@NotNull(message = "A temperatura máxima não pode ser nula")
	@NotEmpty (message = "A temperatura máxima não pode estar vázia")
    private Float maximumTemperature;
    
	@NotNull(message = "O preco não pode ser nulo")
	@NotEmpty (message = "O preco não pode estar vázio")
    private Double price;

	@NotNull(message = "O vendedor não pode ser nulo")
	@NotEmpty (message = "O vendedor não pode estar vázio")
    private Seller seller;

	@NotNull(message = "O vendedor não pode ser nulo")
	@NotEmpty (message = "O vendedor não pode estar vázio")
    private Product product;
	
	
	public SalesAd convertObject() {
		return new SalesAd(volume, minimumTemperature, maximumTemperature, price, seller, product);
	}
}
