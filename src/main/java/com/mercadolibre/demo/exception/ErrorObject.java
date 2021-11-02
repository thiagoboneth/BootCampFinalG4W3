package com.mercadolibre.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorObject {
    private String message;
    private String field;
    private Object parameter;
}
