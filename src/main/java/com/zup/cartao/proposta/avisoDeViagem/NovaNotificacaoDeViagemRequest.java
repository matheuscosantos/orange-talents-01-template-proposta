package com.zup.cartao.proposta.avisoDeViagem;

import com.zup.cartao.proposta.config.validators.ExistsId;
import com.zup.cartao.proposta.solicitaCartao.Proposta;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NovaNotificacaoDeViagemRequest {
    @NotNull
    @NotEmpty
    @ExistsId(domainClass = Proposta.class, fieldName = "numeroDoCartao")
    private String idCartao;

    @NotNull
    @NotEmpty
    private String destino;

    @NotNull
    @Future
    private LocalDate dataDeTermino;

    public NotificacaoDeViagem paraNotificacaoDeViagem(String ipDoCliente, String userAgent) {
        return new NotificacaoDeViagem(
                idCartao,
                destino,
                dataDeTermino,
                ipDoCliente,
                userAgent
        );
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

}
