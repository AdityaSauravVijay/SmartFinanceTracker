package com.smartfinacetracker.models;

public class User {

    public String email;
    public String password;
    public String userId;
    public double monthlyIncome;

    public User(String email, String password, String userId, double monthlyIncome) {
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.monthlyIncome = monthlyIncome;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}

