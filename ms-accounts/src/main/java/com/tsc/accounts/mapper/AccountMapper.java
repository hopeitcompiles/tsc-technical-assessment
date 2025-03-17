package com.tsc.accounts.mapper;

import com.tsc.accounts.dto.AccountCreateDTO;
import com.tsc.accounts.dto.AccountDTO;
import com.tsc.accounts.dto.AccountUpdateDTO;
import com.tsc.accounts.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountDTO accountToAccountDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .initialBalance(account.getInitialBalance())
                .status(account.getStatus())
                .clientId(account.getClientId())
                .build();
    }

    public Account accountDTOToAccount(AccountDTO accountDTO) {
        return Account.builder()
                .id(accountDTO.getId())
                .accountType(accountDTO.getAccountType())
                .accountNumber(accountDTO.getAccountNumber())
                .initialBalance(accountDTO.getInitialBalance())
                .status(accountDTO.getStatus())
                .clientId(accountDTO.getClientId())
                .build();
    }

    public Account accountCreateDTOToAccount(AccountCreateDTO accountCreateDTO) {
        return Account.builder()
                .accountNumber(accountCreateDTO.getAccountNumber())
                .accountType(accountCreateDTO.getAccountType())
                .initialBalance(accountCreateDTO.getInitialBalance())
                .clientId(accountCreateDTO.getClientId())
                .status(true)
                .build();
    }

    public Account patchAccount(Account account, AccountUpdateDTO accountUpdateDTO) {
        if(accountUpdateDTO.getAccountNumber()!=null) {
            account.setAccountNumber(accountUpdateDTO.getAccountNumber());
        }
        if(accountUpdateDTO.getAccountType()!=null) {
            account.setAccountType(accountUpdateDTO.getAccountType());
        }
        if(accountUpdateDTO.getStatus()!=null) {
            account.setStatus(accountUpdateDTO.getStatus());
        }
        return account;
    }
}
