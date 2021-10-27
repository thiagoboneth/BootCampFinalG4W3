package com.mercadolibre.demo.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DelegateResponseDTO {

        private String name;
        private String lastname;
}
