package com.smartfinancetracker.service;

import com.smartfinancetracker.models.Category;
import com.smartfinancetracker.models.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TransactionService {

    ArrayList<Transaction> saveTransactions(ArrayList<Transaction> transactions);

    public Transaction saveTransaction(Transaction transaction);

    public Transaction updateTransaction(Transaction updateTransaction) throws Exception;

    public void deleteTransaction(Long transactionId);

    public List<Transaction> getAllTransactions(Long userId);

    public Optional<Transaction> getTransaction(Long transactionId);

    public List<Transaction> sortTransactionsByDate(Long userId);
}
