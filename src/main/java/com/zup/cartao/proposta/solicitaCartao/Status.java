package com.zup.cartao.proposta.solicitaCartao;

public enum Status {
    ELEGIVEL, NAO_ELEGIVEL, APROVADA, BLOQUEADO, FALHA, CONCLUIDA;

    public static Status resultadoPara(String solicitacao) {
        if(solicitacao.equals("COM_RESTRICAO")){
            return NAO_ELEGIVEL;
        }else if(solicitacao.isEmpty()){
            return ELEGIVEL;
        }
        return null;
    }
}
