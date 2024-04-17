package com.smartfinancetracker.models;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Data
@Entity
@Table(name="users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "role", nullable = true)
    private String role;

    @Column(name = "monthly_income", nullable = true)
    private double monthlyIncome;

    // Default constructor needed by JPA
    public User() {
    }

    // Your constructor
    public User(String email, String password, long userId, double monthlyIncome, String role, String name) {
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.monthlyIncome = monthlyIncome;
        this.role = role;
        this.name = name;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

