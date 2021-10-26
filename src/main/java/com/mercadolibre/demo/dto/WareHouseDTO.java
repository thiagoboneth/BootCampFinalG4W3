package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.WareHouse;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class WareHouseDTO {

    @NotNull(message = "O nome do deposito não pode ser nulo")
    @NotEmpty(message = "O nome do deposito não pode estar vázio")
    @Column(name = "ware_house_name")
    private String ware_house_name;

    public WareHouse convertObject() {
        return new WareHouse(ware_house_name);
    }
}
