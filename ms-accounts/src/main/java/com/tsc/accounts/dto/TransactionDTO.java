package com.tsc.accounts.dto;

import com.tsc.accounts.model.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private TransactionType transactionType;
    private Float amount;
    private Float balance;
    private LocalDateTime date;
}
