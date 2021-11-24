package com.mercadolibre.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdressRestDTO {

    private String Idbuyer;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String fone1;
    private String fone2;
    private String Referencia;

    public AdressRestDTO(String idbuyer, String cep, String logradouro, String complemento, String bairro, String localidade, String uf, String fone1, String fone2, String referencia) {
        Idbuyer = idbuyer;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.fone1 = fone1;
        this.fone2 = fone2;
        Referencia = referencia;
    }
}
