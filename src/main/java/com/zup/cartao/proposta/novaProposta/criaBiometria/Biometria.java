package com.zup.cartao.proposta.novaProposta.criaBiometria;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String idCartao;
    private String fingerprint;
    @CreationTimestamp
    private LocalDateTime dataDeCriacao = LocalDateTime.now();

    @Deprecated
    public Biometria() {
    }

    public Biometria(String idCartao,
                     String fingerprint) {
        this.idCartao = idCartao;

        Base64 base64 = new Base64();
        this.fingerprint = base64.encodeAsString(fingerprint.getBytes(StandardCharsets.UTF_8));
    }

    public Long getId() {
        return id;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
