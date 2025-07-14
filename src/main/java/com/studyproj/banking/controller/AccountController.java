package com.studyproj.banking.controller;

import com.studyproj.banking.domain.reponses.AccountResponse;
import com.studyproj.banking.domain.requests.AccountRequest;
import com.studyproj.banking.data.entity.Account;
import com.studyproj.banking.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//todo: move all modelmappers from controller to service and refact returns to deal with status directly on service instead of controller
@Tag(name = "Account", description = "Account Controller")
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final ModelMapper modelMapper;

    public AccountController(AccountService accountService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Account>> get() {
        List<Account> accounts = accountService.getAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable long id) {
        Account account = accountService.getAccount(id);
        return new ResponseEntity<>(account, (account != null ? HttpStatus.OK : HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AccountResponse> post(@Valid @RequestBody AccountRequest accountRequest) {
        Account account = modelMapper.map(accountRequest, Account.class);
        Account creation = accountService.createAccount(account);
        AccountResponse accountResponse = modelMapper.map(creation, AccountResponse.class);

        return new ResponseEntity<>(accountResponse, (creation == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED ));
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<Double> saldo(@PathVariable long id) {
        Account account = accountService.getAccount(id);
        return account != null ? new ResponseEntity<>(account.getBalance(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/block/{id}")
    public ResponseEntity<String> block(@PathVariable long id) {
        String blockAccount = accountService.blockAccount(id);
        return new ResponseEntity<>(blockAccount, blockAccount != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/unblock/{id}")
    public ResponseEntity<String> unblock(@PathVariable long id) {
        String unblockAccount = accountService.unblockAccount(id);
        return new ResponseEntity<>(unblockAccount, unblockAccount != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/isblocked/{id}")
    public ResponseEntity<String> activityCheck(@PathVariable long id) {
        String activity = accountService.getBlockedStatus(id);
        return new ResponseEntity<>(activity, activity != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);

    }
}
