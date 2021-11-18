package com.mercadolibre.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductInBathDTO {
    private String name;
    private LocalDate due_date;
    private Long current_quantity;
    private String category;
    private String ware_house_name;
    private Long idbatch_number;
}
