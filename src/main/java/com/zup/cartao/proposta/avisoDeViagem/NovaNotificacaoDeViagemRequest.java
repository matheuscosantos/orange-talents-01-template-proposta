package com.zup.cartao.proposta.avisoDeViagem;

import com.zup.cartao.proposta.config.validators.ExistsId;
import com.zup.cartao.proposta.config.validators.UniqueValue;
import com.zup.cartao.proposta.solicitaCartao.Proposta;

import javax.persistence.Column;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NovaNotificacaoDeViagemRequest {
    @NotNull
    @NotEmpty
    @ExistsId(domainClass = Proposta.class, fieldName = "numeroDoCartao")
    @Column(unique = true)
    private String idCartao;

    @NotNull
    @NotEmpty
    private String destino;

    @NotNull
    @Future
    private LocalDate dataDeTermino;

    /*
    Construtor utilizado para testes
     */
    @Deprecated
    public NovaNotificacaoDeViagemRequest(@NotNull @NotEmpty String idCartao,
                                          @NotNull @NotEmpty String destino,
                                          @NotNull @Future LocalDate dataDeTermino) {
        this.idCartao = idCartao;
        this.destino = destino;
        this.dataDeTermino = dataDeTermino;
    }

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
