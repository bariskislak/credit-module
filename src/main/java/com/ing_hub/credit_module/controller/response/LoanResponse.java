package com.ing_hub.credit_module.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class LoanResponse {
    private Long id;
    private Long customerId;
    private BigDecimal amount;
    private int numberOfInstallment;
    private LocalDateTime createDate;
    private Boolean isPaid;
}
