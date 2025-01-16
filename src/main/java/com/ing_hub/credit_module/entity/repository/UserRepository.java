package com.ing_hub.credit_module.entity.repository;

import com.ing_hub.credit_module.entity.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
