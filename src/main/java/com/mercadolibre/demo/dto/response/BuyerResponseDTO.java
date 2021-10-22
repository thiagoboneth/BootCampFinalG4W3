package com.mercadolibre.demo.dto.response;

import com.mercadolibre.demo.model.Buyer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BuyerResponseDTO {

    private String name;
    private String lastName;

        public static BuyerResponseDTO convertDTO(Buyer buyer) {
            return new BuyerResponseDTO(buyer.getName(),buyer.getLastName());
        }
}
