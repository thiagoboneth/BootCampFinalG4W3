package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.Delegate;
import com.mercadolibre.demo.model.Section;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class DelegateDTO {

    @NotNull(message = "O nome não pode ser nulo")
    @NotEmpty(message = "O nome não pode estar vázio")
    private String name;
    @NotNull(message = "O sobrenome não pode ser nulo")
    @NotEmpty (message = "O sobrenome não pode estar vázio")
    private String lastname;
    @NotNull(message = "O sobrenome não pode ser nulo")
    @NotEmpty (message = "O sobrenome não pode estar vázio")
    private Section section_code;

    public Delegate convertObjectDelegate(){
        return new Delegate(name,lastname,section_code);
    }
}
