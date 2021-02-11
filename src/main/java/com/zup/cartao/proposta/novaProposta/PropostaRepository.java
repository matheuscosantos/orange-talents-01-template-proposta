package com.zup.cartao.proposta.novaProposta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByCnpjOuCpf(String cnpjOuCpf);
}
