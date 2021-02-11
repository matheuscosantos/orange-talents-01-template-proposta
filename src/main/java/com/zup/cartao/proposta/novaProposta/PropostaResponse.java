package com.zup.cartao.proposta.novaProposta;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class PropostaResponse {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String cnpjOuCpf;
    @JsonProperty
    private String email;
    @JsonProperty
    private Endereco endereco;
    @JsonProperty
    private BigDecimal salario;

    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.cnpjOuCpf = proposta.getCnpjOuCpf();
        this.email = proposta.getEmail();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
    }

}
