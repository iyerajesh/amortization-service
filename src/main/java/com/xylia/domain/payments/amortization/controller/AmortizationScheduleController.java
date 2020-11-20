package com.xylia.domain.payments.amortization.controller;

import com.xylia.domain.payments.amortization.model.AmortizationRequest;
import com.xylia.domain.payments.amortization.model.AmortizedDataLineItem;
import com.xylia.domain.payments.amortization.service.AmortizationScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller bean, that exposes a REST API, for getting the Amortization details.
 */
@RestController
@RequestMapping("/payments/amortization")
@CrossOrigin
public class AmortizationScheduleController {

    @Autowired
    private AmortizationScheduleService amortizationScheduleService;

    /**
     * Schedule REST API, which accepts a Amortization request object, and calls the Amortization Service to compute
     * the amortization schedule for every month.
     *
     * @param amortizationRequest
     * @return List<AmortizationLineItem> returns a schedule list of amortization line items by month.
     */
    @PostMapping("/schedule")
    public List<AmortizedDataLineItem> getAmortizationSchedule(@RequestBody @Valid AmortizationRequest amortizationRequest) {
        return amortizationScheduleService.computeAmortizationSchedule(amortizationRequest);
    }
}
