package com.zup.cartao.proposta.novaProposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(value = "/api/propostas")
public class PropostaController {

    @Autowired
    PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<?> novaProposta(@RequestBody @Valid NovaPropostaRequest request,
                                          UriComponentsBuilder uriBuilder){
        Proposta novaProposta = propostaRepository.save(request.paraProposta());
        URI uri = uriBuilder.path("/api/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaResponse(novaProposta));
    }
}
