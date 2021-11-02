package com.mercadolibre.demo.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ErrorResponse {
	
    private String message;
    private int code;
    private String status;
    private String objectName;
    private List<ErrorObject> errors;

}
