package com.zup.cartao.proposta.novaProposta;

public enum Status {
    ELEGIVEL, NAO_ELEGIVEL;

    public static Status resultadoPara(String solicitacao) {
        if(solicitacao.equals("COM_RESTRICAO")){
            return NAO_ELEGIVEL;
        }
        return ELEGIVEL;
    }
}
