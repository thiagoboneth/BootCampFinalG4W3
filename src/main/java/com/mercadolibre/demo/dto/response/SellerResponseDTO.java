package com.mercadolibre.demo.dto.response;

import com.mercadolibre.demo.model.Seller;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerResponseDTO {
    private String name;
    private String lastName;

    public static SellerResponseDTO convertSeller(Seller seller){
        return new SellerResponseDTO(seller.getName(), seller.getLastname());
    }
}
