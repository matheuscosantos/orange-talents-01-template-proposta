package com.zup.cartao.proposta.novaBiometria;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class NovaBiometriaResponse {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String idCartao;
    @JsonProperty
    private String fingerprint;
    @JsonProperty
    private LocalDateTime dataDeCriacao;

    public NovaBiometriaResponse(Biometria biometria) {
        this.id = biometria.getId();
        this.idCartao = biometria.getIdCartao();
        this.fingerprint = biometria.getFingerprint();
        this.dataDeCriacao = biometria.getDataDeCriacao();
    }

}
