package com.xylia.domain.payments.amortization.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Model object to capture the amortization request data.
 */

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AmortizationRequest {

    @JsonProperty("loanAmount")
    @NotNull
    @Min(value = 0L, message = "The loan amount must be a positive value!")
    private int loanAmount;

    @JsonProperty("interestRate")
    @NotNull
    @Min(value = 0L, message = "The interest rate must be a positive value!")
    private double interestRate;

    @JsonProperty("loanTerm")
    @NotNull
    @Range(min = 10, max = 30, message
            = "The loan term should be between 10 and 30 years!")
    private int loanTerm;
}
