package com.smartfinacetracker.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bag {
    private String bagId;
    private String userId;
    private List<Transaction> transactions;

    public Bag(String bagId, String userId) {
        this.bagId = bagId;
        this.userId = userId;
        this.transactions = new ArrayList<>();
    }

    // Method to add a transaction
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    // Method to remove a transaction by ID
    public boolean removeTransaction(String transactionId) {
        return transactions.removeIf(t -> t.getTransactionId().equals(transactionId));
    }

    // Get total amount of all transactions
    public double getTotalAmount() {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    // Filter transactions by type
    public List<Transaction> getTransactionsByType(String type) {
        return transactions.stream().filter(t -> t.getType().equals(type)).collect(Collectors.toList());
    }

    // Filter transactions by category
    public List<Transaction> getTransactionsByCategory(String categoryId) {
        return transactions.stream().filter(t -> t.getCategoryId().equals(categoryId)).collect(Collectors.toList());
    }

    // Filter transactions within a date range
    public List<Transaction> getTransactionsByDateRange(LocalDate start, LocalDate end) {
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(start) && !t.getDate().isAfter(end))
                .collect(Collectors.toList());
    }

    // Getters
    public String getBagId() { return bagId; }
    public String getUserId() { return userId; }
    public List<Transaction> getTransactions() { return new ArrayList<>(transactions); }

    // Setters
    public void setBagId(String bagId) { this.bagId = bagId; }
    public void setUserId(String userId) { this.userId = userId; }
    // Note: Setters for the transactions list itself are intentionally omitted to control modifications through defined methods only.
}


