package com.smartfinancetracker.controller;

import com.smartfinancetracker.models.Transaction;
import com.smartfinancetracker.models.User;
import com.smartfinancetracker.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        super();
        this.transactionService = transactionService;
    }

    @GetMapping("/sortByDate")
    public ResponseEntity<List<Transaction>> sortTransactionsByDate() {
        return new ResponseEntity<List<Transaction>>(transactionService.sortTransactions(), HttpStatus.OK);

    }
}
