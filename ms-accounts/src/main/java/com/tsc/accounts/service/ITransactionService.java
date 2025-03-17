package com.tsc.accounts.service;

import com.tsc.accounts.dto.*;

import java.util.List;

public interface ITransactionService {
     TransactionDTO save(TransactionCreateDTO createDTO);
     TransactionDTO update(Long id, TransactionUpdateDTO updateDTO);
     TransactionDTO delete(Long id);
     TransactionDTO findById(Long id);
     List<TransactionDTO> getAll();
     List<TransactionDTO> getAllByAccountId(Long accountId);
}
