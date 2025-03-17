package com.tsc.accounts.service;

import com.tsc.accounts.dto.AccountCreateDTO;
import com.tsc.accounts.dto.AccountDTO;
import com.tsc.accounts.dto.AccountUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
     AccountDTO save(AccountCreateDTO accountCreateDTO);
     AccountDTO update(Long id, AccountUpdateDTO accountUpdateDTO);
     AccountDTO delete(Long id);
     AccountDTO findById(Long id);
     List<AccountDTO> getAll();
}
