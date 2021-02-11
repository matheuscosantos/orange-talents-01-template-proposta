package com.zup.cartao.proposta.novaProposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "solicitaCartaoCliente", url = "localhost:8888/api/cartoes")
public interface SolicitaCartaoClient {

    @PostMapping
    NovoCartaoResponse solicitaCartao(@RequestBody NovoCartaoRequest novoCartaoRequest);

    class NovoCartaoRequest{
        private String documento;
        private String nome;
        private Long idProposta;

        public NovoCartaoRequest(Proposta novaProposta) {
            this.documento = novaProposta.getDocumento();
            this.nome = novaProposta.getNome();
            this.idProposta = novaProposta.getId();
        }

        public String getDocumento() {
            return documento;
        }
        public String getNome() {
            return nome;
        }
        public Long getIdProposta() {
            return idProposta;
        }
    }

    class NovoCartaoResponse{
        private String id;
        public String getId() {
            return id;
        }
    }
}
