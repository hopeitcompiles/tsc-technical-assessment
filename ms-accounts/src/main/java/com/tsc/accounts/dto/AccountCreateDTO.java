package com.tsc.accounts.dto;

import com.tsc.accounts.model.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@ToString
public class AccountCreateDTO {
    @NotNull
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private String accountNumber;
    @NotNull
    private AccountType accountType;
    private Float initialBalance;
    @NotNull
    private Long clientId;
}
