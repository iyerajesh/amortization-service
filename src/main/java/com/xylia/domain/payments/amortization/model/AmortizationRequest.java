package com.xylia.domain.payments.amortization.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

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
    @NotEmpty
    private int loanAmount;

    @JsonProperty("interestRate")
    @NotEmpty
    private double interestRate;

    @JsonProperty("loanTerm")
    @NotEmpty
    private int loanTerm;
}
