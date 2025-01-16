package com.ing_hub.credit_module.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PayLoanResponse {
    private Integer completedInstallmentCount;
    private BigDecimal totalInstallmentAmount;
    private boolean totallyPaid;
}
