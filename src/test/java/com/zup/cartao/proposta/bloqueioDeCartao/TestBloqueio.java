package com.zup.cartao.proposta.bloqueioDeCartao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.cartao.proposta.solicitaBloqueio.NovoBloqueioRequest;
import com.zup.cartao.proposta.solicitaCartao.Endereco;
import com.zup.cartao.proposta.solicitaCartao.Proposta;
import com.zup.cartao.proposta.solicitaCartao.PropostaRepository;
import com.zup.cartao.proposta.solicitaCartao.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Transactional
public class TestBloqueio {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    PropostaRepository repository;

    @BeforeEach
    public void init(){
        Proposta proposta = new Proposta(
                1L,
                "Matheus C. O.",
                "939.999.123-24",
                "matheusoliveira22@gmail.com",
                new Endereco(
                        "12630-000",
                        "Travessa João",
                        "45",
                        "apt. 51",
                        "Centro",
                        "São José dos Campos",
                        "SP",
                        "Brasil"
                ),
                new BigDecimal(1000.0),
                Status.ELEGIVEL,
                "8121-8429-4221-6121"
        );

        repository.save(proposta);
    }

    @Test
    public void deveSolicitarOBloqueioDoCartaoERetornar400() throws Exception{
        NovoBloqueioRequest bloqueioRequest = new NovoBloqueioRequest(
                "8121-8429-4221-6121"
        );

        String json = mapper.writeValueAsString(bloqueioRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/cartao/bloqueio")
                .header("User-Agent","Teste")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
