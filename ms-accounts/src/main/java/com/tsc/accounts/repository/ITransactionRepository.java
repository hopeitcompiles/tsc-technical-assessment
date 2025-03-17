package com.tsc.accounts.repository;

import com.tsc.accounts.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByAccountIdOrderByDateDesc(Long accountId);
}
