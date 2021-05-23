package com.xylia.domain.payments.amortization.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xylia.domain.payments.amortization.model.AmortizationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AmortizationScheduleControllerTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void whenValidInput_thenReturns200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/payments/amortization/schedule")
                .content(MAPPER.writeValueAsString(AmortizationRequest.builder()
                        .loanAmount(10000)
                        .loanTerm(10)
                        .interestRate(2.5)
                        .build()))

                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].starting-balance").exists());
    }


    @Test
    public void readinessProbe_returns200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/health/readiness")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").isNotEmpty());
    }

    @Test
    public void liveNessProbe_Returns200() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/actuator/health/liveness")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").isNotEmpty());
    }
}