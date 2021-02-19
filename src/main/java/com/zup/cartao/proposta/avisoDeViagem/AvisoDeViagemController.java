package com.zup.cartao.proposta.avisoDeViagem;

import com.zup.cartao.proposta.solicitaCartao.Proposta;
import com.zup.cartao.proposta.solicitaCartao.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cartao/viagem")
public class AvisoDeViagemController {

    @Autowired
    private NotificacaoDeViagemRepository notificacaoRepository;

    @Autowired
    private PropostaRepository propostaRepository;

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

        URI uri = uriBuilder.path("/api/cartao/viagem/{id}").buildAndExpand(novaNotificacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new NovaNotificacaoDeViagemResponse(novaNotificacao));
    }
}
