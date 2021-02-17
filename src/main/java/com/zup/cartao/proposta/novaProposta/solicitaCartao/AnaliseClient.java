package com.zup.cartao.proposta.novaProposta.solicitaCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseCliente", url = "localhost:9999/api/solicitacao")
public interface AnaliseClient {

    @PostMapping
    ConsultaAnaliseResponse consultaStatus(@RequestBody ConsultaStatusRequest consultaStatusRequest);

    class ConsultaStatusRequest{
        private String documento;
        private String nome;
        private Long idProposta;

        public ConsultaStatusRequest(Proposta proposta) {
            this.documento = proposta.getDocumento();
            this.nome = proposta.getNome();
            this.idProposta = proposta.getId();
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

    class ConsultaAnaliseResponse{
        private String resultadoSolicitacao;
        private String documento;
        private String nome;
        private Long idProposta;

        public String getResultadoSolicitacao() {
            return resultadoSolicitacao;
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


}
