package com.ing_hub.credit_module.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayLoan {
    private Long loanId;
    private BigDecimal amount;
}
