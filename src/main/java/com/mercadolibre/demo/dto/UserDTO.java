package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDTO {

		@NotBlank(message = "{user.not.blank}")
        private String user;

        @NotBlank(message = "{senha.not.blank}")
        private String senha;

        private boolean ativo = true;
}
