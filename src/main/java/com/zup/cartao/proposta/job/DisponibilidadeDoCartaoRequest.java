package com.zup.cartao.proposta.job;

import com.zup.cartao.proposta.solicitaCartao.Proposta;

public class DisponibilidadeDoCartaoRequest {
    private String documento;
    private String nome;
    private Long idProposta;

    public DisponibilidadeDoCartaoRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
