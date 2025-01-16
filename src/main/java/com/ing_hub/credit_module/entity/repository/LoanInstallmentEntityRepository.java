package com.ing_hub.credit_module.entity.repository;

import com.ing_hub.credit_module.entity.dao.LoanInstallmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanInstallmentEntityRepository extends JpaRepository<LoanInstallmentEntity, Long> {
    List<LoanInstallmentEntity> findByLoanId(Long loanId);

    Optional<LoanInstallmentEntity> findFirstByLoanId(Long loanId);
}
