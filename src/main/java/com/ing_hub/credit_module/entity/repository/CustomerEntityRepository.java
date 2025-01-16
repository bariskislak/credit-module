package com.ing_hub.credit_module.entity.repository;

import com.ing_hub.credit_module.entity.dao.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findById(Long id);
}
