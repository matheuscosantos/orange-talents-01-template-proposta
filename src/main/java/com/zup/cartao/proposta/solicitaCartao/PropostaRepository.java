package com.zup.cartao.proposta.solicitaCartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);
    Optional<Proposta> findByNumeroDoCartao(String idCartao);

    @Query("select p from Proposta p WHERE p.status = 'ELEGIVEL'")
    List<Proposta> findAllElegiveis();

    Optional<Proposta> findByEmail(String documento);
}
