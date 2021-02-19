package com.zup.cartao.proposta.avisoDeViagem;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class NotificacaoDeViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @NotEmpty
    private String idCartao;

    @NotNull
    @NotEmpty
    private String destino;

    @NotNull
    @Future
    private LocalDate dataDeTermino;

    @NotNull
    @CreationTimestamp
    private LocalDateTime instanteDeCriacao = LocalDateTime.now();

    @NotNull
    private String ipDoCliente;

    @NotNull
    @NotEmpty
    private String userAgent;

    @Deprecated
    public NotificacaoDeViagem() {
    }

    public NotificacaoDeViagem(@NotNull @NotEmpty String idCartao,
                               @NotNull @NotEmpty String destino,
                               @NotNull @Future LocalDate dataDeTermino,
                               @NotNull String ipDoCliente,
                               @NotNull @NotEmpty String userAgent) {

        Assert.isTrue(idCartao != null || idCartao.trim().equals(""), "O id do cartão é obrigatório");
        Assert.isTrue(destino != null || destino.trim().equals(""), "O destino é obrigatório");
        Assert.isTrue(dataDeTermino.isAfter(LocalDate.now()), "A data de término deve ser no futuro");
        Assert.isTrue(ipDoCliente != null,"O ip do cliente é obrigatório");
        Assert.isTrue(userAgent != null || userAgent.trim().equals(""), "O User Agent do cartão é obrigatório");

        this.idCartao = idCartao;
        this.destino = destino;
        this.dataDeTermino = dataDeTermino;
        this.ipDoCliente = ipDoCliente;
        this.userAgent = userAgent;
    }

    public Long getId() {
        return id;
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
