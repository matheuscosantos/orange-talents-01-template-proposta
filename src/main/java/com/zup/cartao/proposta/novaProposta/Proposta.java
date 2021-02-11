package com.zup.cartao.proposta.novaProposta;

import com.zup.cartao.proposta.config.validators.CPFouCNPJ;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @CPFouCNPJ
    private String cnpjOuCpf;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @Embedded
    private Endereco endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    @Deprecated
    public Proposta() {
    }

    public Proposta(@NotNull @NotEmpty String cnpjOuCpf,
                    @NotNull @NotEmpty String email,
                    @NotNull Endereco endereco,
                    @NotNull BigDecimal salario) {
        Assert.isTrue(cnpjOuCpf != null || cnpjOuCpf.trim().equals(""), "O CNPJ ou CPF são obrigatórios");
        Assert.isTrue(email != null || email.trim().equals(""), "O email é obrigatórios");
        Assert.isTrue(endereco != null, "O endereço é obrigatório");
        Assert.isTrue(salario != null , "O salário é obrigatórios");
        Assert.isTrue(salario.compareTo(BigDecimal.ZERO) > 0, "O valor do salário deve ser positivo");

        this.cnpjOuCpf = cnpjOuCpf;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getCnpjOuCpf() {
        return cnpjOuCpf;
    }

    public String getEmail() {
        return email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
