package com.mercadolibre.demo.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class WareHouseDTO {

		@NotBlank(message = "{name.not.blank}")
        private String wareHouseName;
}
