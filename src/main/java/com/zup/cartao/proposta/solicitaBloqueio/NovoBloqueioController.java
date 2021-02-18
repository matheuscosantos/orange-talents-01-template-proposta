package com.zup.cartao.proposta.solicitaBloqueio;

import com.zup.cartao.proposta.solicitaCartao.Proposta;
import com.zup.cartao.proposta.solicitaCartao.PropostaRepository;
import com.zup.cartao.proposta.solicitaCartao.Status;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cartao/bloqueio")
public class NovoBloqueioController {

    BloqueioRepository bloqueioRepository;
    PropostaRepository propostaRepository;
    BloqueiaCartaoClient bloqueiaCartaoClient;

    public NovoBloqueioController(BloqueioRepository bloqueioRepository,
                                  PropostaRepository propostaRepository,
                                  BloqueiaCartaoClient bloqueiaCartaoClient) {
        this.bloqueioRepository = bloqueioRepository;
        this.propostaRepository = propostaRepository;
        this.bloqueiaCartaoClient = bloqueiaCartaoClient;
    }

    @PostMapping
    public ResponseEntity<?> cria(@RequestBody @Valid BloqueioRequest request,
                                  @RequestHeader(value = "User-Agent") String userAgent,
                                  HttpServletRequest servletRequest,
                                  UriComponentsBuilder uriBuilder){

        Optional<Proposta> possivelProposta = propostaRepository.findByNumeroDoCartao(request.getIdCartao());

        if(possivelProposta.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(possivelProposta.get().getStatus().equals(Status.BLOQUEADO)){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

        String ip = servletRequest.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = servletRequest.getRemoteAddr();
        }

        try{
            BloqueiaCartaoClient.BloqueiaCartaoRequest requisicaoParaCancelamento = new BloqueiaCartaoClient.BloqueiaCartaoRequest("API Propostas");
            BloqueiaCartaoClient.BloqueiaCartaoResponse respostaDoCancelamento = bloqueiaCartaoClient.bloqueiaCartao(requisicaoParaCancelamento, request.getIdCartao());

            Bloqueio novoBloqueio = bloqueioRepository.save(request.paraBloqueio(userAgent, ip));

            possivelProposta.get().cancelaCartao();
            propostaRepository.save(possivelProposta.get());

            URI uri = uriBuilder.path("/api/solicita-bloqueio/{id}").buildAndExpand(novoBloqueio.getId()).toUri();
            return ResponseEntity.created(uri).body(new BloqueioResponse(novoBloqueio));
        }catch (FeignException.UnprocessableEntity unprocessableEntity){
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
