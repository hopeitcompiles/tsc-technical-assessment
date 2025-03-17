package com.tsc.accounts.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "transaction")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString(exclude = "account")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private Float amount;
    private Float balance;
    @Column(name = "transaction_date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;
}
