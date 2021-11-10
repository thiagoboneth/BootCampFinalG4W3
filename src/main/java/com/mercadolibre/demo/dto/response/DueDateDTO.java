package com.mercadolibre.demo.dto.response;


import lombok.Data;

import java.time.LocalDate;

@Data
public class DueDateDTO {

    Long batchNumber;
    String productId;
    String productTypeID;
    LocalDate dueDate;
    Long quantity;

}
