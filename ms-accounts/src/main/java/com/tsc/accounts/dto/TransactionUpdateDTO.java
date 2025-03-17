package com.tsc.accounts.dto;

import com.tsc.accounts.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionUpdateDTO {
    private TransactionType transactionType;
    private Float amount;
    private Float balance;
    private LocalDateTime date;
}
