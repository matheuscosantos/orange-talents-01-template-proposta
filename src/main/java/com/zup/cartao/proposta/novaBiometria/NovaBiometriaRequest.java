package com.zup.cartao.proposta.novaBiometria;

import com.zup.cartao.proposta.config.validators.ExistsId;
import com.zup.cartao.proposta.solicitaCartao.Proposta;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovaBiometriaRequest {

    @NotNull
    @NotEmpty
    @ExistsId(domainClass = Proposta.class, fieldName = "numeroDoCartao")
    private String idCartao;

    @NotNull
    @NotEmpty
    private String fingerprint;

    @Deprecated
    public NovaBiometriaRequest() {
    }

    public NovaBiometriaRequest(@NotNull @NotEmpty String idCartao,
                                @NotNull @NotEmpty String fingerprint) {
        this.idCartao = idCartao;
        this.fingerprint = fingerprint;
    }

    public NovaBiometriaRequest(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometria paraBiometria() {
        return new Biometria(
                this.idCartao,
                this.fingerprint);
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getFingerprint() {
        return fingerprint;
    }

}
