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
    public Long transactionId;
    @Column(name = "user_id", nullable = false)
    public Long userId;
    @Column(name = "category_id", nullable = true)
    public int categoryId; // Nullable if not categorized

    @Column(name = "transactionName", nullable = false)
    public String name;
    @Column(name = "amount", nullable = false)
    public double amount;
    @Column(name = "date", nullable = false)
    public LocalDate date; // Use LocalDate in real applications
    @Column(name = "type", nullable = false)
    public String type; // "Income" or "Expense"
    @Column(name = "description", nullable = true)
    public String description;

    public Transaction() {
    }

    public Transaction(Long transactionId, Long userId, int categoryId,String name, double amount, LocalDate date, String type, String description) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.description = description;
    }

    // Getters and Setters
    public Long getTransactionId() { return transactionId; }
    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

