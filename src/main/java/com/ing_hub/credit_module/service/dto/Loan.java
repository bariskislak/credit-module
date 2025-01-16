package com.ing_hub.credit_module.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Loan {
    private Long customerId;
    private BigDecimal loanAmount;
    private Integer numberOfInstallments;
    private LocalDateTime createDate;
    private Boolean paid;
}
