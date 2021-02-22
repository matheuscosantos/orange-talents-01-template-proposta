package com.zup.cartao.proposta.carteiraDigital;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CarteiraDigital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String idCartao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CarteiraTipo tipo;

    @Deprecated
    public CarteiraDigital() {
    }

    public CarteiraDigital(@NotBlank @NotNull @Email String email,
                           @NotBlank @NotNull String idCartao,
                           @NotNull CarteiraTipo tipo) {
        Assert.isTrue(email != null || email.trim().equals(""), "O email do cartão é obrigatório");
        Assert.isTrue(idCartao != null || idCartao.trim().equals(""), "O id do cartão é obrigatório");
        this.email = email;
        this.idCartao = idCartao;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public CarteiraTipo getTipo() {
        return tipo;
    }
}
