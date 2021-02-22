package com.zup.cartao.proposta.carteiraDigital;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraDigitalRepository extends JpaRepository<CarteiraDigital, Long> {
    Optional<CarteiraDigital> findByIdCartao(String idCartao);
}
