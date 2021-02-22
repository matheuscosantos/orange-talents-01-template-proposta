package com.zup.cartao.proposta.carteiraDigital;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NovaCarteiraDigitalResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String email;

    @JsonProperty
    private String idCartao;

    public NovaCarteiraDigitalResponse(CarteiraDigital carteiraDigital) {
        this.id = carteiraDigital.getId();
        this.email = carteiraDigital.getEmail();
        this.idCartao = carteiraDigital.getIdCartao();
    }
}
