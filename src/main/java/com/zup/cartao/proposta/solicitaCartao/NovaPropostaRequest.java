package com.zup.cartao.proposta.solicitaCartao;

import com.sun.istack.NotNull;
import com.zup.cartao.proposta.config.validators.CPFouCNPJ;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    @CPFouCNPJ
    private String documento;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Valid
    private NovoEnderecoRequest endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public NovoEnderecoRequest getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Proposta paraProposta(String password) {
        return new Proposta(this.nome,
                            this.documento ,
                            this.email,
                            new Endereco( endereco.getCep(),
                                          endereco.getLogradouro(),
                                          endereco.getNumero(),
                                          endereco.getComplemento(),
                                          endereco.getBairro(),
                                          endereco.getMunicipio(),
                                          endereco.getEstado(),
                                          endereco.getPais()),
                            this.salario,
                            password);
    }

    public NovaPropostaRequest(@NotEmpty String nome,
                               @NotEmpty String documento,
                               @NotEmpty @Email String email,
                               @Valid NovoEnderecoRequest endereco,
                               @Positive BigDecimal salario) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }
}
