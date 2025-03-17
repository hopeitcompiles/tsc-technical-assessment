package com.tsc.accounts.dto;

import com.tsc.accounts.model.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateDTO {
    private AccountType accountType;
    private String accountNumber;
    private Boolean status;
}
