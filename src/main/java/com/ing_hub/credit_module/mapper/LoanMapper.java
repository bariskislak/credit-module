package com.ing_hub.credit_module.mapper;

import com.ing_hub.credit_module.controller.request.CreateLoanRequest;
import com.ing_hub.credit_module.controller.request.PayLoanRequest;
import com.ing_hub.credit_module.controller.response.LoanInstallmentResponse;
import com.ing_hub.credit_module.controller.response.LoanResponse;
import com.ing_hub.credit_module.controller.response.PayLoanResponse;
import com.ing_hub.credit_module.entity.dao.LoanEntity;
import com.ing_hub.credit_module.entity.dao.LoanInstallmentEntity;
import com.ing_hub.credit_module.service.dto.CreateLoan;
import com.ing_hub.credit_module.service.dto.PayLoan;
import com.ing_hub.credit_module.service.dto.PayLoanInfo;
import org.springframework.stereotype.Service;

@Service
public class LoanMapper {

    public CreateLoan requestToDto(CreateLoanRequest request) {
        return CreateLoan.builder()
                .customerId(request.getCustomerId())
                .amount(request.getAmount())
                .interestRate(request.getInterestRate())
                .installments(request.getInstallments())
                .build();
    }

    public LoanResponse toLoanResponse(LoanEntity entity) {
        return LoanResponse.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerId())
                .amount(entity.getAmount())
                .isPaid(entity.getIsPaid())
                .createDate(entity.getCreateDate())
                .numberOfInstallment(entity.getNumberOfInstallment())
                .build();
    }

    public LoanInstallmentResponse toLoanInstallmentResponse(LoanInstallmentEntity entity) {
        return LoanInstallmentResponse.builder()
                .id(entity.getId())
                .loanId(entity.getLoanId())
                .amount(entity.getAmount())
                .defaultAmount(entity.getDefaultAmount())
                .dueDate(entity.getDueDate())
                .paymentDate(entity.getPaymentDate())
                .isPaid(entity.getIsPaid())
                .build();
    }

    public PayLoan toPayLoanDto(PayLoanRequest request) {
        return PayLoan.builder()
                .loanId(request.getLoanId())
                .amount(request.getAmount())
                .build();
    }

    public PayLoanResponse toPayLoanResponse(PayLoanInfo info) {
        return PayLoanResponse.builder()
                .completedInstallmentCount(info.getCompletedInstallmentCount())
                .totalInstallmentAmount(info.getTotalInstallmentAmount())
                .totallyPaid(info.isTotallyPaid())
                .build();
    }
}
