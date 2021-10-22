package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.Buyer;
import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class BuyerDTO {

    @NotNull(message = "O nome não pode ser nulo")
    @NotEmpty(message = "O nome não pode estar vázio")
    private String name;
    @NotNull(message = "O sobrenome não pode ser nulo")
    @NotEmpty (message = "O sobrenome não pode estar vázio")
    private String lastname;

    public Buyer convertObjectBuyer(){
        return new Buyer(name,lastname);
    }
}
