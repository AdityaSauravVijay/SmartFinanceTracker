package com.smartfinancetracker.controller;

import com.smartfinancetracker.models.Transaction;
import com.smartfinancetracker.models.TransactionBag;
import com.smartfinancetracker.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        super();
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> postTransaction(@RequestBody Transaction transaction) {
        Transaction item = transactionService.saveTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Transaction> putTransaction(@PathVariable Long transactionId, @RequestBody Transaction transaction) throws Exception {
        transaction.setTransactionId(transactionId);
        Transaction item = transactionService.updateTransaction(transaction);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long transactionId){
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok("Deleted");
    }
    @GetMapping("/{transactionId}")
    public ResponseEntity<Optional<Transaction>> getOne(@PathVariable Long transactionId) {
        Optional<Transaction> item = transactionService.getTransaction(transactionId);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> allTransactions(@PathVariable Long userId){
        List<Transaction> itemList = transactionService.getAllTransactions(userId);
        return ResponseEntity.status(HttpStatus.OK).body(itemList);
    }

    @GetMapping("/sortByDate/{userId}")
    public ResponseEntity<List<Transaction>> sortTransactionsByDate(@PathVariable Long userId) {
        return new ResponseEntity<List<Transaction>>(transactionService.sortTransactionsByDate(userId), HttpStatus.OK);

    }

    @PostMapping("/addToBag")
    public ResponseEntity<?> addTransactionsToBagAndSave(@RequestBody List<Transaction> transactions) {
        TransactionBag transactionBag = new TransactionBag();

        // Add transactions to the bag
        for (Transaction transaction : transactions) {
            transactionBag.add(transaction);
        }

        transactionService.saveTransactions(new ArrayList<>(transactionBag.toList()));


        return ResponseEntity.ok("Transactions added successfully");
    }

//    @PostMapping
//    public ResponseEntity<ArrayList<Transaction>> postTransactions(@RequestBody Transaction transactions) {
//        ArrayList<Transaction> itemList = transactionService.saveTransactions(transactions);
//        return ResponseEntity.status(HttpStatus.CREATED).body(itemList);
//    }
}
