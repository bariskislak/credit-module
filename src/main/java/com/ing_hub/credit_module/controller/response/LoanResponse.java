package com.ing_hub.credit_module.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class LoanResponse {
    private Long id;
    private Long customerId;
    private BigDecimal amount;
    private int numberOfInstallment;
    private LocalDateTime createDate;
    private Boolean isPaid;
}
