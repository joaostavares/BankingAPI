package com.studyproj.banking.service;

import com.studyproj.banking.data.entity.Account;
import com.studyproj.banking.data.entity.Transaction;
import com.studyproj.banking.config.ExceptionMessage;
import com.studyproj.banking.data.repository.AccountRepository;
import com.studyproj.banking.data.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    private TransactionService transactionService;

    private TransactionRepository transactionRepository;

    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        transactionRepository = mock(TransactionRepository.class);
        transactionService = new TransactionService(transactionRepository, accountRepository, accountService);
    }

    @Test
    void getSuccessWhenDeposit() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(11000.0);

        Transaction transaction = new Transaction();
        transaction.setAmount(1000.0);
        transaction.setAccount(account);

        when(accountRepository.getReferenceById(Mockito.anyLong())).thenReturn(account);
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(account));
        when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(transaction);

        Assertions.assertDoesNotThrow(() -> transactionService.deposit(transaction));

    }

    @Test
    void getNonExistingAccountWhenDeposit() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(11000.0);

        Transaction transaction = new Transaction();
        transaction.setAmount(1000.0);
        transaction.setAccount(account);

        when(accountRepository.getReferenceById(Mockito.anyLong())).thenReturn(account);
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.empty());

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> transactionService.deposit(transaction), "Account does not exist.");
    }

    @Test
    void getBlockedAccountWhenDeposit() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(11000.0);
        account.setBlocked(true);

        Transaction transaction = new Transaction();
        transaction.setAmount(1000.0);
        transaction.setAccount(account);

        when(accountRepository.getReferenceById(Mockito.anyLong())).thenReturn(account);
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(account));

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> transactionService.deposit(transaction), "Account is blocked.");
    }

    @Test
    void getSuccessWhenWithdraw() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(11000.0);

        Transaction transaction = new Transaction();
        transaction.setAmount(100.0);
        transaction.setAccount(account);

        when(accountRepository.getReferenceById(Mockito.anyLong())).thenReturn(account);
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(account));
        when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(transaction);

        Assertions.assertDoesNotThrow(() -> transactionService.withdraw(transaction));
    }

    @Test
    void getNonExistingAccountWhenWithdraw() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(11000.0);

        Transaction transaction = new Transaction();
        transaction.setAmount(100.0);
        transaction.setAccount(account);

        when(accountRepository.getReferenceById(Mockito.anyLong())).thenReturn(account);
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.empty());

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> transactionService.withdraw(transaction), "Account does not exist.");
    }

    @Test
    void getBlockedAccountWhenWithdraw() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(11000.0);
        account.setBlocked(true);

        Transaction transaction = new Transaction();
        transaction.setAmount(100.0);
        transaction.setAccount(account);

        when(accountRepository.getReferenceById(Mockito.anyLong())).thenReturn(account);
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(account));

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> transactionService.withdraw(transaction), "Account is blocked.");
    }

    @Test
    void getInsufficientFoundsWhenWithdraw() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(11000.0);

        Transaction transaction = new Transaction();
        transaction.setAmount(100000.0);
        transaction.setAccount(account);

        when(accountRepository.getReferenceById(Mockito.anyLong())).thenReturn(account);
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(account));

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> transactionService.withdraw(transaction), "Insufficient funds.");
    }

    @Test
    void getSuccessWhenGettingStatement() {
        Account account = mock(Account.class);
        Transaction transaction1 = mock(Transaction.class);
        Transaction transaction2 = mock(Transaction.class);

        when(accountRepository.getReferenceById(Mockito.anyLong())).thenReturn(account);
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(account));

        when(transactionRepository.findAllByAccountId(Mockito.anyLong())).thenReturn(List.of(transaction1, transaction2));

        Assertions.assertDoesNotThrow(() -> transactionService.statement(Mockito.anyLong()));
        Assertions.assertEquals(2, transactionService.statement(Mockito.anyLong()).size());
    }

    @Test
    void getNonExistingAccountWhenGettingStatement() {
        when(accountRepository.getReferenceById(Mockito.anyLong())).thenReturn(mock(Account.class));
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.empty());

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> transactionService.statement(Mockito.anyLong()), "Account does not exist.");
    }
}
