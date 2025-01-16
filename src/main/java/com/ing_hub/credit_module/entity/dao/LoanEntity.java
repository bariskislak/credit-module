package com.ing_hub.credit_module.entity.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="loan")
@Getter
@Setter
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long customerId;
    @Column
    private BigDecimal amount;
    @Column
    private int numberOfInstallment;
    @Column
    private LocalDateTime createDate;
    @Column
    private Boolean isPaid;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }
}
