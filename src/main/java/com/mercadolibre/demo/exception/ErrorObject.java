package com.mercadolibre.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorObject {
    private final String message;
    private final String field;
    private final Object parameter;
}
