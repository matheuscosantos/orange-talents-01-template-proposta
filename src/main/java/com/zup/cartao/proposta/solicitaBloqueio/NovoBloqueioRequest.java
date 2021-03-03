package com.zup.cartao.proposta.solicitaBloqueio;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovoBloqueioRequest {

    @NotNull
    @NotEmpty
    private String idCartao;

    public NovoBloqueioRequest() {
    }

    /*
        Construtor utilizado para testes
         */
    @Deprecated
    public NovoBloqueioRequest(@NotNull @NotEmpty String idCartao) {
        this.idCartao = idCartao;
    }

    public Bloqueio paraBloqueio(String userAgent, String ip) {
        return new Bloqueio(idCartao,
                            userAgent,
                            ip);
    }

    public String getIdCartao() {
        return idCartao;
    }
}
