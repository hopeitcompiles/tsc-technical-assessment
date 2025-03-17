package com.tsc.accounts.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number",unique = true, nullable = false)
    private String accountNumber;
    @Column(name="account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(name="initial_balance")
    private Float initialBalance;
    private Boolean status;
    @Column(name="client_id")
    private Long clientId;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;
}
