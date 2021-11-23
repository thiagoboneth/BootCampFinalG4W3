package com.mercadolibre.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyerDataSaveDTO {
	
	@NotNull(message = "{id.not.null}")
	private Long idBuyer;
	
	@NotBlank(message = "{cep.not.blank}")
	private String cep;

	@NotBlank(message = "{referecia.not.blank}")
	private String referencia;
	
	@NotBlank(message = "{email.not.blank}")
    private String email;
    
	@NotBlank(message = "{telephone.not.blank}")
    private String telephone;
    
	@NotBlank(message = "{cellphone.not.blank}")
    private String cellphone;
}
