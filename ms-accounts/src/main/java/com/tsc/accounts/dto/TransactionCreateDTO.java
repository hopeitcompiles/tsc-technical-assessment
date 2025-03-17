package com.tsc.accounts.dto;

import com.tsc.accounts.model.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionCreateDTO {
    @NotNull
    private Float amount;
    @NotNull
    private Long accountId;
}
