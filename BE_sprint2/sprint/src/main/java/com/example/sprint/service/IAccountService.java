package com.example.sprint.service;

import com.example.sprint.model.Account;

import java.util.Optional;

public interface IAccountService {
    Optional<Account> findByEmail(String email);

    boolean existsByEmail(String email);

    Account save(Account account);
}
