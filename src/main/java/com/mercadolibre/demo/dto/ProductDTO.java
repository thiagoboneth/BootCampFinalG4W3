package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


@Getter
@Setter
public class ProductDTO {
	
	@NotBlank(message = "{name.not.blank}")
	private String name;
	
	@NotBlank(message = "{description.not.blank}")
	private String description;
}
