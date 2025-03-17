package com.tsc.accounts.dto;

import com.tsc.accounts.model.AccountType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private AccountType accountType;
    private Float initialBalance;
    private Boolean status;
    private Long clientId;
}
