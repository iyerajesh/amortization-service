package com.xylia.domain.payments.amortization.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
public class AmortizedDataLineItemTest {

    private AmortizedDataLineItem amortizedDataLineItem;
    private ObjectMapper MAPPER = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        amortizedDataLineItem = AmortizedDataLineItem.builder()
                .paymentMonth(1)
                .interestPayment(100.00)
                .principalPayment(750.00)
                .fixedPaymentAmount(250.00)
                .endingBalance(750.00)
                .build();
    }

    @Test
    public void testIfJsonPropertiesAreCorrectlyMapped() throws JsonProcessingException {

        String lineItemString = MAPPER.writeValueAsString(amortizedDataLineItem);
        assertThat(lineItemString)
                .isEqualTo("{\"month\":1,\"starting-balance\":0.0,\"fixed-payment\":250.0,\"principal-payment\":750.0,\"interest-payment\":100.0,\"ending-balance\":750.0,\"total-interest\":0.0}");
    }

}