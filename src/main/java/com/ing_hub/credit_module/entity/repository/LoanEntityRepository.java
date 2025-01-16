package com.ing_hub.credit_module.entity.repository;

import com.ing_hub.credit_module.entity.dao.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanEntityRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findByCustomerId(Long customerId);

    Optional<LoanEntity> findById(Long id);
}
