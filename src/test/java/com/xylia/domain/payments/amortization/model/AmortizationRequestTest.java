package com.xylia.domain.payments.amortization.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(SpringExtension.class)
//public class AmortizationRequestTest {
//
//    private ValidatorFactory validatorFactory;
//    private Validator validator;
//
//    @BeforeEach
//    public void setUp() {
//        validatorFactory = Validation.buildDefaultValidatorFactory();
//        validator = validatorFactory.getValidator();
//    }
//
//    @Test
//    public void validateIfLoanTermIsInAcceptableRangeWithOutsideRange() {
//        AmortizationRequest amortizationRequest = AmortizationRequest.builder()
//                .loanTerm(9)
//                .build();
//        Set<ConstraintViolation<AmortizationRequest>> validationErrors = validator
//                .validate(amortizationRequest);
//
//        assertThat(validationErrors.stream().anyMatch(error -> error.getMessage()
//                .equals("The loan term should be between 10 and 30 years!"))).isTrue();
//    }
//


@ExtendWith(SpringExtension.class)
public class AmortizationRequestTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }


    @Test
    public void validateIfLoanTermIsInAcceptableRange() {

        AmortizationRequest amortizationRequest = AmortizationRequest.builder()
                .loanTerm(9)
                .build();

        Set<ConstraintViolation<AmortizationRequest>> constraintViolations = validator.validate(amortizationRequest);

        assertThat(constraintViolations.stream().anyMatch(violation -> violation.getMessage()
                .equals("The loan term should be between 10 and 30 years!"))).isTrue();
    }

    @Test
    public void validateIfLoanAmountIsNotNegative() {
        AmortizationRequest amortizationRequest = AmortizationRequest.builder()
                .loanAmount(-1)
                .build();
        Set<ConstraintViolation<AmortizationRequest>> validationErrors = validator
                .validate(amortizationRequest);

        assertThat(validationErrors.stream().anyMatch(error -> error.getMessage()
                .equals("The loan amount must be a positive value!"))).isTrue();
    }

    @Test
    public void validateIfInterestRateIsNotNegative() {
        AmortizationRequest amortizationRequest = AmortizationRequest.builder()
                .interestRate(-1.1)
                .build();
        Set<ConstraintViolation<AmortizationRequest>> validationErrors = validator
                .validate(amortizationRequest);

        assertThat(validationErrors.stream().anyMatch(error -> error.getMessage()
                .equals("The interest rate must be a positive value!"))).isTrue();
    }

    @Test
    public void deserializeAmortizationRequest() throws IOException {

        String jsonRequest = "{\"loanAmount\":10000,\"loanTerm\":10,\"interestRate\":2.5}";

        AmortizationRequest amortizationRequest = new ObjectMapper().readValue(jsonRequest, AmortizationRequest.class);

        assertThat(amortizationRequest.getInterestRate()).isEqualTo(2.5);
        assertThat(amortizationRequest.getLoanAmount()).isEqualTo(10000);
        assertThat(amortizationRequest.getLoanTerm()).isEqualTo(10);
    }
}