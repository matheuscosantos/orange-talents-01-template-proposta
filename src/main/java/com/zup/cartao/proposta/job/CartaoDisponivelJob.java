package com.zup.cartao.proposta.job;

import com.zup.cartao.proposta.solicitaCartao.Proposta;
import com.zup.cartao.proposta.solicitaCartao.PropostaRepository;
import com.zup.cartao.proposta.solicitaCartao.Status;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@EnableScheduling
public class CartaoDisponivelJob {

    private static final Logger log = LoggerFactory.getLogger(CartaoDisponivelJob.class);

    @Autowired
    PropostaRepository repository;

    @Autowired
    CartaoClient cartaoClient;

    Proposta proposta;

    @Scheduled(fixedDelay = 3000)
    @Transactional
    public void buscaNumeroDoCartao(){

        List<Proposta> propostas = repository.findAllElegiveisSemCartao();

        try{
            for(Proposta proposta: propostas){
                DisponibilidadeDoCartaoResponse dados = cartaoClient.verificaDisponibilidade(new DisponibilidadeDoCartaoRequest(proposta));
                proposta.adicionaCartao(dados.getId());
                proposta.setStatus(Status.CONCLUIDA);
                repository.save(proposta);
            }
        }catch (FeignException ex){
            log.info("Não foi encontrado cartão para a proposta: " + proposta.getId() + ".");
        }

    }

}
