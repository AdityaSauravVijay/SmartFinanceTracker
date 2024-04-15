package com.smartfinancetracker.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "category_id", nullable = true) // Nullable if not categorized
    private String categoryId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date")
    private LocalDate date; // Use LocalDate in real applications

    @Column(name = "type")
    private String type; // "Income" or "Expense"

    @Column(name = "description", nullable = true)
    private String description;

    // Default constructor needed by JPA
    public Transaction() {
    }

    // Your constructor
    public Transaction(Long transactionId, String userId, String categoryId, double amount, LocalDate date, String type, String description) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.description = description;
    }

    // Getters and Setters
    public Long getTransactionId() { return transactionId; }
    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; }

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

