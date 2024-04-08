package com.smartfinancetracker.models;

import java.time.LocalDate;

public class Transaction {
    public String transactionId;
    public String userId;
    public String categoryId; // Nullable if not categorized
    public double amount;
    public LocalDate date; // Use LocalDate in real applications
    public String type; // "Income" or "Expense"
    public String description;

    public Transaction(String transactionId, String userId, String categoryId, double amount, LocalDate date, String type, String description) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.description = description;
    }

    // Getters and Setters
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

