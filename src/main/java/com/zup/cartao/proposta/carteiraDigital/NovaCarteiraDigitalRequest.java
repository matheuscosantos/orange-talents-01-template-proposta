package com.zup.cartao.proposta.carteiraDigital;

import com.zup.cartao.proposta.config.validators.ExistsId;
import com.zup.cartao.proposta.solicitaCartao.Proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaCarteiraDigitalRequest {
    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotBlank
    @NotNull
    @ExistsId(domainClass = Proposta.class, fieldName = "numeroDoCartao")
    private String idCartao;

    @NotNull
    public CarteiraTipo tipo;

    public CarteiraDigital paraCarteiraDigital(){
        return new CarteiraDigital(email, idCartao, tipo);
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
