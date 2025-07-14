package com.studyproj.banking.controller;

import com.studyproj.banking.domain.reponses.TransactionResponse;
import com.studyproj.banking.domain.requests.TransactionRequest;
import com.studyproj.banking.data.entity.Transaction;
import com.studyproj.banking.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Transaction", description = "Transaction Controller")
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    private final ModelMapper modelMapper;

    public TransactionController(TransactionService transactionService, ModelMapper modelMapper) {
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/statement/{id}")
    public ResponseEntity<List<TransactionResponse>> extrato(@PathVariable long id) {
        List<Transaction> statement = transactionService.statement(id);
        List<TransactionResponse> statementList = new ArrayList<>();
        statement.forEach(transaction ->
                statementList.add(modelMapper.map(transaction, TransactionResponse.class)));
        return new ResponseEntity<>(statementList, (!statement.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/deposit")
    public ResponseEntity<TransactionResponse> deposito(@Valid @RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = modelMapper.map(transactionRequest, Transaction.class);
        Transaction deposit = transactionService.deposit(transaction);
        TransactionResponse transactionResponse = modelMapper.map(deposit, TransactionResponse.class);
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK );
    }

    @PostMapping(value = "/withdraw")
    public ResponseEntity<TransactionResponse> saque(@Valid @RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = modelMapper.map(transactionRequest, Transaction.class);
        Transaction withdraw = transactionService.withdraw(transaction);
        TransactionResponse transactionResponse = modelMapper.map(withdraw, TransactionResponse.class);
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK );
    }

}
