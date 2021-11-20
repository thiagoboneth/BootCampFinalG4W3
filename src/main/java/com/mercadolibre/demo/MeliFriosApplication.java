package com.mercadolibre.demo;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MeliFriosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeliFriosApplication.class, args);
    }
}