package com.zup.cartao.proposta.novaProposta.criaBiometria;

import com.zup.cartao.proposta.novaProposta.solicitaCartao.Proposta;
import com.zup.cartao.proposta.novaProposta.solicitaCartao.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cria-biometria")
public class CriaBiometriaController {

    @Autowired
    BiometriaRepository biometriaRepository;

    @Autowired
    PropostaRepository propostaRepository;


    @PostMapping
    public ResponseEntity<?> criaBiometria(@RequestBody @Valid NovaBiometriaRequest request, UriComponentsBuilder uriBuilder){
        Optional<Proposta> possivelProposta = propostaRepository.findByNumeroDoCartao(request.getIdCartao());
        if(possivelProposta.isPresent()){
            Biometria novaBiometria = biometriaRepository.save(request.paraBiometria());
            URI uri = uriBuilder.path("/api/cria-biometria/{id}").buildAndExpand(novaBiometria.getId()).toUri();
            return ResponseEntity.created(uri).body(new NovaBiometriaResponse(novaBiometria));
        }
        return ResponseEntity.notFound().build();
    }

}
