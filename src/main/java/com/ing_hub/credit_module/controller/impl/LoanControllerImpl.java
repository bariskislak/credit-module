package com.ing_hub.credit_module.controller.impl;

import com.ing_hub.credit_module.controller.LoanController;
import com.ing_hub.credit_module.controller.request.CreateLoanRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoanControllerImpl implements LoanController {

    @Override
    public ResponseEntity<Map<String, Object>> createLoan(CreateLoanRequest createLoanRequest) {
        return null;
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> listLoans(Long customerId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> listInstallments(Long loanId) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> payLoan(Long loanId, Double amount) {
        return null;
    }
}
