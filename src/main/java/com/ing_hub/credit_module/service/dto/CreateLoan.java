package com.ing_hub.credit_module.service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateLoan {
    private Long customerId;
    private BigDecimal amount;
    private Double interestRate;
    private Integer installments;
}
