package com.zup.cartao.proposta.novaProposta;

import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Embeddable
public class Endereco {
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

    public Endereco(@NotEmpty String cep,
                    @NotEmpty String logradouro,
                    @NotEmpty String numero,
                    @NotEmpty String complemento,
                    @NotEmpty String bairro,
                    @NotEmpty String municipio,
                    @NotEmpty String estado,
                    @NotEmpty String pais) {
        Assert.isTrue(cep != null | cep.trim().equals(""), "O Cep é obrigatório.");
        Assert.isTrue(logradouro != null | logradouro.trim().equals(""), "O logradouro é obrigatório.");
        Assert.isTrue(numero != null | numero.trim().equals(""), "O número é obrigatório.");
        Assert.isTrue(complemento != null | complemento.trim().equals(""), "O complemento é obrigatório.");
        Assert.isTrue(bairro != null | bairro.trim().equals(""), "O bairro é obrigatório.");
        Assert.isTrue(municipio != null | municipio.trim().equals(""), "O município é obrigatório.");
        Assert.isTrue(estado != null | estado.trim().equals(""), "O estado é obrigatório.");
        Assert.isTrue(pais != null | pais.trim().equals(""), "O país é obrigatório.");

        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.municipio = municipio;
        this.estado = estado;
        this.pais = pais;
    }

    @Deprecated
    public Endereco() {
    }

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
}
