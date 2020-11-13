package com.xylia.domain.payments.amortization.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Model object which will return the amortization data for every single month.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AmortizedDataLineItem {

    @JsonProperty("month")
    private int paymentMonth;

    @JsonProperty("starting-balance")
    private double startingBalance;

    @JsonProperty("fixed-payment")
    private double fixedPaymentAmount;

    @JsonProperty("principal-payment")
    private double principalPayment;

    @JsonProperty("interest-payment")
    private double interestPayment;

    @JsonProperty("ending-balance")
    private double endingBalance;

    @JsonProperty("total-interest")
    private double totalInterest;
}
