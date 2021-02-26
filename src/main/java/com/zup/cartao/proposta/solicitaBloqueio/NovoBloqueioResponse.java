package com.zup.cartao.proposta.solicitaBloqueio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class NovoBloqueioResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String idCartao;

    @JsonProperty
    private LocalDateTime instanteDoBloqueio;

    @JsonProperty
    private String ip;

    @JsonProperty
    private String userAgent;

    public NovoBloqueioResponse(Bloqueio bloqueio) {
        this.id = bloqueio.getId();
        this.idCartao = bloqueio.getIdCartao();
        this.instanteDoBloqueio = bloqueio.getInstanteDoBloqueio();
        this.ip = bloqueio.getIp();
        this.userAgent = bloqueio.getUserAgent();
    }
}
