package com.ing_hub.credit_module.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLoanRequest
{
    @NotNull
    private Long customerId;

    @NotNull
    private Double amount;

    @NotNull
    private Double interestRate;

    @NotNull
    private Integer installments;
}
