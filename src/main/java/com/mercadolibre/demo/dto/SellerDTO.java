package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.Seller;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class SellerDTO {

    @NotNull(message = "O nome não pode ser nulo")
    @NotEmpty(message = "O nome não pode estar vázio")
    private String name;
    @NotNull(message = "O sobrenome não pode ser nulo")
    @NotEmpty (message = "O sobrenome não pode estar vázio")
    private String lastname;

    public Seller convertObjectSeller(){
        return new Seller(name,lastname);
    }
}
