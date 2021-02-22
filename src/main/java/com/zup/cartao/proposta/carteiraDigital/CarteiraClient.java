package com.zup.cartao.proposta.carteiraDigital;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "carteiraDigital", url = "localhost:8888/api/cartoes")
public interface CarteiraClient {

    @PostMapping(value = "/{id}/carteiras")
    Response consulta(@RequestBody Request request,
                      @PathVariable String id);

    class Request{
        private String email;
        private CarteiraTipo carteira;

        public Request(String email,
                       CarteiraTipo carteira) {
            this.email = email;
            this.carteira = carteira;
        }

        public String getEmail() {
            return email;
        }

        public CarteiraTipo getCarteira() {
            return carteira;
        }
    }

    class Response{
        private String resultado;
        private String id;

        public String getResultado() {
            return resultado;
        }

        public String getId() {
            return id;
        }
    }
}
