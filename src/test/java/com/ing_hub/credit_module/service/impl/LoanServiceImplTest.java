package com.ing_hub.credit_module.service.impl;

import com.ing_hub.credit_module.entity.dao.CustomerEntity;
import com.ing_hub.credit_module.entity.dao.LoanEntity;
import com.ing_hub.credit_module.entity.dao.LoanInstallmentEntity;
import com.ing_hub.credit_module.entity.repository.CustomerEntityRepository;
import com.ing_hub.credit_module.entity.repository.LoanEntityRepository;
import com.ing_hub.credit_module.entity.repository.LoanInstallmentEntityRepository;
import com.ing_hub.credit_module.service.dto.CreateLoan;
import com.ing_hub.credit_module.utils.SecurityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {

    @Mock
    private LoanEntityRepository loanRepository;

    @Mock
    private CustomerEntityRepository customerRepository;

    @Mock
    private LoanInstallmentEntityRepository installmentRepository;

    @Mock
    private SecurityUtils securityUtils;

    @InjectMocks
    private LoanServiceImpl loanService;

    private CustomerEntity testCustomer;
    private LoanEntity testLoan;
    private CreateLoan createLoanRequest;

    @BeforeEach
    void setUp() {
        testCustomer = new CustomerEntity();
        testCustomer.setId(1L);
        testCustomer.setCreditLimit(BigDecimal.valueOf(20000));

        testLoan = new LoanEntity();
        testLoan.setId(1L);
        testLoan.setCustomerId(1L);
        testLoan.setAmount(BigDecimal.valueOf(10000));
        testLoan.setIsPaid(false);

        createLoanRequest = new CreateLoan();
        createLoanRequest.setCustomerId(1L);
        createLoanRequest.setAmount(BigDecimal.valueOf(10000));
        createLoanRequest.setInterestRate(0.2);
        createLoanRequest.setInstallments(12);
    }

    @Test
    void createLoan_Success() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        when(loanRepository.save(any(LoanEntity.class))).thenReturn(testLoan);
        doNothing().when(securityUtils).validateUserAccess(any());

        // Act
        Boolean result = loanService.createLoan(createLoanRequest);

        // Assert
        assertTrue(result);
        verify(customerRepository).save(any(CustomerEntity.class));
        verify(installmentRepository).saveAll(anyList());
    }

    @Test
    void createLoan_InsufficientCreditLimit() {
        // Arrange
        testCustomer.setCreditLimit(BigDecimal.valueOf(5000));
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        doNothing().when(securityUtils).validateUserAccess(any());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> loanService.createLoan(createLoanRequest));
    }

    @Test
    void createLoan_CustomerNotFound() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        doNothing().when(securityUtils).validateUserAccess(any());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> loanService.createLoan(createLoanRequest));
    }

    @Test
    void listLoans_Success() {
        // Arrange
        doNothing().when(securityUtils).validateUserAccess(any());
        when(loanRepository.findByCustomerId(1L)).thenReturn(List.of(testLoan));

        // Act
        List<LoanEntity> result = loanService.listLoans(1L);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(testLoan.getId(), result.get(0).getId());
    }

    @Test
    void listInstallments_Success() {
        // Arrange
        LoanInstallmentEntity installment = new LoanInstallmentEntity();
        installment.setLoanId(1L);
        when(loanRepository.findById(1L)).thenReturn(Optional.of(testLoan));
        when(installmentRepository.findByLoanId(1L)).thenReturn(List.of(installment));
        doNothing().when(securityUtils).validateUserAccess(any());

        // Act
        List<LoanInstallmentEntity> result = loanService.listInstallments(1L);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getLoanId());
    }
} 