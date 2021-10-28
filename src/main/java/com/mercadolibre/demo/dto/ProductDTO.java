package com.mercadolibre.demo.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	
	@NotBlank(message = "{name.not.blank}")
	private String name;
	
	@NotBlank(message = "{description.not.blank}")
	private String description;
}
