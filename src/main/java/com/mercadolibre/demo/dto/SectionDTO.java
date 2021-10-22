package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.Section;
import com.mercadolibre.demo.model.WareHouse;
import lombok.Getter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class SectionDTO {

    @NotNull(message = "A capacidade não pode ser nula")
    @NotEmpty (message = "A capacidade não pode estar vázio")
    private int capacity;

    @NotNull(message = "A WareHouse não pode ser nula")
    @NotEmpty (message = "A WareHouse não pode estar vázia")
    private WareHouse wareHouse;


    public Section convertObject() {
        return new Section(capacity,wareHouse);
    }
}
