package com.ing_hub.credit_module.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PayLoanInfo {
    private Integer completedInstallmentCount;
    private BigDecimal totalInstallmentAmount;
    private boolean totallyPaid;
}
