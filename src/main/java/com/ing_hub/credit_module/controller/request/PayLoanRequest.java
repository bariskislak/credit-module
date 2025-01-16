package com.ing_hub.credit_module.controller.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayLoanRequest {
    private Long loanId;
    private BigDecimal amount;
}
