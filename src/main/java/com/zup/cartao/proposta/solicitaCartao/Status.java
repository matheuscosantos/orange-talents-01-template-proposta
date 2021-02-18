package com.zup.cartao.proposta.solicitaCartao;

public enum Status {
    ELEGIVEL, NAO_ELEGIVEL, APROVADA, BLOQUEADO, FALHA;

    public static Status resultadoPara(String solicitacao) {
        if(solicitacao.equals("COM_RESTRICAO")){
            return NAO_ELEGIVEL;
        }
        return ELEGIVEL;
    }
}
