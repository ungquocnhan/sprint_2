package com.example.sprint.repository;

import com.example.sprint.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);

    boolean existsByEmail(String email);
}
