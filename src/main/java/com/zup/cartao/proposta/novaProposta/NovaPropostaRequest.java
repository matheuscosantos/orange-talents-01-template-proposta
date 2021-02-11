package com.zup.cartao.proposta.novaProposta;

import com.sun.istack.NotNull;
import com.zup.cartao.proposta.config.validators.CPFouCNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotNull
    @NotEmpty
    @CPFouCNPJ
    private String cnpjOuCpf;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    private NovoEnderecoRequest endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    public String getCnpjOuCpf() {
        return cnpjOuCpf;
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

    public Proposta paraProposta() {
        return new Proposta(this.cnpjOuCpf,
                            this.email,
                            new Endereco( endereco.getCep(),
                                          endereco.getLogradouro(),
                                          endereco.getNumero(),
                                          endereco.getComplemento(),
                                          endereco.getBairro(),
                                          endereco.getMunicipio(),
                                          endereco.getEstado(),
                                          endereco.getPais()),
                            this.salario);
    }
}
