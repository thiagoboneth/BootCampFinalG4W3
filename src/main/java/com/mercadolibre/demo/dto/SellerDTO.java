package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SellerDTO {

	
	@NotBlank(message = "{name.not.blank}")
    private String name;

	@NotBlank(message = "{description.not.blank}")
    private String lastname;
}
