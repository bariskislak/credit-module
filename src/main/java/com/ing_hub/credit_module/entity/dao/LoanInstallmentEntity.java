package com.ing_hub.credit_module.entity.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="loan_installment")
@Getter
@Setter
public class LoanInstallmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long loanId;
    @Column
    private BigDecimal amount;
    @Column
    private LocalDate dueDate;
    @Column
    private BigDecimal defaultAmount;
    @Column
    private Boolean isPaid;
    @Column
    private LocalDate paymentDate;
}
