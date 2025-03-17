package com.tsc.accounts.controller;

import com.tsc.accounts.dto.*;
import com.tsc.accounts.serviceimp.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movimientos")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAccounts(){
        List<TransactionDTO> transactions=this.transactionService.getAll();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getAccount(@PathVariable Long id){
        TransactionDTO transaction=this.transactionService.findById(id);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createAccount(@Valid @RequestBody TransactionCreateDTO transaction){
        TransactionDTO transactionDTO=this.transactionService.save(transaction);
        return ResponseEntity.ok(transactionDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TransactionDTO> updateAccount(@PathVariable Long id,@RequestBody TransactionUpdateDTO transaction){
        TransactionDTO transactionDTO=this.transactionService.update(id, transaction);
        return ResponseEntity.ok(transactionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransactionDTO> deleteAccount(@PathVariable Long id){
        TransactionDTO transactionDTO=this.transactionService.delete(id);
        return ResponseEntity.ok(transactionDTO);
    }
}
