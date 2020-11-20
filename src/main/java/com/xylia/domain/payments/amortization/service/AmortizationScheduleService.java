package com.xylia.domain.payments.amortization.service;

import com.google.common.collect.Lists;
import com.xylia.domain.payments.amortization.model.AmortizationRequest;
import com.xylia.domain.payments.amortization.model.AmortizedDataLineItem;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

import static java.lang.Double.valueOf;
import static org.apache.commons.math3.util.Precision.round;

/**
 * Service class, which is responsible for computing the amortization details, and populating the
 * AmortizationLineItem objects.
 */

@Service
public class AmortizationScheduleService {

    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    /**
     *  Computes the Amortization Schedule, based on the Amortization request recieved.
     * @param amortizationRequest The Amortization request object.
     * @return List<AmortizationLineItem> returns a schedule list of amortization line items by month.
     */
    public final List<AmortizedDataLineItem> computeAmortizationSchedule(AmortizationRequest amortizationRequest) {

        List<AmortizedDataLineItem> amortizedDataLineItemList = Lists.newArrayList();


        double loanAmount = amortizationRequest.getLoanAmount();
        double annualInterestRate = amortizationRequest.getInterestRate();
        int numberOfYears = amortizationRequest.getLoanTerm();

        int numMonths = numberOfYears * 12;
        double monthlyInterestRate = annualInterestRate / 12;

        // Compute monthly payment
        double monthlyPayment = computeMonthlyPayment(loanAmount, numberOfYears,
                monthlyInterestRate);

        double principal = loanAmount;
        double interestPaid, principalPaid, newBalance;
        double totalInterestPaid = 0;

        for (int month = 1; month <= numMonths; month++) {

            // Compute amount paid and new balance for each payment period
            interestPaid = principal * (monthlyInterestRate / 100);
            totalInterestPaid += interestPaid;

            principalPaid = monthlyPayment - interestPaid;
            newBalance = principal - principalPaid;

            AmortizedDataLineItem amortizedDataLineItem = AmortizedDataLineItem.builder()
                    .paymentMonth(month)
                    .fixedPaymentAmount(round(monthlyPayment, 2))
                    .principalPayment(round(principalPaid, 2))
                    .interestPayment(round(interestPaid, 2))
                    .startingBalance(round(principal, 2))
                    .endingBalance(round(newBalance, 2))
                    .totalInterest(round(totalInterestPaid, 2))
                    .build();

            // Update the balance
            principal = newBalance;

            amortizedDataLineItemList.add(amortizedDataLineItem);
        }
        return amortizedDataLineItemList;
    }


    private static final double computeMonthlyPayment(double loanAmount, int numberOfYears,
                                                      double monthlyInterestRate) {
        monthlyInterestRate /= 100;
        return loanAmount * monthlyInterestRate /
                (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
    }
}
