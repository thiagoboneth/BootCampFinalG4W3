package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.CuponsBlackFriday;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscontBlackFridayDTO {

    private CuponsBlackFriday cuponsBlackFriday;
    private Double valueOfCart;
    private String cupomValue;
    private Double valueWithDescont;


}

