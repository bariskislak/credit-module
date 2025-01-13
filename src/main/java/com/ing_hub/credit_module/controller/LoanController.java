package com.ing_hub.credit_module.controller;

import com.ing_hub.credit_module.controller.request.CreateLoanRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/loans")
public interface LoanController {

    @PostMapping("/create")
    ResponseEntity<Map<String, Object>> createLoan(@RequestBody CreateLoanRequest createLoanRequest);

    @GetMapping("/list")
    ResponseEntity<List<Map<String, Object>>> listLoans(@RequestParam("customerId") Long customerId);

    @GetMapping("/{loanId}/installments")
    ResponseEntity<List<Map<String, Object>>> listInstallments(@PathVariable("loanId") Long loanId);

    @PostMapping("/{loanId}/pay")
    ResponseEntity<Map<String, Object>> payLoan(@PathVariable("loanId") Long loanId, @RequestParam("amount") Double amount);
}
