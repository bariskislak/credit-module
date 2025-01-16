package com.ing_hub.credit_module.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateLoan
{
    private Long customerId;
    private BigDecimal amount;
    private Double interestRate;
    private Integer installments;
}
