package com.xylia.domain.payments.amortization.service;

import com.xylia.domain.payments.amortization.model.AmortizationRequest;
import com.xylia.domain.payments.amortization.model.AmortizedDataLineItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class AmortizationScheduleServiceTest {

    private AmortizationRequest amortizationRequest;

    @Autowired
    private AmortizationScheduleService amortizationScheduleService;

    @BeforeEach
    public void setUp() {

        amortizationRequest = AmortizationRequest.builder()
                .loanAmount(10000)
                .interestRate(2.5)
                .loanTerm(10)
                .build();
    }

    @Test
    public void testIfAmortizationScheduleChecksOut() {
        List<AmortizedDataLineItem> amortizedDataLineItemList = amortizationScheduleService
                .computeAmortizationSchedule(amortizationRequest);

        assertThat(amortizedDataLineItemList.size()).isEqualTo(120);

        assertThat(amortizedDataLineItemList.stream().findFirst().get().getPaymentMonth()).isEqualTo(1);
        assertThat(amortizedDataLineItemList.stream().findFirst().get().getFixedPaymentAmount()).isEqualTo(94.27);
        assertThat(amortizedDataLineItemList.stream().findFirst().get().getStartingBalance()).isEqualTo(10000);

        System.out.println(amortizedDataLineItemList);

        long count = amortizedDataLineItemList.stream().count();

        assertThat(amortizedDataLineItemList.stream().skip(count - 1).findFirst().get()
                .getPaymentMonth()).isEqualTo(120);
        assertThat(amortizedDataLineItemList.stream().skip(count - 1).findFirst().get()
                .getFixedPaymentAmount()).isEqualTo(94.27);
        assertThat(amortizedDataLineItemList.stream().skip(count - 1).findFirst().get()
                .getEndingBalance()).isEqualTo(0.0);

    }

}