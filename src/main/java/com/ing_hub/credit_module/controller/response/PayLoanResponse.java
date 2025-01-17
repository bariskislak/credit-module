package com.ing_hub.credit_module.controller.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PayLoanResponse {
    private Integer completedInstallmentCount;
    private BigDecimal totalInstallmentAmount;
    private Boolean totallyPaid;
}
