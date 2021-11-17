package com.mercadolibre.demo.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SectionNativeDTO {
    String nome;
    Long Current_quantity;
    String Ware_house_name;
}