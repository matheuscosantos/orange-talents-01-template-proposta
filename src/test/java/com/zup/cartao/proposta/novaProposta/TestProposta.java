package com.zup.cartao.proposta.novaProposta;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.cartao.proposta.solicitaCartao.NovaPropostaRequest;
import com.zup.cartao.proposta.solicitaCartao.NovoEnderecoRequest;
import org.junit.jupiter.api.DisplayName;
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
public class TestProposta {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("Deve solicitar uma proposta e retornar 400")
    public void deveSolicitarUmaPropostaERetornar400() throws Exception {
        NovoEnderecoRequest endereco = new NovoEnderecoRequest(
                "12630-000",
                "Travessa João",
                "45",
                "apt. 51",
                "Centro",
                "São José dos Campos",
                "SP",
                "Brasil"
        );
        NovaPropostaRequest propostaRequest = new NovaPropostaRequest(
                "Matheus C. O. Santos",
                "949.879.830-36",
                "oliveiramatheus77@gmail.com",
                endereco,
                new BigDecimal(10000)
        );

        String json = mapper.writeValueAsString(propostaRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/propostas")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
