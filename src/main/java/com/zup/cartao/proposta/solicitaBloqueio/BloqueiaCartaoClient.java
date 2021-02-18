package com.zup.cartao.proposta.solicitaBloqueio;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "solicitaBloqueioDoCartao", url = "localhost:8888/api/cartoes")
public interface BloqueiaCartaoClient {

    @PostMapping(value = "/{id}/bloqueios")
    BloqueiaCartaoResponse bloqueiaCartao(@RequestBody BloqueiaCartaoRequest request, @PathVariable String id);

    class BloqueiaCartaoRequest{

        private String sistemaResponsavel;

        public BloqueiaCartaoRequest(String sistemaResponsavel) {
            this.sistemaResponsavel = sistemaResponsavel;
        }

        public String getSistemaResponsavel() {
            return sistemaResponsavel;
        }
    }

    class BloqueiaCartaoResponse{
        private String resultado;
        public String getResultado() {
            return resultado;
        }
    }
}
