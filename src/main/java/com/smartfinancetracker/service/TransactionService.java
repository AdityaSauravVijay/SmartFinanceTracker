package com.smartfinancetracker.service;

import com.smartfinancetracker.models.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> sortTransactions();
}
