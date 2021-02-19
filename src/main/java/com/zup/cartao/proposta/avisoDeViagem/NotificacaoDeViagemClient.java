package com.zup.cartao.proposta.avisoDeViagem;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@FeignClient(name = "notificacaoDeViagem", url = "localhost:8888/api/cartoes")
public interface NotificacaoDeViagemClient {

    @PostMapping(value = "/{id}/avisos")
    Response notificaViagem(@RequestBody Request request,
                            @PathVariable String id);

    class Request{
        private String destino;
        private LocalDate validoAte;

        public Request(String destino,
                       LocalDate validoAte) {
            this.destino = destino;
            this.validoAte = validoAte;
        }

        public String getDestino() {
            return destino;
        }

        public LocalDate getValidoAte() {
            return validoAte;
        }
    }

    class Response{
        private String resultado;

        public String getResultado() {
            return resultado;
        }
    }
}
