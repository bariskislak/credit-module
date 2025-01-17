package com.ing_hub.credit_module.controller.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class LoanInstallmentResponse {
    private Long id;
    private Long loanId;
    private BigDecimal amount;
    private BigDecimal defaultAmount;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private Boolean isPaid;
}
