package com.studyproj.banking.service;

import com.studyproj.banking.data.entity.Account;
import com.studyproj.banking.config.ExceptionMessage;
import com.studyproj.banking.data.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    private AccountService accountService;

    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        accountService = new AccountService(accountRepository);
    }

    @Test
    void getsuccessGetAllAccounts() {
        Account account1 = mock(Account.class);
        Account account2 = mock(Account.class);

        when(accountService.getAll()).thenReturn(List.of(account1, account2));

        Assertions.assertDoesNotThrow(() -> accountService.getAll());
        Assertions.assertEquals(2, accountService.getAll().size());
    }

    @Test
    void getSuccessAccountById() {
        Account account = mock(Account.class);

        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(account));

        Assertions.assertDoesNotThrow(() -> accountService.getAccount(Mockito.anyLong()));
        Assertions.assertEquals(account, accountService.getAccount(Mockito.anyLong()));
    }

    @Test
    void getSuccessWhenCreateANewAccount() {
        Assertions.assertDoesNotThrow(() -> accountService.createAccount(new Account()));
    }

    @Test
    void getSuccessWhenBlockAccount() {
        Account account = mock(Account.class);

        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(account));

        Assertions.assertEquals("The account with id " + account.getId() + " is now blocked", accountService.blockAccount(Mockito.anyLong()));
    }

    @Test
    void getAccountDoNotExistsWhenBlockAccount() {
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.empty());

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> accountService.blockAccount(Mockito.anyLong()), "Account not found");
    }

    @Test
    void getAccountAlreadyBlockedWhenBlockAccount() {
        Account account = new Account();
        account.setId(Mockito.anyLong());
        account.setBlocked(true);

        when(accountRepository.findById(account.getId())).thenReturn(java.util.Optional.of(account));

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> accountService.blockAccount(Mockito.anyLong()), "Account already blocked");
    }

    @Test
    void getSuccessWhenUnblockAccount() {
        Account account = new Account();
        account.setId(Mockito.anyLong());
        account.setBlocked(true);

        when(accountRepository.findById(account.getId())).thenReturn(java.util.Optional.of(account));

        Assertions.assertEquals("The account with id " + account.getId() + " is now unblocked", accountService.unblockAccount(account.getId()));
    }

    @Test
    void getAccountDoNotExistsWhenUnblockAccount() {
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.empty());

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> accountService.unblockAccount(Mockito.anyLong()), "Account not found");
    }

    @Test
    void getAccountAlreadyUnblockedWhenUnblockAccount() {
        Account account = new Account();
        account.setId(Mockito.anyLong());
        account.setBlocked(false);

        when(accountRepository.findById(account.getId())).thenReturn(java.util.Optional.of(account));

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> accountService.unblockAccount(Mockito.anyLong()), "Account already unblocked");
    }

    @Test
    void getFalseWhenCheckBlockStatus() {
        Account account = new Account();
        account.setId(Mockito.anyLong());
        account.setBlocked(false);

        when(accountRepository.findById(account.getId())).thenReturn(java.util.Optional.of(account));

        Assertions.assertEquals("The account with id " + account.getId() + " is unblocked", accountService.getBlockedStatus(account.getId()));
    }

    @Test
    void getTrueWhenCheckBlockStatus() {
        Account account = new Account();
        account.setId(Mockito.anyLong());
        account.setBlocked(true);

        when(accountRepository.findById(account.getId())).thenReturn(java.util.Optional.of(account));

        Assertions.assertEquals("The account with id " + account.getId() + " is blocked", accountService.getBlockedStatus(account.getId()));
    }

    @Test
    void getAccountDoNotExistsWhenCheckBlockStatus() {
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.empty());

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> accountService.getBlockedStatus(Mockito.anyLong()), "Account not found");
    }
}