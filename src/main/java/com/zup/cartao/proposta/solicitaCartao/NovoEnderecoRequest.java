package com.zup.cartao.proposta.solicitaCartao;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class NovoEnderecoRequest {

    @NotNull
    @NotEmpty
    private String cep;

    @NotNull
    @NotEmpty
    private String logradouro;

    @NotNull
    @NotEmpty
    private String numero;

    @NotNull
    @NotEmpty
    private String complemento;

    @NotNull
    @NotEmpty
    private String bairro;

    @NotNull
    @NotEmpty
    private String municipio;

    @NotNull
    @NotEmpty
    private String estado;

    @NotNull
    @NotEmpty
    private String pais;

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public NovoEnderecoRequest(@NotEmpty String cep,
                               @NotEmpty String logradouro,
                               @NotEmpty String numero,
                               @NotEmpty String complemento,
                               @NotEmpty String bairro,
                               @NotEmpty String municipio,
                               @NotEmpty String estado,
                               @NotEmpty String pais) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.municipio = municipio;
        this.estado = estado;
        this.pais = pais;
    }
}
