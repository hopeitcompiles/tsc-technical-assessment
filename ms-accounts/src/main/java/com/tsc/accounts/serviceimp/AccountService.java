package com.tsc.accounts.serviceimp;

import com.tsc.accounts.dto.AccountCreateDTO;
import com.tsc.accounts.dto.AccountDTO;
import com.tsc.accounts.dto.AccountUpdateDTO;
import com.tsc.accounts.exception.BusinessException;
import com.tsc.accounts.mapper.AccountMapper;
import com.tsc.accounts.model.Account;
import com.tsc.accounts.repository.IAccountRepository;
import com.tsc.accounts.service.IAccountService;
import com.tsc.accounts.service.IClientService;
import feign.FeignException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final IAccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final IClientService clientService;

    @Autowired
    public AccountService(IAccountRepository repository, IClientService clientService, AccountMapper accountMapper) {
        this.accountRepository = repository;
        this.accountMapper = accountMapper;
        this.clientService = clientService;
    }

    @Override
    public AccountDTO save(AccountCreateDTO accountCreateDTO) {

        try{
            this.clientService.getClient(accountCreateDTO.getClientId());
        }catch (FeignException.NotFound e){
            throw new BusinessException("Client "+accountCreateDTO.getClientId()+" does not exist");
        }catch (FeignException e){
            logger.error("Feign error"+e.getMessage());
            throw new RuntimeException("Error while checking user existence");
        }

        Optional<Account> optionalAccount = this.accountRepository.findAccountByAccountNumber(accountCreateDTO.getAccountNumber());
        if (optionalAccount.isPresent()) {
            String message = String.format("Account number %s already exists", accountCreateDTO.getAccountNumber());
            logger.error(message);
            throw new EntityExistsException(message);
        }
        Account createAccount = accountMapper.accountCreateDTOToAccount(accountCreateDTO);
        if(createAccount.getInitialBalance()==null){
            createAccount.setInitialBalance(0f);
        }
        logger.info("Saving account " + createAccount.toString());
        Account account=this.accountRepository.save(createAccount);
        return accountMapper.accountToAccountDTO(account);
    }

    @Override
    public AccountDTO update(Long id, AccountUpdateDTO accountUpdateDTO) {
        AccountDTO accountDTO=this.findById(id);
        Optional<Account> optionalAccount = this.accountRepository.findAccountByAccountNumberAndIdNot(accountUpdateDTO.getAccountNumber(),id);
        if (optionalAccount.isPresent()) {
            logger.error("Account number already exists: "+accountUpdateDTO.getAccountNumber());
            throw new EntityExistsException("Account number "+accountUpdateDTO.getAccountNumber()+" already exists");
        }
        Account account=accountMapper.accountDTOToAccount(accountDTO);
        Account updateAccount=accountMapper.patchAccount(account,accountUpdateDTO);
        return accountMapper.accountToAccountDTO(this.accountRepository.save(updateAccount));
    }

    @Override
    public AccountDTO delete(Long id) {
        AccountDTO accountDTO=this.findById(id);
        this.accountRepository.deleteById(id);
        return accountDTO;
    }

    @Override
    public AccountDTO findById(Long id) {
        Optional<AccountDTO> accountDTO = this.accountRepository.findById(id).map(accountMapper::accountToAccountDTO);
        if(accountDTO.isEmpty()){
            logger.error("Account not found: "+id);
            throw new EntityNotFoundException("Account not found");
        }
        return accountDTO.get();
    }

    @Override
    public List<AccountDTO> getAll() {
        List<Account> accounts=this.accountRepository.findAll();
        logger.info("Found "+accounts.size()+" accounts");
        return accounts.stream().map(accountMapper::accountToAccountDTO).collect(Collectors.toList());
    }
}
