package com.studyproj.banking.services.impl;

import com.studyproj.banking.entities.Account;
import com.studyproj.banking.entities.Transaction;
import com.studyproj.banking.exceptions.ExceptionMessage;
import com.studyproj.banking.repositories.AccountRepository;
import com.studyproj.banking.repositories.TransactionRepository;
import com.studyproj.banking.services.AccountService;
import com.studyproj.banking.services.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    private final AccountService accountService;

    private static final String ERROR = "Account does not exist.";

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    public Transaction deposit(Transaction transaction) {
        Account account = accountRepository.getReferenceById(transaction.getAccount().getId());
        if (isNull(accountService.getAccount(account.getId()))) {
            throw new ExceptionMessage(ERROR);
        }
        if (account.isBlocked()){
            throw new ExceptionMessage("Account is blocked.");
        }
        transaction.setOldBalance(account.getBalance());
        account.setBalance(account.getBalance() + transaction.getAmount());
        transaction.setNewBalance(account.getBalance());
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionType("Deposit");
        transactionRepository.save(transaction);
        return transaction;
    }

    public Transaction withdraw(Transaction transaction) {
        Account account = accountRepository.getReferenceById(transaction.getAccount().getId());
        if (isNull(accountService.getAccount(account.getId()))) {
            throw new ExceptionMessage(ERROR);
        }
        if (account.isBlocked()){
            throw new ExceptionMessage("Account is blocked.");
        }
        if(account.getBalance() < transaction.getAmount()) {
            throw new ExceptionMessage("Insufficient funds.");
        }
        if(transaction.getAmount() < 10.0) {
            throw new ExceptionMessage("Minimum amount to withdraw is 10.");
        }
        transaction.setOldBalance(account.getBalance());
        account.setBalance(account.getBalance() - transaction.getAmount());
        transaction.setNewBalance(account.getBalance());
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionType("withdraw");
        transactionRepository.save(transaction);
        return transaction;
    }

    public List<Transaction> statement(long id) {
        Account account = accountRepository.getReferenceById(id);
        if (isNull(accountService.getAccount(account.getId()))) {
            throw new ExceptionMessage(ERROR);
        }
        return transactionRepository.findAllByAccountId(id);
    }
}
