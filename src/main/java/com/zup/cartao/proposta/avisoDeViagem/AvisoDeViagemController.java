package com.zup.cartao.proposta.avisoDeViagem;

import com.zup.cartao.proposta.solicitaCartao.Proposta;
import com.zup.cartao.proposta.solicitaCartao.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cartao/viagem")
public class AvisoDeViagemController {

    @Autowired
    private NotificacaoDeViagemRepository notificacaoRepository;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private NotificacaoDeViagemClient notificacaoDeViagemClient;

    @PostMapping
    public ResponseEntity<?> criaNotificacao(@RequestBody @Valid NovaNotificacaoDeViagemRequest request,
                                             UriComponentsBuilder uriBuilder,
                                             @RequestHeader(value = "User-Agent") String userAgent,
                                             HttpServletRequest servletRequest){

        Optional<Proposta> possivelProposta = propostaRepository.findByNumeroDoCartao(request.getIdCartao());
        if(possivelProposta.isEmpty()){
            ResponseEntity.notFound().build();
        }

        String ip = servletRequest.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = servletRequest.getRemoteAddr();
        }

        NotificacaoDeViagem novaNotificacao = request.paraNotificacaoDeViagem(userAgent, ip);
        novaNotificacao = notificacaoRepository.save(novaNotificacao);

        try{
            NotificacaoDeViagemClient.Request requisicaoDaNotificacao = new NotificacaoDeViagemClient.Request(request.getDestino(), request.getDataDeTermino());
            notificacaoDeViagemClient.notificaViagem(requisicaoDaNotificacao, request.getIdCartao());
            URI uri = uriBuilder.path("/api/cartao/viagem/{id}").buildAndExpand(novaNotificacao.getId()).toUri();
            return ResponseEntity.created(uri).body(new NovaNotificacaoDeViagemResponse(novaNotificacao));
        }catch (FeignException.UnprocessableEntity unprocessableEntity){
            HashMap mensagem = new HashMap();
            mensagem.put("Mensagem","Notificação de viagem já criada.");
            return ResponseEntity.unprocessableEntity().body(mensagem);
        }
    }
}
