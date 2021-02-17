package com.zup.cartao.proposta.novaProposta.solicitaCartao;

import com.zup.cartao.proposta.config.validators.CPFouCNPJ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Component
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    @CPFouCNPJ
    private String documento;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @Embedded
    @Valid
    private Endereco endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String numeroDoCartao;

    private static final Logger log = LoggerFactory.getLogger(Proposta.class);

    @Deprecated
    public Proposta() {
    }

    public Proposta(@NotNull @NotEmpty String nome,
                    @NotNull @NotEmpty String documento,
                    @NotNull @NotEmpty String email,
                    @NotNull Endereco endereco,
                    @NotNull BigDecimal salario) {
        Assert.isTrue(nome != null || nome.trim().equals(""), "O nome é obrigatórios");
        Assert.isTrue(documento != null || documento.trim().equals(""), "O documento é obrigatórios");
        Assert.isTrue(email != null || email.trim().equals(""), "O email é obrigatórios");
        Assert.isTrue(endereco != null, "O endereço é obrigatório");
        Assert.isTrue(salario != null , "O salário é obrigatórios");
        Assert.isTrue(salario.compareTo(BigDecimal.ZERO) > 0, "O valor do salário deve ser positivo");

        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
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

    public Status getStatus() {
        return status;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public void atualizaStatus(String solicitacao){
        this.status = Status.resultadoPara(solicitacao);
    }

    public void adicionaCartao(String cartao){
        this.numeroDoCartao = cartao;
    }

    @Scheduled(fixedDelay = 10000)
    public void verificaCriacaoDoCartao(){
        if(numeroDoCartao != null){
            this.status = Status.APROVADA;
        }
    }
}
