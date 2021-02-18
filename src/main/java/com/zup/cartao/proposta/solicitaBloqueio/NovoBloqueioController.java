package com.zup.cartao.proposta.solicitaBloqueio;

import com.zup.cartao.proposta.novaBiometria.NovaBiometriaResponse;
import com.zup.cartao.proposta.solicitaCartao.Proposta;
import com.zup.cartao.proposta.solicitaCartao.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    public NovoBloqueioController(BloqueioRepository bloqueioRepository,
                                  PropostaRepository propostaRepository) {
        this.bloqueioRepository = bloqueioRepository;
        this.propostaRepository = propostaRepository;
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

        String ip = servletRequest.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = servletRequest.getRemoteAddr();
        }
        try{
            Bloqueio novoBloqueio = bloqueioRepository.save(request.paraBloqueio(userAgent, ip));
            URI uri = uriBuilder.path("/api/solicita-bloqueio/{id}").buildAndExpand(novoBloqueio.getId()).toUri();
            return ResponseEntity.created(uri).body(new BloqueioResponse(novoBloqueio));
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(request);
        }
    }
}
