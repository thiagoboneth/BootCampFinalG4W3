package com.mercadolibre.demo.dto.response;

import com.mercadolibre.demo.model.WareHouse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class WareHouseResponseDTO {

        private String name;

        public static WareHouseResponseDTO convertDTO(WareHouse wareHouse) {
            return new WareHouseResponseDTO(wareHouse.getWare_house_name());
        }
}
