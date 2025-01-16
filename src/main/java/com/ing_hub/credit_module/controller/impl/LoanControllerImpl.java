package com.ing_hub.credit_module.controller.impl;

import com.ing_hub.credit_module.controller.LoanController;
import com.ing_hub.credit_module.controller.request.CreateLoanRequest;
import com.ing_hub.credit_module.controller.request.PayLoanRequest;
import com.ing_hub.credit_module.controller.response.LoanInstallmentResponse;
import com.ing_hub.credit_module.controller.response.LoanResponse;
import com.ing_hub.credit_module.controller.response.PayLoanResponse;
import com.ing_hub.credit_module.mapper.LoanMapper;
import com.ing_hub.credit_module.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanControllerImpl implements LoanController {

    private final LoanService loanService;
    private final LoanMapper loanMapper;

    @Override
    public ResponseEntity<Boolean> createLoan(CreateLoanRequest createLoanRequest) {
        return ResponseEntity.ok(loanService.createLoan(loanMapper.requestToDto(createLoanRequest)));
    }

    @Override
    public ResponseEntity<List<LoanResponse>> listLoans(Long customerId) {
        return ResponseEntity.ok(loanService.listLoans(customerId).stream().map(loanMapper::toLoanResponse).toList());
    }

    @Override
    public ResponseEntity<List<LoanInstallmentResponse>> listInstallments(Long loanId) {
        return ResponseEntity.ok(loanService.listInstallments(loanId).stream().map(loanMapper::toLoanInstallmentResponse).toList());
    }

    @Override
    public ResponseEntity<PayLoanResponse> payLoan(PayLoanRequest payLoanRequest) {
        return ResponseEntity.ok(loanMapper.toPayLoanResponse(loanService.payLoan(loanMapper.toPayLoanDto(payLoanRequest))));
    }

}
