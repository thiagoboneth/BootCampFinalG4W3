package com.mercadolibre.demo.dto.response;

import com.mercadolibre.demo.model.WareHouse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SectionResponseDTO {
    private int capacity;
    private WareHouse wareHouse;
}
