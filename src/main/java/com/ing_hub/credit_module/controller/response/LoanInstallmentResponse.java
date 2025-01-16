package com.ing_hub.credit_module.controller.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanInstallmentResponse {
    private Long id;
    private Long loanId;
    private BigDecimal amount;
    private LocalDate dueDate;
    private BigDecimal defaultAmount;
    private Boolean isPaid;
    private LocalDate paymentDate;
}
