package com.zup.cartao.proposta.novaBiometria;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovaBiometriaRequest {

    @NotNull
    @NotEmpty
    private String idCartao;

    @NotNull
    @NotEmpty
    private String fingerprint;

    @Deprecated
    public NovaBiometriaRequest() {
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
