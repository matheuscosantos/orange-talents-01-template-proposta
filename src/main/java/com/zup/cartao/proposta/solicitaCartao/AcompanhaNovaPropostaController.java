package com.zup.cartao.proposta.solicitaCartao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/propostas/acompanhamento")
public class AcompanhaNovaPropostaController {

    PropostaRepository repository;

    public AcompanhaNovaPropostaController(PropostaRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> acompanhaProposta(@PathVariable Long id){
        Optional<Proposta> possivelProposta = repository.findById(id);
        if(possivelProposta.isPresent()){
            return ResponseEntity.ok(new NovaPropostaResponse(possivelProposta.get()));
        }
        return ResponseEntity.notFound().build();
    }

}
