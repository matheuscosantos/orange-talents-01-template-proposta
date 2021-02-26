package com.zup.cartao.proposta.job;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "verificaDisponibilidadeDoCartao", url = "localhost:8888/api/cartoes")
public interface CartaoClient {

    @PostMapping
    DisponibilidadeDoCartaoResponse verificaDisponibilidade(DisponibilidadeDoCartaoRequest request);
}
