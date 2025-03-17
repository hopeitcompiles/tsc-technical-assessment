package com.tsc.accounts.serviceimp;

import com.tsc.accounts.dto.TransactionCreateDTO;
import com.tsc.accounts.dto.TransactionDTO;
import com.tsc.accounts.dto.TransactionUpdateDTO;
import com.tsc.accounts.exception.BusinessException;
import com.tsc.accounts.mapper.TransactionMapper;
import com.tsc.accounts.model.Account;
import com.tsc.accounts.model.Transaction;
import com.tsc.accounts.model.TransactionType;
import com.tsc.accounts.repository.IAccountRepository;
import com.tsc.accounts.repository.ITransactionRepository;
import com.tsc.accounts.service.ITransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;
    private final IAccountRepository accountRepository;
    private final TransactionMapper transactionMapper;
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(ITransactionRepository transactionRepository, IAccountRepository accountRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDTO save(TransactionCreateDTO createDTO)  {
        Optional<Account> optionalAccount = this.accountRepository.findById(createDTO.getAccountId());
        if(optionalAccount.isEmpty()) {
            throw new BusinessException("Account not found");
        }

        Transaction transaction=transactionMapper.transactionCreateDTOToTransaction(createDTO);
        transaction.setDate(LocalDateTime.now());
        transaction.setAccount(optionalAccount.get());
        List<Transaction> transactions=this.transactionRepository.findAllByAccountIdOrderByDateDesc(optionalAccount.get().getId());

        float balance=createDTO.getAmount();
        if(!transactions.isEmpty()){
            Transaction lastTransaction=transactions.get(0);
            logger.info("Last transaction: "+lastTransaction);
            balance+=lastTransaction.getBalance()!=null?lastTransaction.getBalance():0f;
            logger.info("New transaction balance: "+balance);
        }

        if(balance<0){
            throw new BusinessException("Saldo no disponible");
        }

        transaction.setTransactionType(createDTO.getAmount()<0? TransactionType.DEBIT:TransactionType.CREDIT);
        transaction.setBalance(balance);
        Transaction createdTransaction= this.transactionRepository.save(transaction);
        return transactionMapper.transactionToTransactionDTO(createdTransaction);
    }

    @Override
    public TransactionDTO update(Long id, TransactionUpdateDTO updateDTO) {
        TransactionDTO transactionDTO=this.findById(id);
        Transaction transaction=transactionMapper.transactionDTOtoTransaction(transactionDTO);
        Transaction updateTransaction=this.transactionMapper.patchTransaction(transaction,updateDTO);
        return this.transactionMapper.transactionToTransactionDTO(this.transactionRepository.save(updateTransaction));
    }

    @Override
    public TransactionDTO delete(Long id) {
        TransactionDTO transactionDTO=this.findById(id);
        this.transactionRepository.deleteById(id);
        return transactionDTO;
    }

    @Override
    public TransactionDTO findById(Long id) {
        Optional<TransactionDTO> transactionDTO = this.transactionRepository.findById(id).map(transactionMapper::transactionToTransactionDTO);
        if(transactionDTO.isEmpty()){
            throw new EntityNotFoundException("Transaction not found");
        }
        return transactionDTO.get();
    }

    @Override
    public List<TransactionDTO> getAll() {
        return this.transactionRepository.findAll().stream().map(transactionMapper::transactionToTransactionDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getAllByAccountId(Long accountId) {
        return this.transactionRepository.findAllByAccountIdOrderByDateDesc(accountId).stream().map(transactionMapper::transactionToTransactionDTO).collect(Collectors.toList());
    }
}
