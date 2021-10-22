package com.mercadolibre.demo.dto.response;

import com.mercadolibre.demo.model.Delegate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DelegateResponseDTO {

        private String name;
        private String lastname;


    public static DelegateResponseDTO convertDTO(Delegate delegate) {
            return new DelegateResponseDTO(delegate.getName(),delegate.getLastname());
        }
}
