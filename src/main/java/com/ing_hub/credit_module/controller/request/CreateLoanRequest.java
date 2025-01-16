package com.ing_hub.credit_module.controller.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateLoanRequest
{
    @NotNull
    private Long customerId;

    @NotNull
    @Min(0)
    private BigDecimal amount;

    @NotNull
    @DecimalMin(value = "0.1", message = "Interest rate must be greater than or equal to 0.1")
    @DecimalMax(value = "0.5", message = "Interest rate must be less than or equal to 0.5")
    private Double interestRate;

    @NotNull
    @Pattern(regexp = "^(6|9|12|24)$", message = "Number of installments must be 6, 9, 12 or 24")
    private Integer installments;
}
