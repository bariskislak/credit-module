package com.ing_hub.credit_module.service.impl;

import com.ing_hub.credit_module.entity.dao.CustomerEntity;
import com.ing_hub.credit_module.entity.dao.LoanEntity;
import com.ing_hub.credit_module.entity.dao.LoanInstallmentEntity;
import com.ing_hub.credit_module.entity.repository.CustomerEntityRepository;
import com.ing_hub.credit_module.entity.repository.LoanEntityRepository;
import com.ing_hub.credit_module.entity.repository.LoanInstallmentEntityRepository;
import com.ing_hub.credit_module.service.LoanService;
import com.ing_hub.credit_module.service.dto.CreateLoan;
import com.ing_hub.credit_module.service.dto.PayLoan;
import com.ing_hub.credit_module.service.dto.PayLoanInfo;
import com.ing_hub.credit_module.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanEntityRepository loanRepository;

    private final CustomerEntityRepository customerRepository;

    private final LoanInstallmentEntityRepository loanInstallmentRepository;

    private final SecurityUtils securityUtils;

    /**
     * Create a new loan for a customer.
     */
    public Boolean createLoan(CreateLoan request) {
        securityUtils.validateUserAccess(request.getCustomerId());
        BigDecimal totalAmount = request.getAmount().multiply(BigDecimal.valueOf(1 + request.getInterestRate()));
        Optional<CustomerEntity> customer = customerRepository.findById(request.getCustomerId());
        BigDecimal remainingLimit = customer.map(CustomerEntity::getCreditLimit).orElse(BigDecimal.ZERO);

        if (totalAmount.compareTo(remainingLimit) > 0) {
            throw new IllegalArgumentException("Customer does not have enough credit limit.");
        }

        LoanEntity loan = new LoanEntity();
        loan.setCustomerId(request.getCustomerId());
        loan.setAmount(totalAmount);
        loan.setNumberOfInstallment(request.getInstallments());
        loan.setIsPaid(false);

        loan = loanRepository.save(loan);

        generateInstallments(loan.getId(), totalAmount, request.getInstallments());
        customer.ifPresent(customerEntity -> {
            customerEntity.setCreditLimit(remainingLimit.subtract(totalAmount));
            customerRepository.save(customerEntity);
        });
        return true;
    }

    private void generateInstallments(Long loanId, BigDecimal totalAmount, int numberOfInstallments) {
        BigDecimal installmentAmount = totalAmount.divide(BigDecimal.valueOf(numberOfInstallments), RoundingMode.HALF_UP);

        List<LoanInstallmentEntity> installments = new ArrayList<>();
        for (int i = 1; i <= numberOfInstallments; i++) {
            LoanInstallmentEntity installment = new LoanInstallmentEntity();
            installment.setLoanId(loanId);
            installment.setAmount(installmentAmount);
            installment.setDefaultAmount(BigDecimal.ZERO);
            installment.setDueDate(LocalDate.now().withDayOfMonth(1).plusMonths(i));
            installment.setIsPaid(false);
            installments.add(installment);
        }

        loanInstallmentRepository.saveAll(installments);
    }

    /**
     * List loans for a customer.
     */
    public List<LoanEntity> listLoans(Long customerId) {
        securityUtils.validateUserAccess(customerId);
        return loanRepository.findByCustomerId(customerId);
    }

    /**
     * List installments for a loan.
     */
    public List<LoanInstallmentEntity> listInstallments(Long loanId) {
        loanRepository.findById(loanId).ifPresent(loan -> securityUtils.validateUserAccess(loan.getCustomerId()));
        return loanInstallmentRepository.findByLoanId(loanId);
    }

    /**
     * Pay installments for a loan.
     */
    public PayLoanInfo payLoan(PayLoan request) {
        Long loanId = request.getLoanId();
        BigDecimal paymentAmount = request.getAmount();
        LoanEntity loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found."));
        securityUtils.validateUserAccess(loan.getCustomerId());

        List<LoanInstallmentEntity> installments = loanInstallmentRepository.findByLoanId(loanId);
        Optional<CustomerEntity> customerEntity = customerRepository.findById(loan.getCustomerId());
        BigDecimal remainingPayment = paymentAmount;
        int installmentsPaid = 0;
        BigDecimal totalSpent = BigDecimal.ZERO;

        for (LoanInstallmentEntity installment : installments) {
            if (installment.getDueDate().isAfter(LocalDate.now().plusMonths(3)) || installment.getIsPaid()) {
                continue;
            }

            if (remainingPayment.compareTo(installment.getAmount()) >= 0) {
                installment.setIsPaid(true);
                installment.setPaymentDate(LocalDate.now()); 
                installmentsPaid++;
                customerEntity.ifPresent(entity -> {
                    entity.setCreditLimit(entity.getCreditLimit().add(installment.getAmount()));
                    customerRepository.save(entity);
                });
                if (remainingPayment.compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }
            }

            remainingPayment = remainingPayment.subtract(installment.getAmount());
            totalSpent = totalSpent.add(installment.getAmount());
        }

        loanInstallmentRepository.saveAll(installments);

        if (installments.stream().allMatch(LoanInstallmentEntity::getIsPaid)) {
            loan.setIsPaid(true);
            loanRepository.save(loan);
        }
        return PayLoanInfo.builder().completedInstallmentCount(installmentsPaid)
                .totalInstallmentAmount(totalSpent).totallyPaid(loan.getIsPaid()).build();
    }
}
