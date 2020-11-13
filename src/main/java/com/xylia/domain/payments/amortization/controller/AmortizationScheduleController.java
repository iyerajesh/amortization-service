package com.xylia.domain.payments.amortization.controller;

import com.xylia.domain.payments.amortization.model.AmortizationRequest;
import com.xylia.domain.payments.amortization.model.AmortizedDataLineItem;
import com.xylia.domain.payments.amortization.service.AmortizationScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payments/amortization")
public class AmortizationScheduleController {

    @Autowired
    private AmortizationScheduleService amortizationScheduleService;

    @PostMapping("/schedule")
    public List<AmortizedDataLineItem> getAmortizationSchedule(@RequestBody @Valid AmortizationRequest amortizationRequest) {
        return amortizationScheduleService.computeAmortizationSchedule(amortizationRequest);
    }
}
