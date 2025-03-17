package com.tsc.accounts.mapper;

import com.tsc.accounts.dto.TransactionCreateDTO;
import com.tsc.accounts.dto.TransactionDTO;
import com.tsc.accounts.dto.TransactionUpdateDTO;
import com.tsc.accounts.model.Account;
import com.tsc.accounts.model.Transaction;
import com.tsc.accounts.model.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionDTO transactionToTransactionDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .date(transaction.getDate())
                .amount(transaction.getAmount())
                .balance(transaction.getBalance())
                .transactionType(transaction.getTransactionType())
                .id(transaction.getId())
                .build();
    }

    public Transaction transactionDTOtoTransaction(TransactionDTO transactionDTO) {
        return Transaction.builder()
                .id(transactionDTO.getId())
                .date(transactionDTO.getDate())
                .amount(transactionDTO.getAmount())
                .balance(transactionDTO.getBalance())
                .transactionType(transactionDTO.getTransactionType())
                .build();
    }

    public Transaction transactionCreateDTOToTransaction(TransactionCreateDTO transactionDTO) {
        return Transaction.builder()
                .transactionType(transactionDTO.getAmount()>=0? TransactionType.CREDIT:TransactionType.DEBIT)
                .amount(transactionDTO.getAmount())
                .account(Account.builder().id(transactionDTO.getAccountId()).build())
                .build();
    }

    public Transaction patchTransaction(Transaction transaction, TransactionUpdateDTO transactionUpdateDTO){
        if(transactionUpdateDTO.getDate()!=null){
            transaction.setDate(transactionUpdateDTO.getDate());
        }
        if(transactionUpdateDTO.getAmount()!=null){
            transaction.setAmount(transactionUpdateDTO.getAmount());
            TransactionType transactionType=transactionUpdateDTO.getAmount()>=0? TransactionType.CREDIT:TransactionType.DEBIT;
            transaction.setTransactionType(transactionType);
            float newBalance=transaction.getBalance()-transaction.getAmount()+transactionUpdateDTO.getAmount();
            transaction.setBalance(newBalance);
        }
        if(transactionUpdateDTO.getBalance()!=null){
            transaction.setBalance(transactionUpdateDTO.getBalance());
        }
        return transaction;
    }
}
