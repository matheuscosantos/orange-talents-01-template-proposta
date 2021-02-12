package com.zup.cartao.proposta.novaProposta;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/propostas")
public class PropostaController {

    PropostaRepository propostaRepository;
    AnaliseClient analiseClient;
    SolicitaCartaoClient solicitaCartaoClient;

    public PropostaController(PropostaRepository propostaRepository,
                              AnaliseClient analiseClient,
                              SolicitaCartaoClient solicitaCartaoClient) {
        this.propostaRepository = propostaRepository;
        this.analiseClient = analiseClient;
        this.solicitaCartaoClient = solicitaCartaoClient;
    }

    @PostMapping
    public ResponseEntity<?> novaProposta(@RequestBody @Valid NovaPropostaRequest request,
                                          UriComponentsBuilder uriBuilder){

        Optional<Proposta> possivelProposta = propostaRepository.findByDocumento(request.getDocumento());

        if(possivelProposta.isPresent()){
            HashMap<String, Object> resposta = new HashMap<>();
            resposta.put("mensagem", "Já existe solicitação para esse cliente.");
            return ResponseEntity.unprocessableEntity().body(resposta);
        }

        Proposta novaProposta = propostaRepository.save(request.paraProposta());
        URI uri = uriBuilder.path("/api/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();

        try{
//          Proposta APROVADA
            AnaliseClient.ConsultaStatusRequest requisicaoDaAnalise = new AnaliseClient.ConsultaStatusRequest(novaProposta);
            AnaliseClient.ConsultaAnaliseResponse resposta = analiseClient.consultaStatus(requisicaoDaAnalise);
            novaProposta.atualizaStatus(resposta.getResultadoSolicitacao());
            propostaRepository.save(novaProposta);
//
//          Solicita Cartão
            try {
                SolicitaCartaoClient.NovoCartaoRequest requisicaoDoCartao = new SolicitaCartaoClient.NovoCartaoRequest(novaProposta);
                SolicitaCartaoClient.NovoCartaoResponse respostaCartao = solicitaCartaoClient.solicitaCartao(requisicaoDoCartao);
                novaProposta.adicionaCartao(respostaCartao.getId());
                novaProposta.verificaCriacaoDoCartao();
                propostaRepository.save(novaProposta);
                return ResponseEntity.created(uri).body(new PropostaResponse(novaProposta));
            }catch (FeignException.UnprocessableEntity e) {
                return ResponseEntity.created(uri).body(new PropostaResponse(novaProposta));
            }
//
//          Proposta NAO_APROVADA
        }catch (FeignException.UnprocessableEntity e){
            novaProposta.atualizaStatus("COM_RESTRICAO");
            propostaRepository.save(novaProposta);
            return ResponseEntity.created(uri).body(new PropostaResponse(novaProposta));
        }
//
    }
}
