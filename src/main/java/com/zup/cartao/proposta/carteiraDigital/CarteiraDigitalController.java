package com.zup.cartao.proposta.carteiraDigital;

import com.zup.cartao.proposta.solicitaCartao.Proposta;
import com.zup.cartao.proposta.solicitaCartao.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = "/api/cartao/carteira-digital/paypal")
public class CarteiraDigitalController {

    CarteiraDigitalRepository carteiraRepository;
    PropostaRepository propostaRepository;
    CarteiraClient client;

    public CarteiraDigitalController(CarteiraDigitalRepository carteiraRepository,
                                     PropostaRepository propostaRepository,
                                     CarteiraClient client) {
        this.carteiraRepository = carteiraRepository;
        this.propostaRepository = propostaRepository;
        this.client = client;
    }

    @PostMapping
    public ResponseEntity<?> cria(@RequestBody @Valid NovaCarteiraDigitalRequest request,
                                  UriComponentsBuilder uriBuilder){

        Optional<Proposta> possivelProposta = propostaRepository.findByNumeroDoCartao(request.getIdCartao());

        if(possivelProposta.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Optional<CarteiraDigital> possivelCarteira = carteiraRepository.findByIdCartao(request.getIdCartao());

        if(possivelCarteira.isPresent()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        try{
            CarteiraClient.Request requisicaoDeCarteiraDigital = new CarteiraClient.Request(request.getEmail(), CarteiraTipo.PAYPAL);
            client.consulta(requisicaoDeCarteiraDigital, request.getIdCartao());
        }catch (FeignException.UnprocessableEntity unprocessableEntity){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        CarteiraDigital novaCarteira = request.paraCarteiraDigital();
        novaCarteira = carteiraRepository.save(novaCarteira);

        URI uri = uriBuilder.path("/api/cartao/carteira-digital/{id}/paypal").buildAndExpand(novaCarteira.getId()).toUri();
        return ResponseEntity.created(uri).body(new NovaCarteiraDigitalResponse(novaCarteira));
    }
}
