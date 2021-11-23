package com.mercadolibre.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDataDTO {

	private Long idBuyer;
	
	private String cep;
	
	private String logradouro;
	
	private String complemento;
	
	
	private String referencia;
	
    
	private String bairro;
	
	
	private String localidade;
	
	
	private String uf;
	
	
    private String email;
    
   
    private String telephone;
    

    private String cellphone;
    
    

	




    

    
}
