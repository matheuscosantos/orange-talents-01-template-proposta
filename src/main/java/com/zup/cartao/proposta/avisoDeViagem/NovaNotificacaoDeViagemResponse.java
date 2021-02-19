package com.zup.cartao.proposta.avisoDeViagem;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NovaNotificacaoDeViagemResponse {

    @JsonProperty
    private String idCartao;

    @JsonProperty
    private String destino;

    @JsonProperty
    private LocalDate dataDeTermino;

    @JsonProperty
    private LocalDateTime instanteDeCriacao = LocalDateTime.now();

    @JsonProperty
    private String ipDoCliente;

    @JsonProperty
    private String userAgent;

    public NovaNotificacaoDeViagemResponse(NotificacaoDeViagem notificacaoDeViagem) {
        this.idCartao = notificacaoDeViagem.getIdCartao();
        this.destino = notificacaoDeViagem.getDestino();
        this.dataDeTermino = notificacaoDeViagem.getDataDeTermino();
        this.instanteDeCriacao = notificacaoDeViagem.getInstanteDeCriacao();
        this.ipDoCliente = notificacaoDeViagem.getIpDoCliente();
        this.userAgent = notificacaoDeViagem.getUserAgent();
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataDeTermino() {
        return dataDeTermino;
    }

    public LocalDateTime getInstanteDeCriacao() {
        return instanteDeCriacao;
    }

    public String getIpDoCliente() {
        return ipDoCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
