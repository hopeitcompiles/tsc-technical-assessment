package com.tsc.accounts.controller;

import com.tsc.accounts.dto.AccountCreateDTO;
import com.tsc.accounts.dto.AccountDTO;
import com.tsc.accounts.dto.AccountUpdateDTO;
import com.tsc.accounts.dto.TransactionDTO;
import com.tsc.accounts.model.Client;
import com.tsc.accounts.service.IAccountService;
import com.tsc.accounts.service.IClientService;
import com.tsc.accounts.service.ITransactionService;
import com.tsc.accounts.serviceimp.TransactionService;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cuentas")
public class AccountController {
    private final IAccountService accountService;
    private final ITransactionService transactionService;

    public AccountController(IAccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAccounts(){
        List<AccountDTO> accounts=this.accountService.getAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id){
        AccountDTO account=this.accountService.findById(id);
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountCreateDTO account){
        AccountDTO accountDTO=this.accountService.save(account);
        return ResponseEntity.ok(accountDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id,@RequestBody AccountUpdateDTO account){
        AccountDTO accountDTO=this.accountService.update(id, account);
        return ResponseEntity.ok(accountDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDTO> deleteAccount(@PathVariable Long id){
        AccountDTO accountDTO=this.accountService.delete(id);
        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping("/{id}/movimientos")
    public ResponseEntity<List<TransactionDTO>> getTransactions(@PathVariable Long id){
        List<TransactionDTO> transactions=this.transactionService.getAllByAccountId(id);
        return ResponseEntity.ok(transactions);
    }


}
