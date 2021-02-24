package com.zup.cartao.proposta.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;

public class Encryptor{

    private String password;
    private StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();

    public Encryptor(String password) {
        this.password = password;
        textEncryptor.setPassword(this.password);
        textEncryptor.setSaltGenerator(new ZeroSaltGenerator());
    }

    public String encrypt(String text){
        return textEncryptor.encrypt(text);
    }

    public String decrypt(String text){
        return textEncryptor.decrypt(text);
    }

}