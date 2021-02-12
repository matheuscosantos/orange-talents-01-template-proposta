package com.zup.cartao.proposta.novaProposta;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class PropostaResponse {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String nome;
    @JsonProperty
    private String documento;
    @JsonProperty
    private String email;
    @JsonProperty
    private Endereco endereco;
    @JsonProperty
    private BigDecimal salario;
    @JsonProperty
    private Status status;
    @JsonProperty
    private String numeroDoCartao;

    public PropostaResponse(Proposta proposta) {
        this.nome = proposta.getNome();
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatus();
        this.numeroDoCartao = proposta.getNumeroDoCartao();
    }

}
