package com.ing_hub.credit_module.service;

import com.ing_hub.credit_module.entity.dao.LoanEntity;
import com.ing_hub.credit_module.entity.dao.LoanInstallmentEntity;
import com.ing_hub.credit_module.service.dto.CreateLoan;
import com.ing_hub.credit_module.service.dto.PayLoan;
import com.ing_hub.credit_module.service.dto.PayLoanInfo;

import java.util.List;

public interface LoanService {

    Boolean createLoan(CreateLoan request);

    PayLoanInfo payLoan(PayLoan request);

    List<LoanEntity> listLoans(Long customerId);

    List<LoanInstallmentEntity> listInstallments(Long loanId);

}
