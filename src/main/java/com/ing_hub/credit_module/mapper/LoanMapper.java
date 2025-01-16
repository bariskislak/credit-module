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
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    
    @Mapping(source = "customerId", target = "customerId")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "interestRate", target = "interestRate")
    @Mapping(source = "installments", target = "installments")
    CreateLoan requestToDto(CreateLoanRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "customerId", target = "customerId")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "isPaid", target = "isPaid")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "numberOfInstallment", target = "numberOfInstallment")
    LoanResponse toLoanResponse(LoanEntity entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "loanId", target = "loanId")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "defaultAmount", target = "defaultAmount")
    @Mapping(source = "dueDate", target = "dueDate")
    @Mapping(source = "paymentDate", target = "paymentDate")
    @Mapping(source = "isPaid", target = "isPaid")
    LoanInstallmentResponse toLoanInstallmentResponse(LoanInstallmentEntity entity);

    @Mapping(source = "loanId", target = "loanId")
    @Mapping(source = "amount", target = "amount")
    PayLoan toPayLoanDto(PayLoanRequest request);

    @Mapping(source = "completedInstallmentCount", target = "completedInstallmentCount")
    @Mapping(source = "totalInstallmentAmount", target = "totalInstallmentAmount")
    @Mapping(source = "totallyPaid", target = "totallyPaid")
    PayLoanResponse toPayLoanResponse(PayLoanInfo info);
}
