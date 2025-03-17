package com.tsc.accounts.repository;

import com.tsc.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByAccountNumber(String accountNumber);

    Optional<Account> findAccountByAccountNumberAndIdNot(String accountNumber, Long id);
}
