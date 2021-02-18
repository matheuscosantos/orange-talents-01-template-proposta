package com.zup.cartao.proposta.solicitaBloqueio;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @NotEmpty
    private String idCartao;

    @NotNull
    @CreationTimestamp
    private LocalDateTime instanteDoBloqueio;

    @NotNull
    @NotEmpty
    private String ip;

    @NotNull
    @NotEmpty
    private String userAgent;

    public Bloqueio(String idCartao,
                    String userAgent,
                    String ip
                    ) {
        Assert.isTrue(idCartao != null || idCartao.trim().equals(""), "O id do cartão é obrigatórios");
        Assert.isTrue(userAgent != null || userAgent.trim().equals(""), "O UserAgent é obrigatórios");
        Assert.isTrue(ip != null || ip.trim().equals(""), "O ip é obrigatório");

        this.idCartao = idCartao;
        this.instanteDoBloqueio = LocalDateTime.now();
        this.ip = ip;
        this.userAgent = userAgent;
    }

    public Long getId() {
        return id;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public LocalDateTime getInstanteDoBloqueio() {
        return instanteDoBloqueio;
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
