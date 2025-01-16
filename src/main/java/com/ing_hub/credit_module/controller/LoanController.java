package com.ing_hub.credit_module.controller;

import com.ing_hub.credit_module.controller.request.CreateLoanRequest;
import com.ing_hub.credit_module.controller.request.PayLoanRequest;
import com.ing_hub.credit_module.controller.response.LoanInstallmentResponse;
import com.ing_hub.credit_module.controller.response.LoanResponse;
import com.ing_hub.credit_module.controller.response.PayLoanResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/loans")
public interface LoanController {

    @PostMapping("/create")
    ResponseEntity<Boolean> createLoan(@RequestBody CreateLoanRequest createLoanRequest);

    @GetMapping("/list")
    ResponseEntity<List<LoanResponse>> listLoans(@RequestParam("customerId") Long customerId);

    @GetMapping("/{loanId}/installments")
    ResponseEntity<List<LoanInstallmentResponse>> listInstallments(@PathVariable("loanId") Long loanId);

    @PostMapping("/{loanId}/pay")
    ResponseEntity<PayLoanResponse> payLoan(@RequestBody PayLoanRequest payLoanRequest);
}
