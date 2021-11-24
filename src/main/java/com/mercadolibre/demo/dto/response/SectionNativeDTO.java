package com.mercadolibre.demo.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SectionNativeDTO {
    String nome;
    Long Current_quantity;
    String Ware_house_name;

}